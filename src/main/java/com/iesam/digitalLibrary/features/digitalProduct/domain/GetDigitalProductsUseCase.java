package com.iesam.digitalLibrary.features.digitalProduct.domain;

import java.util.List;

public class GetDigitalProductsUseCase {

    private final DigitalProductRepository digitalProductRepository;

    public GetDigitalProductsUseCase(DigitalProductRepository digitalProductRepository) {
        this.digitalProductRepository = digitalProductRepository;
    }

    public List<DigitalProduct> execute(){
        return digitalProductRepository.getDigitalProducts();
    }
}
