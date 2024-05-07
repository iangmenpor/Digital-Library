package com.iesam.digitalLibrary.features.digitalProduct.domain;

public class SaveDigitalProductUseCase {

    private final DigitalProductRepository digitalProductRepository;

    public SaveDigitalProductUseCase(DigitalProductRepository digitalProductRepository) {
        this.digitalProductRepository = digitalProductRepository;
    }

    public void execute(DigitalProduct model){
        digitalProductRepository.saveDigitalProduct(model);
    }
}
