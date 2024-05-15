package com.iesam.digitalLibrary.features.loan.domain;

public class UpdateLoanUseCase {

    private final LoanRepository loanRepository;

    public UpdateLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(Loan model){
        loanRepository.updateLoan(model);
    }
}
