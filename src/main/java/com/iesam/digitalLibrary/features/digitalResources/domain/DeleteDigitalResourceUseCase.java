package com.iesam.digitalLibrary.features.digitalResources.domain;

public class DeleteDigitalResourceUseCase <T extends DigitalResource>  {

    private final DigitalResourceRepository<T> digitalResourceRepository;

    public DeleteDigitalResourceUseCase(DigitalResourceRepository<T> digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(Integer id){
        digitalResourceRepository.deleteDigitalResource(id);
    }
}
