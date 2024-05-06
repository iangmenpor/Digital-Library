package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubDataSourceRepository2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteUserUseCaseTest {

    private DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        deleteUserUseCase = null;
    }
    @Test
    public void cuandoSePoneIdDeUsuarioExistenteElUsuarioSeraBorradoDeMemoria(){
        //Given
        User userToDelete = new User(1, "TestName", "TestSurname", "TestDNI", "TestEmail");
        StubDataSourceRepository2 stubDataSourceRepository2 = new StubDataSourceRepository2();
        stubDataSourceRepository2.save(userToDelete);
        deleteUserUseCase = new DeleteUserUseCase(new UserDataRepository(stubDataSourceRepository2));

        //When
        deleteUserUseCase.execute(1);

        //Then
        User deletedUser = stubDataSourceRepository2.findById(1);
        Assertions.assertNull(deletedUser);
    }

    @Test
    public void cuandoSePoneIdDeUsuarioNoExistenteNoSeBorraraNada(){
        //Given
        User userNotToDelete = new User(1, "TestName", "TestSurname", "TestDNI", "TestEmail");
        StubDataSourceRepository2 stubDataSourceRepository2 = new StubDataSourceRepository2();
        stubDataSourceRepository2.save(userNotToDelete);
        deleteUserUseCase = new DeleteUserUseCase(new UserDataRepository(stubDataSourceRepository2));

        //When
        deleteUserUseCase.execute(100); //Suponiendo que Usuario con ID 100 no existe

        //Then
        User notDeletedUser = stubDataSourceRepository2.findById(1);
        Assertions.assertNotNull(notDeletedUser);
    }
}