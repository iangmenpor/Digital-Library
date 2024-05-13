package com.iesam.digitalLibrary.features.loan.data.local;

import com.iesam.digitalLibrary.features.loan.domain.Loan;

public interface LoanDataSourceRepository {

    void save(Loan model);
<<<<<<< feature/42/alta_prestamo
=======

    Loan findById(Integer id);
>>>>>>> master
}
