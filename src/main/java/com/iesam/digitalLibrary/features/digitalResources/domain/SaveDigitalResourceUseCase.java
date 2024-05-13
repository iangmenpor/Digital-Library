package com.iesam.digitalLibrary.features.digitalResources.domain;

public class SaveDigitalResourceUseCase <T extends DigitalResource> {

    private final DigitalResourceRepository<T> digitalResourceRepository;

    public SaveDigitalResourceUseCase(DigitalResourceRepository<T> digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(T model){
        digitalResourceRepository.saveDigitalResource(model);
    }
}
