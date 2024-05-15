package com.iesam.digitalLibrary.features.loan.domain;

import java.util.List;

public class GetOngoingLoansUseCase {

    private final LoanRepository loanRepository;

    public GetOngoingLoansUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> execute(){
        return loanRepository.getOngoingLoans();
    }
}
