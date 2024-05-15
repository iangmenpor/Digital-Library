package com.iesam.digitalLibrary.features.loan.domain;

import java.util.List;

public class GetCompletedLoansUseCase {

    private final LoanRepository loanRepository;

    public GetCompletedLoansUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> execute(){
        return loanRepository.getCompletedLoans();
    }
}
