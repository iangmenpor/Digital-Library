package com.iesam.digitalLibrary.features.digitalResources.data.local;


import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EbookMemLocalDataSource implements EBookDataSourceRepository {

    private final Map<String, EBook> dataStore = new TreeMap<>();
    private static EbookMemLocalDataSource instance = null;


    private EbookMemLocalDataSource(){}
    public static EbookMemLocalDataSource getInstance(){
        if (instance==null){
            instance = new EbookMemLocalDataSource();
        }
        return instance;
    }
    @Override
    public void save(EBook model) {
        dataStore.put(model.id.toString(), model);
    }
    @Override
    public void saveList(List<EBook> models) {
        for (EBook demo : models) {
            save(demo);
        }
    }
    @Override
    public EBook findById(Integer id) {
        return dataStore.get(id.toString());
    }
    @Override
    public List<EBook> findAll() {
        return dataStore.values().stream().toList();
    }
    @Override
    public void delete(Integer modelId) {
        dataStore.remove(modelId.toString());
    }
    @Override
    public void update(EBook model) {
        String productId = model.id.toString();
        if (dataStore.containsKey(productId)) {
            dataStore.put(productId, model); // Actualizar el producto en el mapa
        } else {
            System.out.println("<!> Producto no encontrado para actualizar.");
        }
    }

}
