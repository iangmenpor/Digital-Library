package com.iesam.digitalLibrary.features.digitalResources.domain;

public class SaveDigitalResourceUseCase {

    private final DigitalResourceRepository digitalResourceRepository;

    public SaveDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(EBook model){
        digitalResourceRepository.saveEbook(model);
    }
}
