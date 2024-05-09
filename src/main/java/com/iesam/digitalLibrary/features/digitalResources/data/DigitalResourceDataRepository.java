package com.iesam.digitalLibrary.features.digitalResources.data;

import com.iesam.digitalLibrary.features.digitalResources.data.local.EBookDataSourceRepository;
import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResourceRepository;
import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;

import java.util.List;

public class DigitalResourceDataRepository implements DigitalResourceRepository {

    private final EBookDataSourceRepository eBookDataSourceRepository;

    public DigitalResourceDataRepository(EBookDataSourceRepository dataSourceRepository) {
        this.eBookDataSourceRepository = dataSourceRepository;
    }


    @Override
    public void saveEbook(EBook model) {
        eBookDataSourceRepository.save(model);
    }

    @Override
    public EBook getEbook(Integer id) {
        return eBookDataSourceRepository.findById(id);
    }

    @Override
    public void deleteEbook(Integer id) {
        eBookDataSourceRepository.delete(id);
    }

    @Override
    public List<EBook> getEbooks() {
        return eBookDataSourceRepository.findAll();
    }

    @Override
    public void updateEbook(EBook model) {
        eBookDataSourceRepository.update(model);
    }
}
