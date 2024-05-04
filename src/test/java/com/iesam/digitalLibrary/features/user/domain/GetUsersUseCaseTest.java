package com.iesam.digitalLibrary.features.user.domain;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.StubDataSourceRepository2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class GetUsersUseCaseTest {

    private GetUsersUseCase getUsersUseCase;
    @BeforeEach
    void setUp() {
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
        StubDataSourceRepository2 stubDataSourceRepository2 = new StubDataSourceRepository2();
        stubDataSourceRepository2.saveList(expectedUsers);
        getUsersUseCase = new GetUsersUseCase(new UserDataRepository(stubDataSourceRepository2));

        //When
        List<User> actualUsers = getUsersUseCase.execute();

        //Then
        Assertions.assertEquals(expectedUsers.size() , actualUsers.size());

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