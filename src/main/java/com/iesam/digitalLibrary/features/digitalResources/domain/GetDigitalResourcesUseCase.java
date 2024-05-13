package com.iesam.digitalLibrary.features.digitalResources.domain;

import java.util.List;

public class GetDigitalResourcesUseCase <T extends DigitalResource> {

    private final DigitalResourceRepository<T> digitalResourceRepository;

    public GetDigitalResourcesUseCase(DigitalResourceRepository<T> digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public List<T> execute(){
        return digitalResourceRepository.getDigitalResources();
    }
}
