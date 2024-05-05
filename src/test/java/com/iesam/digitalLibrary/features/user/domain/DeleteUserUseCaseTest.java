package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.StubUserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubUserMemLocalDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteUserUseCaseTest {

    private DeleteUserUseCase deleteUserUseCase;
    private StubUserDataRepository stubUserDataRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        deleteUserUseCase = null;
        stubUserDataRepository= null;
    }
    @Test
    public void cuandoSePoneIdDeUsuarioExistenteElUsuarioSeraBorradoDeMemoria(){
        //Given
        User userToDelete = new User(1, "TestName", "TestSurname", "TestDNI", "TestEmail");
        stubUserDataRepository = new StubUserDataRepository(StubUserMemLocalDataSource.getInstance());
        stubUserDataRepository.saveUser(userToDelete);
        deleteUserUseCase = new DeleteUserUseCase(stubUserDataRepository);
        //When
        deleteUserUseCase.execute(1);

        //Then
        User deletedUser = stubUserDataRepository.getUser(1);
        Assertions.assertNull(deletedUser, "El usuario no debe existir tras ser eliminado.");
    }

    @Test
    public void cuandoSePoneIdDeUsuarioNoExistenteNoSeBorraraNada(){
        //Given
        User existingUser = new User(1, "TestName", "TestSurname", "TestDNI", "TestEmail");
        stubUserDataRepository = new StubUserDataRepository(StubUserMemLocalDataSource.getInstance());
        stubUserDataRepository.saveUser(existingUser);
        deleteUserUseCase = new DeleteUserUseCase(stubUserDataRepository);

        //When
        deleteUserUseCase.execute(-1); //Suponiendo que no existe usuario con id -1

        //Then
        User notDeletedUser = stubUserDataRepository.getUser(1);
        Assertions.assertNotNull(notDeletedUser , "El usuario no debe existir al no pasar su ID para eliminación.");
    }
}