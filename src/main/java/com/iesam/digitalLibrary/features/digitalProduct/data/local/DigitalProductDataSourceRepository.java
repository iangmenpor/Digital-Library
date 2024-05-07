package com.iesam.digitalLibrary.features.digitalProduct.data.local;


import com.iesam.digitalLibrary.features.digitalProduct.domain.DigitalProduct;

public interface DigitalProductDataSourceRepository {

    void save (DigitalProduct model);
}
