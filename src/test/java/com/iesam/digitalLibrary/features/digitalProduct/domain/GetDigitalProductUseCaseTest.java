package com.iesam.digitalLibrary.features.digitalProduct.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDigitalProductUseCaseTest {

    @Mock
    private DigitalProductRepository productRepository;

    private GetDigitalProductUseCase getDigitalProductUseCase;

    @BeforeEach
    void setUp() {
        getDigitalProductUseCase = new GetDigitalProductUseCase(productRepository);
    }

    @AfterEach
    void tearDown() {
        getDigitalProductUseCase = null;
    }

    @Test
    public void cuandoSeBuscaIdDeUnProductoDigitalExistenteDebeDevolverUsuarioBuscado(){
        //Given
        DigitalProduct expectedProduct = new DigitalProduct(1, "TitleTest","AuthorTest","FormatTest");
        Mockito.when(productRepository.getDigitalProduct(1)).thenReturn(expectedProduct);

        //When
        DigitalProduct actualProduct = getDigitalProductUseCase.execute(1);

        //Then
        Assertions.assertEquals(expectedProduct.id , actualProduct.id);
        Assertions.assertEquals(expectedProduct.title , actualProduct.title);
        Assertions.assertEquals(expectedProduct.author , actualProduct.author);
        Assertions.assertEquals(expectedProduct.format , actualProduct.format);
    }
    @Test
    public void cuandoSeBuscaIdDeProductoDigitalNoExistenteDarNull(){
        //Given
        Mockito.when(productRepository.getDigitalProduct(-999)).thenReturn(null);

        //When
        DigitalProduct actualProduct = getDigitalProductUseCase.execute(-999);

        //Then
        Assertions.assertNull(actualProduct , "No debió recuperarse ningún producto");
    }
}