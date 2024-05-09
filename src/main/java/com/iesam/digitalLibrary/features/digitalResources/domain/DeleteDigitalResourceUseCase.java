package com.iesam.digitalLibrary.features.digitalResources.domain;

public class DeleteDigitalResourceUseCase {

    private final DigitalResourceRepository digitalResourceRepository;

    public DeleteDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(Integer id){
        digitalResourceRepository.deleteEbook(id);
    }
}
