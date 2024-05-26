package com.iesam.digitalLibrary.features.loan.data;

import com.iesam.digitalLibrary.features.loan.data.local.LoanDataSourceRepository;
import com.iesam.digitalLibrary.features.loan.data.local.LoanMemLocalDataSource;
import com.iesam.digitalLibrary.features.loan.domain.Loan;
import com.iesam.digitalLibrary.features.loan.domain.LoanRepository;

import java.util.List;

public class LoanDataRepository implements LoanRepository {

    private final LoanDataSourceRepository memLocalDataSource;
    private final LoanDataSourceRepository loanFileLocalDataSource;

    public LoanDataRepository(LoanDataSourceRepository loanFileLocalDataSource) {
        this.memLocalDataSource = LoanMemLocalDataSource.getInstance();
        this.loanFileLocalDataSource = loanFileLocalDataSource;
    }

    @Override
    public void saveLoan(Loan model) {
        loanFileLocalDataSource.save(model);
    }

    @Override
    public void deleteLoan(Integer id) {
        memLocalDataSource.delete(id);
        loanFileLocalDataSource.delete(id);
    }

    @Override
    public Loan getLoan(Integer id) {
        Loan loan = memLocalDataSource.findById(id); // 1º Buscar en memoria
        if (loan != null) {
            return loan;
        }
        loan = loanFileLocalDataSource.findById(id); // 2ª Si no está en mem, buscar en fichero
        if (loan != null) {
            memLocalDataSource.save(loan); // Guardar en mem si lo encuentra
        }
        return loan;
    }

    @Override
    public List<Loan> getCompletedLoans() {
        List<Loan> completedLoans = memLocalDataSource.findCompletedLoans();
        List<Loan> fileCompletedLoans = loanFileLocalDataSource.findCompletedLoans();
        if (!completedLoans.isEmpty() && completedLoans.size() == fileCompletedLoans.size()) {
            return completedLoans;
        }
        completedLoans = fileCompletedLoans;
        if (completedLoans != null && !completedLoans.isEmpty()) {
            memLocalDataSource.saveList(completedLoans);
        }
        return completedLoans;
    }

    @Override
    public List<Loan> getOngoingLoans() {
        List<Loan> ongoingLoans = memLocalDataSource.findOngoingLoans();
        List<Loan> fileOngoingLoans = loanFileLocalDataSource.findOngoingLoans();
        if (!ongoingLoans.isEmpty() && ongoingLoans.size() == fileOngoingLoans.size()) {
            return ongoingLoans;
        }
        ongoingLoans = fileOngoingLoans;
        if (ongoingLoans != null && !ongoingLoans.isEmpty()) {
            memLocalDataSource.saveList(ongoingLoans);
        }
        return ongoingLoans;
    }

    @Override
    public void updateLoan(Loan model) {
        memLocalDataSource.update(model);
        loanFileLocalDataSource.update(model);
    }
}
