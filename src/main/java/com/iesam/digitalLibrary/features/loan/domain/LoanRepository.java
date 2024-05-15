package com.iesam.digitalLibrary.features.loan.domain;

import java.util.List;

public interface LoanRepository {

    void saveLoan(Loan model);
    void deleteLoan(Integer id);
    Loan getLoan(Integer id);
    List<Loan> getCompletedLoans();
    List<Loan> getOngoingLoans();
    void updateLoan(Loan model);
}
