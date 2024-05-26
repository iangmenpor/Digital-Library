package com.iesam.digitalLibrary.features.loan.data.local;



import com.iesam.digitalLibrary.features.loan.domain.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LoanMemLocalDataSource implements LoanDataSourceRepository{

    private final Map<String, Loan> dataStore = new TreeMap<>();
    private static LoanMemLocalDataSource instance = null;


    private LoanMemLocalDataSource(){}
    public static LoanMemLocalDataSource getInstance(){
        if (instance==null){
            instance = new LoanMemLocalDataSource();
        }
        return instance;
    }
    @Override
    public void save(Loan model) {
        dataStore.put(model.id.toString(), model);
    }
    @Override
    public void saveList(List<Loan> models) {
        for (Loan demo : models) {
            save(demo);
        }
    }

    @Override
    public Loan findById(Integer id) {
        return dataStore.get(id.toString());
    }

    public List<Loan> findAll() {
        return dataStore.values().stream().toList();
    }

    @Override
    public void delete(Integer modelId) {
        dataStore.remove(modelId.toString());
    }
    @Override
    public void update(Loan model) {
        String productId = model.id.toString();
        if (dataStore.containsKey(productId)) {
            dataStore.put(productId, model);
        } else {
            System.out.println("<!> Préstamo no encontrado para actualizar.");
        }
    }
    @Override
    public List<Loan> findCompletedLoans() {
        List<Loan> completedLoans = new ArrayList<>();
        for (Loan loan : dataStore.values()) {
            if (loan.isReturned()) {
                completedLoans.add(loan);
            }
        }
        return completedLoans;
    }
    @Override
    public List<Loan> findOngoingLoans() {
        List<Loan> ongoingLoans = new ArrayList<>();
        for (Loan loan : dataStore.values()) {
            if (!loan.isReturned()) {
                ongoingLoans.add(loan);
            }
        }
        return ongoingLoans;
    }
}
