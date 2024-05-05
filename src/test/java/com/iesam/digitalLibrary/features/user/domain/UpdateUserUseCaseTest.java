package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubDataSourceRepository2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateUserUseCaseTest {

    private UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        updateUserUseCase = null;
    }

    @Test
    public void cuandoSeActualizanLosDatosDeUsuarioExistenteDebenRealizarseLosCambios(){
        //Given
        User existingUser = new User(1, "OldName", "OldSurname", "OldDNI", "OldEmail");
        StubDataSourceRepository2 stubDataSourceRepository2 = new StubDataSourceRepository2();
        stubDataSourceRepository2.save(existingUser);

        User updatedUser = new User(1, "NewName", "NewSurname", "NewDNI", "NewEmail");
        updateUserUseCase = new UpdateUserUseCase(new UserDataRepository(stubDataSourceRepository2));

        //When
        updateUserUseCase.execute(updatedUser);

        //Then
        User retrievedUser = stubDataSourceRepository2.findById(1);

        Assertions.assertEquals(updatedUser.id , retrievedUser.id);
        Assertions.assertEquals(updatedUser.name , retrievedUser.name);
        Assertions.assertEquals(updatedUser.surname, retrievedUser.surname);
        Assertions.assertEquals(updatedUser.dni, retrievedUser.dni);
        Assertions.assertEquals(updatedUser.email , retrievedUser.email);

    }

    @Test
    public void cuandoSeIntentaActualizarLosDatosDeUnUsuarioNoExistenteNoDebenRealizarseCambios(){
        //Given
        StubDataSourceRepository2 stubDataSourceRepository2 = new StubDataSourceRepository2();
        User existingUser = new User(1, "ExistingName", "ExistingSurname", "ExistingDNI", "ExistingEmail");
        stubDataSourceRepository2.save(existingUser);
        updateUserUseCase = new UpdateUserUseCase(new UserDataRepository(stubDataSourceRepository2));

        // Crear un nuevo usuario con los datos actualizados y un ID diferente
        User updatedUser = new User(2, "NewName", "NewSurname", "NewDNI", "NewEmail");

        //When
        updateUserUseCase.execute(updatedUser);

        //Then
        //Verificar que el usuario existente no haya sido modificado
        User retrievedExistingUser = stubDataSourceRepository2.findById(1);
        Assertions.assertEquals(existingUser, retrievedExistingUser, "El usuario existente no debería haber sido modificado");

    }
}