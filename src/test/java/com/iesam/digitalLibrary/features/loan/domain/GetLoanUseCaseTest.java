package com.iesam.digitalLibrary.features.loan.domain;

import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;
import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;
import com.iesam.digitalLibrary.features.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GetLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;
    private GetLoanUseCase getLoanUseCase;

    @BeforeEach
    void setUp() {
        getLoanUseCase = new GetLoanUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        getLoanUseCase = null;
    }

    @Test
    public void  cuandoSeBuscaIdDeUnPrestamoExistenteDebeDevolverPrestamo() {
        //Given
        ArrayList<DigitalResource> resources = new ArrayList<>();
        resources.add(new EBook(1, "TestISBN1", "TestTitle1", "TestAuthor1","TestDesc1" ,"TestNumPages1", "TestFormat1"));
        resources.add(new EBook(2, "TestISBN2", "TestTitle2", "TestAuthor2","TestDesc2" ,"TestNumPages2", "TestFormat2"));
        resources.add(new EBook(3, "TestISBN3", "TestTitle3", "TestAuthor3","TestDesc3", "TestNumPages3", "TestFormat3"));
        User user = new User(1,"NameTest","SurnameTest","DNITest","EmailTest");

        Loan expectedLoan = new Loan(1,user, resources, null);
        Mockito.when(loanRepository.getLoan(1)).thenReturn(expectedLoan);

        //When
        Loan actualLoan = getLoanUseCase.execute(1);

        //Then
        assertEquals(expectedLoan, actualLoan);
    }

    @Test
    public void cuandoSeBuscaIdDePrestamoNoExistenteDevolverNull(){
        //Give
        Mockito.when(loanRepository.getLoan(-999)).thenReturn(null);

        //When
        Loan actualLoan = getLoanUseCase.execute(-999);

        //Then
        assertNull(actualLoan);
    }
}