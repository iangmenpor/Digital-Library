package com.iesam.digitalLibrary.features.loan.data;

import com.iesam.digitalLibrary.features.loan.data.local.LoanDataSourceRepository;
import com.iesam.digitalLibrary.features.loan.domain.Loan;
import com.iesam.digitalLibrary.features.loan.domain.LoanRepository;

public class LoanDataRepository implements LoanRepository {

    private final LoanDataSourceRepository dataSourceRepository;

    public LoanDataRepository(LoanDataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public void saveLoan(Loan model) {
        dataSourceRepository.save(model);
    }
<<<<<<< feature/42/alta_prestamo
=======

    @Override
    public Loan getLoan(Integer id) {
        return dataSourceRepository.findById(id);
    }
>>>>>>> master
}
