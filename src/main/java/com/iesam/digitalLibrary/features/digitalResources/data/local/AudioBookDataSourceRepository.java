package com.iesam.digitalLibrary.features.digitalResources.data.local;

import com.iesam.digitalLibrary.features.digitalResources.domain.AudioBook;

import java.util.List;

public interface AudioBookDataSourceRepository {
    void save(AudioBook model);

    void saveList(List<AudioBook> models);

    AudioBook findById(Integer id);

    List<AudioBook> findAll();

    void delete(Integer modelId);

    void update(AudioBook model);
}
