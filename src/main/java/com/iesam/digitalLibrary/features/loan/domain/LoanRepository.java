package com.iesam.digitalLibrary.features.loan.domain;

public interface LoanRepository {

    void saveLoan(Loan model);

    Loan getLoan(Integer id);
}
