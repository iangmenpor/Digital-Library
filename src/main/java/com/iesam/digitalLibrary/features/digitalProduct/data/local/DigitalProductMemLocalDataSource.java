package com.iesam.digitalLibrary.features.digitalProduct.data.local;

import com.iesam.digitalLibrary.features.digitalProduct.domain.DigitalProduct;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DigitalProductMemLocalDataSource implements DigitalProductDataSourceRepository {

    private final Map<String, DigitalProduct> dataStore = new TreeMap<>();
    private static DigitalProductMemLocalDataSource instance = null;


    private DigitalProductMemLocalDataSource(){}
    public static DigitalProductMemLocalDataSource getInstance(){
        if (instance==null){
            instance = new DigitalProductMemLocalDataSource();
        }
        return instance;
    }
    @Override
    public void save(DigitalProduct model) {
        dataStore.put(model.id.toString(), model);
    }
    @Override
    public void saveList(List<DigitalProduct> models) {
        for (DigitalProduct demo : models) {
            save(demo);
        }
    }
    @Override
    public DigitalProduct findById(Integer id) {
        return dataStore.get(id.toString());
    }
    @Override
    public List<DigitalProduct> findAll() {
        return dataStore.values().stream().toList();
    }
    @Override
    public void delete(Integer modelId) {
        dataStore.remove(modelId.toString());
    }
    @Override
    public void update(DigitalProduct model) {
        String productId = model.id.toString();
        if (dataStore.containsKey(productId)) {
            dataStore.put(productId, model); // Actualizar el producto en el mapa
        } else {
            System.out.println("<!> Producto no encontrado para actualizar.");
        }
    }

}
