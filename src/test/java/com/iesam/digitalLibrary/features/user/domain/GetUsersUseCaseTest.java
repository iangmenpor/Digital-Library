package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.StubUserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubUserMemLocalDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class GetUsersUseCaseTest {

    private GetUsersUseCase getUsersUseCase;
    private StubUserDataRepository stubUserDataRepository;
  
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        getUsersUseCase = null;
        stubUserDataRepository = null;
    }

    @Test
    public void cuandoSeSolicitaLaListaDeUsuariosSeDevuelveCorrectamente(){
        //Given
        List<User> expectedUsers = Arrays.asList(
                new User(1, "TestName1", "TestSurname1", "TestDNI1", "TestEmail1"),
                new User(2, "TestName2", "TestSurname2", "TestDNI2", "TestEmail2"),
                new User(3, "TestName3", "TestSurname3", "TestDNI3", "TestEmail3")
        );
      
        stubUserDataRepository = new StubUserDataRepository(StubUserMemLocalDataSource.getInstance());
        getUsersUseCase = new GetUsersUseCase(stubUserDataRepository);

        for (User users : expectedUsers){ stubUserDataRepository.saveUser(users);}

        //When
        List<User> actualUsers = getUsersUseCase.execute();

        //Then
        Assertions.assertEquals(expectedUsers.size() , actualUsers.size(), "El tamaño debe ser el mismo.");

        for (int i = 0; i < expectedUsers.size(); i++){
            User expectedUser = expectedUsers.get(i);
            User actualUser = actualUsers.get(i);
            Assertions.assertEquals(expectedUser.id, actualUser.id);
            Assertions.assertEquals(expectedUser.name, actualUser.name);
            Assertions.assertEquals(expectedUser.surname, actualUser.surname);
            Assertions.assertEquals(expectedUser.dni, actualUser.dni);
            Assertions.assertEquals(expectedUser.email, actualUser.email);
        }
    }
}