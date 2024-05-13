package com.iesam.digitalLibrary.features.digitalResources.domain;

public class GetDigitalResourceUseCase <T extends DigitalResource>  {

    private final DigitalResourceRepository<T> digitalResourceRepository;

    public GetDigitalResourceUseCase(DigitalResourceRepository<T> digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public T execute(Integer id){
        return digitalResourceRepository.getDigitalResource(id);
    }
}
