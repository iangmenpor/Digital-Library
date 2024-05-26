package com.iesam.digitalLibrary.features.digitalResources.data.local;

import com.iesam.digitalLibrary.features.digitalResources.domain.AudioBook;
import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResourceRepository;

import java.util.List;

public class AudioBookDataRepository implements DigitalResourceRepository<AudioBook> {

    private final AudioBookDataSourceRepository memLocalDataSource;
    private final AudioBookDataSourceRepository audioBookFileLocalDataSource;

    public AudioBookDataRepository(AudioBookDataSourceRepository dataSourceRepository) {
        this.memLocalDataSource = AudioBookMemLocalDataSource.getInstance();
        this.audioBookFileLocalDataSource = dataSourceRepository;
    }

    @Override
    public void saveDigitalResource(AudioBook model) {
        audioBookFileLocalDataSource.save(model);
    }

    @Override
    public AudioBook getDigitalResource(Integer id) {
        AudioBook audioBook = memLocalDataSource.findById(id); // Buscar en memoria
        if (audioBook != null) {
            return audioBook;
        }
        return audioBookFileLocalDataSource.findById(id); // Si no está en memoria, buscar en el fichero
    }

    @Override
    public void deleteDigitalResource(Integer id) {
        memLocalDataSource.delete(id);
        audioBookFileLocalDataSource.delete(id); // Asegurar que también se elimina del fichero
    }

    @Override
    public List<AudioBook> getDigitalResources() {
        List<AudioBook> audioBooks = memLocalDataSource.findAll();
        List<AudioBook> fileAudioBooks = audioBookFileLocalDataSource.findAll();
        if (!audioBooks.isEmpty() && audioBooks.size() == fileAudioBooks.size()) {
            return audioBooks;
        }
        audioBooks = fileAudioBooks;
        if (audioBooks != null && !audioBooks.isEmpty()) {
            memLocalDataSource.saveList(audioBooks);
        }
        return audioBooks;
    }

    @Override
    public void updateDigitalResource(AudioBook model) {
        memLocalDataSource.update(model);
        audioBookFileLocalDataSource.update(model);
    }
}
