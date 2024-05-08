package com.iesam.digitalLibrary.features.digitalProduct.domain;

public class DeleteDigitalProductUseCase {

    private final DigitalProductRepository digitalProductRepository;

    public DeleteDigitalProductUseCase(DigitalProductRepository digitalProductRepository) {
        this.digitalProductRepository = digitalProductRepository;
    }

    public void execute(Integer id){
        digitalProductRepository.deleteDigitalProduct(id);
    }
}
