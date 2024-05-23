package com.iesam.digitalLibrary.features.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseTest {

    @Mock
    private UserRepository userRepository;
    private GetUsersUseCase getUsersUseCase;

    @BeforeEach
    void setUp() {
        getUsersUseCase = new GetUsersUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        getUsersUseCase = null;
    }

    @Test
    public void cuandoSeSolicitaLaListaDeUsuariosSeDevuelveCorrectamente(){
        //Given
        List<User> expectedUsers = Arrays.asList(
                new User(1, "TestName1", "TestSurname1", "TestDNI1", "TestEmail1"),
                new User(2, "TestName2", "TestSurname2", "TestDNI2", "TestEmail2"),
                new User(3, "TestName3", "TestSurname3", "TestDNI3", "TestEmail3")
        );
        Mockito.when(getUsersUseCase.execute()).thenReturn(expectedUsers);
        //When
        List<User> actualUsers = getUsersUseCase.execute();
        //Then
        Assertions.assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            Assertions.assertEquals(expectedUsers.get(i), actualUsers.get(i));
        }
    }
}