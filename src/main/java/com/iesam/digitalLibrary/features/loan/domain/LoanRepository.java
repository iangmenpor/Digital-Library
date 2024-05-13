package com.iesam.digitalLibrary.features.loan.domain;

public interface LoanRepository {

    void saveLoan(Loan model);
    void deleteLoan(Integer id);
    Loan getLoan(Integer id);
}
