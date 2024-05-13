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
    private DigitalResourceRepository<DigitalResource> digitalResourceRepository;

    private GetDigitalResourcesUseCase<DigitalResource> getDigitalResourcesUseCase;

    @BeforeEach
    void setUp() {
        getDigitalResourcesUseCase = new GetDigitalResourcesUseCase<>(digitalResourceRepository);
    }

    @AfterEach
    void tearDown() {
        getDigitalResourcesUseCase = null;
    }

    @Test
    public void cuandoSeSolicitaLaListaDeRecursosDigitalesSeDevuelveCorrectamente(){
        //Given
        List<DigitalResource> expectedResources = Arrays.asList(
                new EBook(1, "TestISBN1", "TestTitle1", "TestAuthor1", "TestNumPages1", "TestFormat1"),
                new EBook(2, "TestISBN2", "TestTitle2", "TestAuthor2", "TestNumPages2", "TestFormat2"),
                new EBook(3, "TestISBN3", "TestTitle3", "TestAuthor3", "TestNumPages3", "TestFormat3")
        );
        Mockito.when(digitalResourceRepository.getDigitalResources()).thenReturn(expectedResources);

        //When
        List<DigitalResource> actualResources = getDigitalResourcesUseCase.execute();

        //Then
        Assertions.assertEquals(expectedResources.size(), actualResources.size());

        for (int i = 0; i < expectedResources.size(); i++){
            DigitalResource expectedResource = expectedResources.get(i);
            DigitalResource actualResource = actualResources.get(i);

            Assertions.assertEquals(expectedResource.id, actualResource.id);
            Assertions.assertEquals(expectedResource.title, actualResource.title);
            Assertions.assertEquals(expectedResource.author, actualResource.author);
            Assertions.assertEquals(expectedResource.format, actualResource.format);
        }
    }
}