package com.iesam.digitalLibrary.features.digitalProduct.data.local;


import com.iesam.digitalLibrary.features.digitalProduct.domain.DigitalProduct;

import java.util.List;

public interface DigitalProductDataSourceRepository {

    void save (DigitalProduct model);
    DigitalProduct findById(Integer modelId);
    void delete(Integer modelId);
    List<DigitalProduct> findAll();
    void saveList(List<DigitalProduct> models);
    void update(DigitalProduct model);
}
