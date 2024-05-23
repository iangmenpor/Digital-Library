package com.iesam.digitalLibrary.features.user.domain;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;
    private UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    void setUp() {
        updateUserUseCase = new UpdateUserUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        updateUserUseCase = null;
    }

    @Test
    public void cuandoRecibeUsuarioLlamaAlMetodo(){
        //Given
        User user = new User(1, "TestName", "OldSurname", "OldDNI", "OldEmail");

        //When
        updateUserUseCase.execute(user);

        //Then
        Mockito.verify(userRepository, Mockito.times(1)).updateUser(user);
    }
}