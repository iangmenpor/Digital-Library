package com.iesam.digitalLibrary.features.digitalProduct.domain;

public interface DigitalProductRepository {

    void saveDigitalProduct(DigitalProduct model);
    DigitalProduct getDigitalProduct(Integer id);
    void deleteDigitalProduct(Integer id);
}
