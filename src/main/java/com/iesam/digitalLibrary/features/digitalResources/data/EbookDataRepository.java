package com.iesam.digitalLibrary.features.digitalResources.data;

import com.iesam.digitalLibrary.features.digitalResources.data.local.EBookDataSourceRepository;
import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResourceRepository;
import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;

import java.util.List;

public class EbookDataRepository implements DigitalResourceRepository<EBook> {

    private final EBookDataSourceRepository eBookDataSourceRepository;

    public EbookDataRepository(EBookDataSourceRepository dataSourceRepository) {
        this.eBookDataSourceRepository = dataSourceRepository;
    }


    @Override
    public void saveDigitalResource(EBook model) {
        eBookDataSourceRepository.save(model);
    }

    @Override
    public EBook getDigitalResource(Integer id) {
        return eBookDataSourceRepository.findById(id);
    }

    @Override
    public void deleteDigitalResource(Integer id) {
        eBookDataSourceRepository.delete(id);
    }

    @Override
    public List<EBook> getDigitalResources() {
        return eBookDataSourceRepository.findAll();
    }

    @Override
    public void updateDigitalResource(EBook model) {
        eBookDataSourceRepository.update(model);
    }
}
