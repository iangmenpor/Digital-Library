package com.iesam.digitalLibrary.features.digitalProduct.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class SaveDigitalProductUseCaseTest {

    @Mock
    private DigitalProductRepository productRepository;

    private SaveDigitalProductUseCase saveDigitalProductUseCase;

    @BeforeEach
    void setUp() {
        saveDigitalProductUseCase = new SaveDigitalProductUseCase(productRepository);
    }

    @AfterEach
    void tearDown() {
        saveDigitalProductUseCase = null;
    }

    @Test
    public void reciboUnProductoDigitalYLoGuardoEnMemoria(){
        //Given
        DigitalProduct ProductToSave = new EBook(1,"1234", "Niebla", "Miguel de Unamuno",
                "176", "ePUB");

        //When
        saveDigitalProductUseCase.execute(ProductToSave);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).saveDigitalProduct(ProductToSave);
    }
}