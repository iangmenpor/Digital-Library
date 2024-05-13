package com.iesam.digitalLibrary.features.loan.domain;

public class GetLoanUseCase {

    private final LoanRepository loanRepository;

    public GetLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan execute(Integer id){
        return loanRepository.getLoan(id);
    }
}
