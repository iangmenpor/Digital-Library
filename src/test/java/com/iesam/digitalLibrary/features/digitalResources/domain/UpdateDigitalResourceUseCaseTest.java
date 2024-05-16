package com.iesam.digitalLibrary.features.digitalResources.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateDigitalResourceUseCaseTest {

    @Mock
    private DigitalResourceRepository<DigitalResource> digitalResourceRepository;
    private UpdateDigitalResourceUseCase<DigitalResource> updateDigitalResourceUseCase;

    @BeforeEach
    void setUp() {
        updateDigitalResourceUseCase = new UpdateDigitalResourceUseCase<>(digitalResourceRepository);
    }

    @AfterEach
    void tearDown() {
        updateDigitalResourceUseCase = null;
    }

    @Test
    public void cuandoPasoNuevoModeloDebeLlamarAMetodoParaActualizarDeRepositorioDeControl(){
        //Given
        DigitalResource newResource= new EBook(1, "TestISBN", "TestTitle3",
                "TestAuthor3", "TestNumPages", "TestFormat3");

        //When
        updateDigitalResourceUseCase.execute(newResource);

        //Then
        Mockito.verify(digitalResourceRepository , Mockito.times(1)).updateDigitalResource(newResource);
    }
}