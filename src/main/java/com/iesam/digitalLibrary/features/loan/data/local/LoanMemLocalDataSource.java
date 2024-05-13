package com.iesam.digitalLibrary.features.loan.data.local;



import com.iesam.digitalLibrary.features.loan.domain.Loan;

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

    public void delete(Integer modelId) {
        dataStore.remove(modelId.toString());
    }

    public void update(Loan model) {
        String productId = model.id.toString();
        if (dataStore.containsKey(productId)) {
            dataStore.put(productId, model); // Actualizar el producto en el mapa
        } else {
            System.out.println("<!> Producto no encontrado para actualizar.");
        }
    }

}
