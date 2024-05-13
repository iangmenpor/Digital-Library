package com.iesam.digitalLibrary.features.loan.domain;

public class DeleteLoanUseCase {

    private final LoanRepository loanRepository;

    public DeleteLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(Integer id){
        loanRepository.deleteLoan(id);
    }
}
