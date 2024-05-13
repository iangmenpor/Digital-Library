package com.iesam.digitalLibrary.features.digitalResources.domain;

public class UpdateDigitalResourceUseCase <T extends DigitalResource>  {

    private final DigitalResourceRepository<T> digitalResourceRepository;

    public UpdateDigitalResourceUseCase(DigitalResourceRepository<T> digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(T model){
        digitalResourceRepository.updateDigitalResource(model);
    }
}
