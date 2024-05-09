package com.iesam.digitalLibrary.features.digitalProduct.domain;

public class UpdateDigitalProductUseCase {

    private final DigitalProductRepository digitalProductRepository;

    public UpdateDigitalProductUseCase(DigitalProductRepository digitalProductRepository) {
        this.digitalProductRepository = digitalProductRepository;
    }

    public void execute(DigitalProduct model){
        digitalProductRepository.updateDigitalProduct(model);
    }
}
