package com.iesam.digitalLibrary.features.user.domain;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {

    @Mock
    private UserRepository userRepository;
    private DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    void setUp() {
        deleteUserUseCase = new DeleteUserUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        deleteUserUseCase = null;
    }
    @Test
    public void cuandoSePoneIdDeUsuarioLLamaraAlMetodoDeBorrado(){
        //Given
        Integer userId = 1;
        //When
        deleteUserUseCase.execute(userId);
        //Then
        Mockito.verify(userRepository, Mockito.times(1)).deleteUser(userId);

    }
}