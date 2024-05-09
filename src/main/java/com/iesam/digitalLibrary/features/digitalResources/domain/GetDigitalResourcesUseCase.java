package com.iesam.digitalLibrary.features.digitalResources.domain;

import java.util.List;

public class GetDigitalResourcesUseCase {

    private final DigitalResourceRepository digitalResourceRepository;

    public GetDigitalResourcesUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public List<EBook> execute(){
        return digitalResourceRepository.getEbooks();
    }
}
