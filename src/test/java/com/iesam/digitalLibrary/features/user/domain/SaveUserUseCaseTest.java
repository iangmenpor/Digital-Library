package com.iesam.digitalLibrary.features.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveUserUseCaseTest {

    @Mock
    private UserRepository userRepository;
    private SaveUserUseCase saveUserUseCase;

    @BeforeEach
    void setUp() {
        saveUserUseCase = new SaveUserUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        saveUserUseCase = null;
    }

    @Test
    public void cuandoRecibeUsuarioEjecutaElMetodoParaGuardarUsuario() {
        //Given
        User user = new User(null, "TestName", "TestSurname", "TestEmail", "TestEmail");

        //When
        saveUserUseCase.execute(user);

        //Then
        Mockito.verify(userRepository , Mockito.times(1)).saveUser(user);
    }
}