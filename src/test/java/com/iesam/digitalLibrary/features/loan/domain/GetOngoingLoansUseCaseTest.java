package com.iesam.digitalLibrary.features.loan.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GetOngoingLoansUseCaseTest {

    @Mock
    private LoanRepository loanRepository;
    private GetOngoingLoansUseCase getOngoingLoansUseCase;

    @BeforeEach
    void setUp() {
        getOngoingLoansUseCase = new GetOngoingLoansUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        getOngoingLoansUseCase = null;
    }

    @Test
    public void trasEjecutarDevolverListaDePrestamosSinFechaDeEntrega(){
        //Given
        List<Loan> allLoans = Arrays.asList(
                new Loan(1, null, null, null),
                new Loan(2, null, null, new Date()),
                new Loan(3, null, null, null)
        );

        List<Loan> ongoingLoans = allLoans.stream()
                .filter(loan -> loan.returnDate == null)
                .collect(Collectors.toList());

        Mockito.when(loanRepository.getOngoingLoans()).thenReturn(ongoingLoans);

        // When
        List<Loan> result = getOngoingLoansUseCase.execute();

        // Then
        assertEquals(result.size(), 2);
        for (Loan loan : result) {
            assertNull(loan.returnDate);
        }
    }
}