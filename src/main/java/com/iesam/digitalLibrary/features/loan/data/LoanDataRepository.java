package com.iesam.digitalLibrary.features.loan.data;

import com.iesam.digitalLibrary.features.loan.data.local.LoanDataSourceRepository;
import com.iesam.digitalLibrary.features.loan.domain.Loan;
import com.iesam.digitalLibrary.features.loan.domain.LoanRepository;

import java.util.List;

public class LoanDataRepository implements LoanRepository {

    private final LoanDataSourceRepository dataSourceRepository;

    public LoanDataRepository(LoanDataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public void saveLoan(Loan model) {
        dataSourceRepository.save(model);
    }

    @Override
    public void deleteLoan(Integer id) {
        dataSourceRepository.delete(id);
    }

    @Override
    public Loan getLoan(Integer id) {
        return dataSourceRepository.findById(id);
    }

    @Override
    public List<Loan> getCompletedLoans() {
        return dataSourceRepository.findCompletedLoans();
    }

    @Override
    public List<Loan> getOngoingLoans() {
        return dataSourceRepository.findOngoingLoans();
    }

    @Override
    public void updateLoan(Loan model) {
        dataSourceRepository.update(model);
    }
}
