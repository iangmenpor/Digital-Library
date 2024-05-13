package com.iesam.digitalLibrary.features.digitalResources.domain;

import java.util.List;

public interface DigitalResourceRepository<E extends DigitalResource> {

    void saveDigitalResource(E model);
    E getDigitalResource(Integer id);
    void deleteDigitalResource(Integer id);
    List<E> getDigitalResources();
    void updateDigitalResource(E model);
}
