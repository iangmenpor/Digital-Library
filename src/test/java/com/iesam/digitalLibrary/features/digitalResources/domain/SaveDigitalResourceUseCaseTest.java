package com.iesam.digitalLibrary.features.digitalResources.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class SaveDigitalResourceUseCaseTest {

    @Mock
    private DigitalResourceRepository productRepository;

    private SaveDigitalResourceUseCase saveDigitalResourceUseCase;

    @BeforeEach
    void setUp() {
        saveDigitalResourceUseCase = new SaveDigitalResourceUseCase(productRepository);
    }

    @AfterEach
    void tearDown() {
        saveDigitalResourceUseCase = null;
    }

    @Test
    public void reciboUnProductoDigitalYLoGuardoEnMemoria(){
        //Given
        EBook productToSave = new EBook(1,"1234", "Niebla", "Miguel de Unamuno",
                "176", "ePUB");

        //When
        saveDigitalResourceUseCase.execute(productToSave);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).saveEbook(productToSave);
    }
}