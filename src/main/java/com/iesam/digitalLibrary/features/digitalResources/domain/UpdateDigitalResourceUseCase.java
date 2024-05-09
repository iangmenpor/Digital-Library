package com.iesam.digitalLibrary.features.digitalResources.domain;

public class UpdateDigitalResourceUseCase {

    private final DigitalResourceRepository digitalResourceRepository;

    public UpdateDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(EBook model){
        digitalResourceRepository.updateEbook(model);
    }
}
