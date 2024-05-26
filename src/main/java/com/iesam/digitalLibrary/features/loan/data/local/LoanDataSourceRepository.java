package com.iesam.digitalLibrary.features.loan.data.local;

import com.iesam.digitalLibrary.features.loan.domain.Loan;

import java.util.List;

public interface LoanDataSourceRepository {

    void save(Loan model);
    void delete(Integer id);
    Loan findById(Integer id);
    List<Loan> findOngoingLoans();
    List<Loan> findCompletedLoans();
    void update(Loan model);
    void saveList(List<Loan> models);
}
