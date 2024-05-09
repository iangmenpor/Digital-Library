package com.iesam.digitalLibrary.features.digitalResources.data.local;


import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;

import java.util.List;

public interface EBookDataSourceRepository {

    void save (EBook model);
    EBook findById(Integer modelId);
    void delete(Integer modelId);
    List<EBook> findAll();
    void saveList(List<EBook> models);
    void update(EBook model);
}
