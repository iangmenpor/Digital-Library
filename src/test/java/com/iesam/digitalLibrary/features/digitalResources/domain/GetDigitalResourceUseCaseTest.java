package com.iesam.digitalLibrary.features.digitalResources.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDigitalResourceUseCaseTest {

    @Mock
    private DigitalResourceRepository<DigitalResource> digitalResourceRepository;

    private GetDigitalResourceUseCase<DigitalResource> getDigitalResourceUseCase;

    @BeforeEach
    void setUp() {
        getDigitalResourceUseCase = new GetDigitalResourceUseCase<>(digitalResourceRepository);
    }

    @AfterEach
    void tearDown() {
        getDigitalResourceUseCase = null;
    }

    @Test
    public void cuandoSeBuscaIdDeUnProductoDigitalExistenteDebeDevolverUsuarioBuscado(){
        //Given
        DigitalResource expectedProduct = new EBook(1, "TestISBN", "TestTitle3",
                "TestAuthor3","TestDesc" , "TestNumPages", "TestFormat3");
        Mockito.when(digitalResourceRepository.getDigitalResource(1)).thenReturn(expectedProduct);

        //When
        DigitalResource actualProduct = getDigitalResourceUseCase.execute(1);

        //Then
        Assertions.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void cuandoSeBuscaIdDeProductoDigitalNoExistenteDarNull(){
        //Given
        Mockito.when(digitalResourceRepository.getDigitalResource(-999)).thenReturn(null);

        //When
        DigitalResource actualProduct = getDigitalResourceUseCase.execute(-999);

        //Then
        Assertions.assertNull(actualProduct, "No debió recuperarse ningún producto");
    }
}