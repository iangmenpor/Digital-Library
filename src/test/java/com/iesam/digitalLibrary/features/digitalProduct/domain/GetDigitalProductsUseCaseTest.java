package com.iesam.digitalLibrary.features.digitalProduct.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetDigitalProductsUseCaseTest {
    @Mock
    private DigitalProductRepository digitalProductRepository;

    private GetDigitalProductsUseCase getDigitalProductsUseCase;

    @BeforeEach
    void setUp() {
        getDigitalProductsUseCase = new GetDigitalProductsUseCase(digitalProductRepository);
    }

    @AfterEach
    void tearDown() {
        getDigitalProductsUseCase = null;
    }

    @Test
    public void cuandoSeSolicitaLaListaDeUsuariosSeDevuelveCorrectamente(){
        //Given
        List<DigitalProduct> expectedProducts = Arrays.asList(
                new DigitalProduct(1,"TestTitle1", "TestAuthor1","TestFormat1"),
                new DigitalProduct(2, "TestTitle2", "TestAuthor2","TestFormat2"),
                new EBook(3, "TestISBN", "TestTitle3", "TestAuthor3", "TestNumPages", "TestFormat3")
        );
        Mockito.when(digitalProductRepository.getDigitalProducts()).thenReturn(expectedProducts);

        //When
        List<DigitalProduct> actualProducts = getDigitalProductsUseCase.execute();

        //Then
        Assertions.assertEquals(expectedProducts.size(), actualProducts.size());


        for (int i = 0; i < expectedProducts.size(); i++){
            DigitalProduct expectedProduct = expectedProducts.get(i);
            DigitalProduct actualProduct = actualProducts.get(i);

            Assertions.assertEquals(expectedProduct.id, actualProduct.id);
            Assertions.assertEquals(expectedProduct.title, actualProduct.title);
            Assertions.assertEquals(expectedProduct.author, actualProduct.author);
            Assertions.assertEquals(expectedProduct.format, actualProduct.format);
        }
    }
}