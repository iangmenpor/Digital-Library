package com.iesam.digitalLibrary.features.digitalResources.data.local;

import com.iesam.digitalLibrary.features.digitalResources.domain.AudioBook;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AudioBookMemLocalDataSource implements AudioBookDataSourceRepository {

    private final Map<String, AudioBook> dataStore = new TreeMap<>();
    private static AudioBookMemLocalDataSource instance = null;


    private AudioBookMemLocalDataSource() {
    }

    public static AudioBookMemLocalDataSource getInstance() {
        if (instance == null) {
            instance = new AudioBookMemLocalDataSource();
        }
        return instance;
    }

    @Override
    public void save(AudioBook model) {
        dataStore.put(model.id.toString(), model);
    }

    @Override
    public void saveList(List<AudioBook> models) {
        for (AudioBook demo : models) {
            save(demo);
        }
    }

    @Override
    public AudioBook findById(Integer id) {
        return dataStore.get(id.toString());
    }

    @Override
    public List<AudioBook> findAll() {
        return dataStore.values().stream().toList();
    }

    @Override
    public void delete(Integer modelId) {
        dataStore.remove(modelId.toString());
    }

    @Override
    public void update(AudioBook model) {
        String productId = model.id.toString();
        if (dataStore.containsKey(productId)) {
            dataStore.put(productId, model); // Actualizar el producto en el mapa
        } else {
            System.out.println("<!> AudioBook no encontrado para actualizar.");
        }
    }
}
