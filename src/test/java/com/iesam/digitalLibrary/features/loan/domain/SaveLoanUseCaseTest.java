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

@ExtendWith(MockitoExtension.class)
class SaveLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;
    private SaveLoanUseCase saveLoanUseCase;
    @BeforeEach
    void setUp() {
        saveLoanUseCase = new SaveLoanUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        saveLoanUseCase = null;
    }

    @Test
    public void reciboUnPrestamoYLoGuardoEnMemoria() {
        //Given
        ArrayList<DigitalResource> resources = new ArrayList<>();
        resources.add(new EBook(1, "TestISBN1", "TestTitle1", "TestAuthor1", "TestDesc1","TestNumPages1", "TestFormat1"));
        resources.add(new EBook(2, "TestISBN2", "TestTitle2", "TestAuthor2","TestDesc2", "TestNumPages2", "TestFormat2"));
        resources.add(new EBook(3, "TestISBN3", "TestTitle3", "TestAuthor3","TestDesc3", "TestNumPages3", "TestFormat3"));
        User user = new User(1,"NameTest","SurnameTest","DNITest","EmailTest");

        Loan loanToSave = new Loan(1,user, resources, null);

        //When
        saveLoanUseCase.execute(loanToSave);

        //Then
        Mockito.verify(loanRepository, Mockito.times(1)).saveLoan(loanToSave);
    }
}