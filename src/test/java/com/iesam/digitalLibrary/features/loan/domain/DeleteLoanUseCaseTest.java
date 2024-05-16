package com.iesam.digitalLibrary.features.loan.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    private DeleteLoanUseCase deleteLoanUseCase;

    @BeforeEach
    void setUp() {
        deleteLoanUseCase = new DeleteLoanUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        deleteLoanUseCase = null;
    }

    @Test
    public void dadoIDExistenteEliminarPrestamoConEseID() {
        // Given
        int validID = 1;

        // When
        deleteLoanUseCase.execute(validID);

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).deleteLoan(validID);
    }

}