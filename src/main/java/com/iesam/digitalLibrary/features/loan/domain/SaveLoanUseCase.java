package com.iesam.digitalLibrary.features.loan.domain;

public class SaveLoanUseCase {

    private final LoanRepository loanRepository;

    public SaveLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(Loan model){
        loanRepository.saveLoan(model);
    }
}
