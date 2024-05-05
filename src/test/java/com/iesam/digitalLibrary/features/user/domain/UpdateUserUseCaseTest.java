package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.StubUserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubUserMemLocalDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateUserUseCaseTest {

    private UpdateUserUseCase updateUserUseCase;
    private StubUserDataRepository stubUserDataRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        updateUserUseCase = null;
        stubUserDataRepository = null;
    }

    @Test
    public void cuandoSeActualizanLosDatosDeUsuarioExistenteDebenRealizarseLosCambios(){
        //Given
        User existingUser = new User(1, "OldName", "OldSurname", "OldDNI", "OldEmail");
        stubUserDataRepository = new StubUserDataRepository(StubUserMemLocalDataSource.getInstance());
        stubUserDataRepository.saveUser(existingUser);

        User updatedUser = new User(1, "NewName", "NewSurname", "NewDNI", "NewEmail");
        updateUserUseCase = new UpdateUserUseCase(stubUserDataRepository);

        //When
        updateUserUseCase.execute(updatedUser);

        //Then
        User retrievedUser = stubUserDataRepository.getUser(1);

        Assertions.assertEquals(updatedUser.id , retrievedUser.id);
        Assertions.assertEquals(updatedUser.surname, retrievedUser.surname);
        Assertions.assertEquals(updatedUser.dni, retrievedUser.dni);
        Assertions.assertEquals(updatedUser.email , retrievedUser.email);

    }

    @Test
    public void cuandoSeIntentaActualizarLosDatosDeUnUsuarioNoExistenteNoDebenRealizarseCambios(){
        //Given
        User existingUser = new User(
                1,
                "ExistingName",
                "ExistingSurname",
                "ExistingDNI"
                ,"ExistingEmail");

        stubUserDataRepository = new StubUserDataRepository(StubUserMemLocalDataSource.getInstance());
        stubUserDataRepository.saveUser(existingUser);
        updateUserUseCase = new UpdateUserUseCase(stubUserDataRepository);

        // Crear un nuevo usuario con los datos actualizados y un ID diferente
        User updatedUser = new User(2, "NewName", "NewSurname", "NewDNI", "NewEmail");

        //When
        updateUserUseCase.execute(updatedUser);

        //Then
        //Verificar que el usuario existente no haya sido modificado
        User retrievedExistingUser = stubUserDataRepository.getUser(1);
        Assertions.assertEquals(existingUser, retrievedExistingUser, "El usuario no ha sido modificado");

    }
}