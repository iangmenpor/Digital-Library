package com.iesam.digitalLibrary.features.digitalResources.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class DeleteDigitalResourceUseCaseTest {

    @Mock
    private DigitalResourceRepository<DigitalResource> digitalResourceRepository;
    private DeleteDigitalResourceUseCase<DigitalResource> deleteDigitalResourceUseCase;

    @BeforeEach
    void setUp() {
        deleteDigitalResourceUseCase = new DeleteDigitalResourceUseCase<>(digitalResourceRepository);
    }

    @AfterEach
    void tearDown() {
        deleteDigitalResourceUseCase = null;
    }

    @Test
    public void cuandoSePasaIDdeRecursoDigitalPorParametroDebeEjecutarMetodoDelete(){
        //Given
        int id = 1;

        //When
        deleteDigitalResourceUseCase.execute(id);

        //Then
        Mockito.verify(digitalResourceRepository, Mockito.times(1)).deleteDigitalResource(id);

    }
}