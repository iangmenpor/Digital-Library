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
    private DigitalResourceRepository productRepository;

    private GetDigitalResourceUseCase getDigitalResourceUseCase;

    @BeforeEach
    void setUp() {
        getDigitalResourceUseCase = new GetDigitalResourceUseCase(productRepository);
    }

    @AfterEach
    void tearDown() {
        getDigitalResourceUseCase = null;
    }

    @Test
    public void cuandoSeBuscaIdDeUnProductoDigitalExistenteDebeDevolverUsuarioBuscado(){
        //Given
        DigitalResource expectedProduct = new EBook(1, "TestISBN", "TestTitle3",
                "TestAuthor3", "TestNumPages", "TestFormat3");
        Mockito.when(productRepository.getEbook(1)).thenReturn((EBook) expectedProduct);

        //When
        DigitalResource actualProduct = getDigitalResourceUseCase.execute(1);

        //Then
        Assertions.assertEquals(expectedProduct.id , actualProduct.id);
        Assertions.assertEquals(expectedProduct.title , actualProduct.title);
        Assertions.assertEquals(expectedProduct.author , actualProduct.author);
        Assertions.assertEquals(expectedProduct.format , actualProduct.format);
    }
    @Test
    public void cuandoSeBuscaIdDeProductoDigitalNoExistenteDarNull(){
        //Given
        Mockito.when(productRepository.getEbook(-999)).thenReturn(null);

        //When
        DigitalResource actualProduct = getDigitalResourceUseCase.execute(-999);

        //Then
        Assertions.assertNull(actualProduct , "No debió recuperarse ningún producto");
    }
}