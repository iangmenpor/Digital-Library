package com.iesam.digitalLibrary.features.digitalProduct.domain;

import java.util.List;

public interface DigitalProductRepository {

    void saveDigitalProduct(DigitalProduct model);
    DigitalProduct getDigitalProduct(Integer id);
    void deleteDigitalProduct(Integer id);
    List<DigitalProduct> getDigitalProducts();
    void updateDigitalProduct(DigitalProduct model);
}
