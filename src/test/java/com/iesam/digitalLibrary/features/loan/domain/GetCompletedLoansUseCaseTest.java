package com.iesam.digitalLibrary.features.loan.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GetCompletedLoansUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    private GetCompletedLoansUseCase getCompletedLoansUseCase;

    @BeforeEach
    void setUp() {
        getCompletedLoansUseCase = new GetCompletedLoansUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        getCompletedLoansUseCase = null;
    }

    @Test
    public void cuandoSeEjecutaDevolverListaDePrestamosConFechaDeDevolucion() {
        // Given
        List<Loan> allLoans = new ArrayList<>();
        allLoans.add(new Loan(1, null, null, new Date()));
        allLoans.add(new Loan(2, null, null, new Date()));
        allLoans.add(new Loan(3, null, null, null));

        List<Loan> completedLoans = new ArrayList<>();
        completedLoans.add(allLoans.get(0));
        completedLoans.add(allLoans.get(1));

        Mockito.when(loanRepository.getCompletedLoans()).thenReturn(completedLoans);

        // When
        List<Loan> result = getCompletedLoansUseCase.execute();

        // Then
        assertEquals(result.size(), 2);
        for (Loan loan : result) {
            assertNotNull(loan.returnDate);
        }
    }
}