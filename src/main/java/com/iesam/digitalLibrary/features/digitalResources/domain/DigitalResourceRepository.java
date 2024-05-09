package com.iesam.digitalLibrary.features.digitalResources.domain;

import java.util.List;

public interface DigitalResourceRepository {

    void saveEbook(EBook model);
    EBook getEbook(Integer id);
    void deleteEbook(Integer id);
    List<EBook> getEbooks();
    void updateEbook(EBook model);
}
