package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubDataSourceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetUserUseCaseTest {

    private GetUserUseCase getUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        getUserUseCase = null;
    }

    @Test
    public void cuandoSeBuscaIdDeUsuarioExistenteDebeDevolverUsuarioBuscado(){
        //Given
        User expectedUser = new User(1,"NameTest","SurnTest","dniTest","emailTest");
        getUserUseCase = new GetUserUseCase(new UserDataRepository(new StubDataSourceRepository()));

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
        getUserUseCase = new GetUserUseCase(new UserDataRepository(new StubDataSourceRepository()));

        //When
        User actualUser = getUserUseCase.execute(100); //Suponiendo que el Usuario con ID 100 no existe

        //Then
        Assertions.assertNull(actualUser);
    }
}