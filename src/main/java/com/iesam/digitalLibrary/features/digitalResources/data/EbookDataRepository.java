package com.iesam.digitalLibrary.features.digitalResources.data;

import com.iesam.digitalLibrary.features.digitalResources.data.local.EBookDataSourceRepository;
import com.iesam.digitalLibrary.features.digitalResources.data.local.EbookMemLocalDataSource;
import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResourceRepository;
import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;

import java.util.List;

public class EbookDataRepository implements DigitalResourceRepository<EBook> {

    private final EBookDataSourceRepository memLocalDataSource;
    private final EBookDataSourceRepository eBookFileLocalDataSource;

    public EbookDataRepository(EBookDataSourceRepository dataSourceRepository) {
        this.memLocalDataSource = EbookMemLocalDataSource.getInstance();
        this.eBookFileLocalDataSource = dataSourceRepository;
    }

    @Override
    public void saveDigitalResource(EBook model) {
        eBookFileLocalDataSource.save(model);
    }

    @Override
    public EBook getDigitalResource(Integer id) {
        EBook eBook = memLocalDataSource.findById(id); // Buscar en memoria
        if (eBook != null) {
            return eBook;
        }
        return eBookFileLocalDataSource.findById(id); // Si no está en memoria, buscar en el fichero
    }

    @Override
    public void deleteDigitalResource(Integer id) {
        memLocalDataSource.delete(id);
        eBookFileLocalDataSource.delete(id); // Asegurar que también se elimina del fichero
    }

    @Override
    public List<EBook> getDigitalResources() {
        List<EBook> eBooks = memLocalDataSource.findAll();
        List<EBook> fileEBooks = eBookFileLocalDataSource.findAll();
        if (!eBooks.isEmpty() && eBooks.size() == fileEBooks.size()) {
            return eBooks;
        }
        eBooks = fileEBooks;
        if (eBooks != null && !eBooks.isEmpty()) {
            memLocalDataSource.saveList(eBooks);
        }
        return eBooks;
    }

    @Override
    public void updateDigitalResource(EBook model) {
        memLocalDataSource.update(model);
        eBookFileLocalDataSource.update(model);
    }
}