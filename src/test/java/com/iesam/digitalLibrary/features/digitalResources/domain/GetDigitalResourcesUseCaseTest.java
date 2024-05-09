package com.iesam.digitalLibrary.features.digitalResources.domain;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetDigitalResourcesUseCaseTest {
    @Mock
    private DigitalResourceRepository digitalResourceRepository;

    private GetDigitalResourcesUseCase getDigitalResourcesUseCase;
    @BeforeEach
    void setUp() {
        getDigitalResourcesUseCase = new GetDigitalResourcesUseCase(digitalResourceRepository);
    }

    @AfterEach
    void tearDown() {
        getDigitalResourcesUseCase = null;
    }

    @Test
    public void cuandoSeSolicitaLaListaDeUsuariosSeDevuelveCorrectamente(){
        //Given
        List<EBook> expectedProducts = Arrays.asList(
                new EBook(1, "TestISBN1", "TestTitle1", "TestAuthor1", "TestNumPages1", "TestFormat1"),
                new EBook(2, "TestISBN2", "TestTitle2", "TestAuthor2", "TestNumPages2", "TestFormat2"),
                new EBook(3, "TestISBN3", "TestTitle3", "TestAuthor3", "TestNumPages3", "TestFormat3")
        );
        Mockito.when(digitalResourceRepository.getEbooks()).thenReturn(expectedProducts);

        //When
        List<EBook> actualProducts = getDigitalResourcesUseCase.execute();

        //Then
        Assertions.assertEquals(expectedProducts.size(), actualProducts.size());

        for (int i = 0; i < expectedProducts.size(); i++){
            EBook expectedEbook = expectedProducts.get(i);
            EBook actualEbook = actualProducts.get(i);

            Assertions.assertEquals(expectedEbook.id, actualEbook.id);
            Assertions.assertEquals(expectedEbook.title, actualEbook.title);
            Assertions.assertEquals(expectedEbook.author, actualEbook.author);
            Assertions.assertEquals(expectedEbook.isbn, actualEbook.isbn);
            Assertions.assertEquals(expectedEbook.numPages, actualEbook.numPages);
            Assertions.assertEquals(expectedEbook.format, actualEbook.format);
        }
    }
}