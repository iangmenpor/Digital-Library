package com.iesam.digitalLibrary.features.loan.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;


@ExtendWith(MockitoExtension.class)
class UpdateLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;
    private UpdateLoanUseCase updateLoanUseCase;

    @BeforeEach
    void setUp() {
        updateLoanUseCase = new UpdateLoanUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        updateLoanUseCase = null;
    }

    @Test
    public void cuandoSePasaModeloDePrestamoActualizadoLLamaAMetodoParaActualizarLosDatos() {
        //Given
        Loan newLoan =  new Loan (1,null, null, new Date());

        //When
        updateLoanUseCase.execute(newLoan);

        //Then
        Mockito.verify(loanRepository, Mockito.times(1)).updateLoan(newLoan);

    }
}