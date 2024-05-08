package com.iesam.digitalLibrary.features.digitalProduct.domain;

public class GetDigitalProductUseCase {

    private final DigitalProductRepository digitalProductRepository;

    public GetDigitalProductUseCase(DigitalProductRepository digitalProductRepository) {
        this.digitalProductRepository = digitalProductRepository;
    }

    public DigitalProduct execute(Integer id){
        return digitalProductRepository.getDigitalProduct(id);
    }
}
