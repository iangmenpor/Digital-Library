package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.StubUserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubUserMemLocalDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GetUserUseCaseTest {

    private GetUserUseCase getUserUseCase;
    private StubUserDataRepository stubUserDataRepository;
  
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        getUserUseCase = null;
        stubUserDataRepository = null;
    }

    @Test
    public void cuandoSeBuscaIdDeUsuarioExistenteDebeDevolverUsuarioBuscado(){
        //Given
        stubUserDataRepository = new StubUserDataRepository(StubUserMemLocalDataSource.getInstance());
        User expectedUser = new User(1,"NameTest","SurnameTest","DNITest","EmailTest");
        stubUserDataRepository.saveUser(expectedUser);
        getUserUseCase = new GetUserUseCase(stubUserDataRepository);

        //When
        User actualUser = getUserUseCase.execute(1);

        //Then
        Assertions.assertEquals(expectedUser.id, actualUser.id);
        Assertions.assertEquals(expectedUser.name, actualUser.name);
        Assertions.assertEquals(expectedUser.surname, actualUser.surname);
        Assertions.assertEquals(expectedUser.dni, actualUser.dni);
        Assertions.assertEquals(expectedUser.email, actualUser.email);
    }

    @Test
    public void cuandoSeBuscaIdDeUsuarioNoExistenteDarNull(){
        //Given
        stubUserDataRepository = new StubUserDataRepository(StubUserMemLocalDataSource.getInstance());
        getUserUseCase = new GetUserUseCase(stubUserDataRepository);

        //When
        User actualUser = getUserUseCase.execute(-1); //Suponiendo que no existe id -1

        //Then
        Assertions.assertNull(actualUser, "No debió haberse recuperado ningún usuario.");
    }
}