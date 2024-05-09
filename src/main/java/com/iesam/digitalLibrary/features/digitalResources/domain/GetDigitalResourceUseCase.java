package com.iesam.digitalLibrary.features.digitalResources.domain;

public class GetDigitalResourceUseCase {

    private final DigitalResourceRepository digitalResourceRepository;

    public GetDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public EBook execute(Integer id){
        return digitalResourceRepository.getEbook(id);
    }
}
