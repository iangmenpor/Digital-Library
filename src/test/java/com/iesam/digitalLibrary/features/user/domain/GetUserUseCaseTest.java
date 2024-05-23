package com.iesam.digitalLibrary.features.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseTest {
    @Mock
    private UserRepository userRepository;
    private GetUserUseCase getUserUseCase;

    @BeforeEach
    void setUp() {
        getUserUseCase = new GetUserUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        getUserUseCase = null;
    }

    @Test
    public void cuandoSeBuscaIdDeUsuarioExistenteDebeDevolverUsuarioBuscado(){
        //Given
        User expectedUser = new User(1,"NameTest","SurnameTest","DNITest","EmailTest");
        Mockito.when(getUserUseCase.execute(1)).thenReturn(expectedUser);

        //When
        User actualUser = getUserUseCase.execute(1);

        //Then
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void cuandoSeBuscaIdDeUsuarioNoExistenteDarNull(){
        //Given
        Mockito.when(getUserUseCase.execute(Mockito.anyInt())).thenReturn(null);

        //When
        User actualUser = getUserUseCase.execute(-1); //Suponiendo que no existe id -1

        //Then
        Assertions.assertNull(actualUser, "No debió haberse recuperado ningún usuario.");
    }
}