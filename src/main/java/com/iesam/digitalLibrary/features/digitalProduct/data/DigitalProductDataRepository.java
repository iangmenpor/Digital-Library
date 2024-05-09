package com.iesam.digitalLibrary.features.digitalProduct.data;

import com.iesam.digitalLibrary.features.digitalProduct.data.local.DigitalProductDataSourceRepository;
import com.iesam.digitalLibrary.features.digitalProduct.domain.DigitalProduct;
import com.iesam.digitalLibrary.features.digitalProduct.domain.DigitalProductRepository;
import com.iesam.digitalLibrary.features.digitalProduct.domain.EBook;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class DigitalProductDataRepository implements DigitalProductRepository {

    private final DigitalProductDataSourceRepository dataSourceRepository;

    public DigitalProductDataRepository(DigitalProductDataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public void saveDigitalProduct(DigitalProduct model) {
        dataSourceRepository.save(model);
    }

    @Override
    public DigitalProduct getDigitalProduct(Integer id) {
        return dataSourceRepository.findById(id);
    }

    @Override
    public void deleteDigitalProduct(Integer id) {
        dataSourceRepository.delete(id);
    }

    @Override
    public List<DigitalProduct> getDigitalProducts() {
        return dataSourceRepository.findAll();
    }

    @Override
    public void updateDigitalProduct(DigitalProduct model) {
        dataSourceRepository.update(model);
    }
}
