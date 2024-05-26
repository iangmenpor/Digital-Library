package com.iesam.digitalLibrary.features.digitalResources.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalLibrary.features.digitalResources.domain.AudioBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AudioBookFileDataSource implements AudioBookDataSourceRepository {

    private final String nameFile = "audioBooks.txt";

    private final Gson gson = new Gson();

    private final Type typeList = new TypeToken<ArrayList<AudioBook>>() {
    }.getType();

    @Override
    public void save(AudioBook model) {
        List<AudioBook> models = findAll();
        models.add(model);
        saveToFile(models);
    }

    @Override
    public void saveList(List<AudioBook> models) {
        saveToFile(models);
    }

    private void saveToFile(List<AudioBook> models) {
        try {
            FileWriter myWriter = new FileWriter(nameFile);
            myWriter.write(gson.toJson(models));
            myWriter.close();
            System.out.println("<OK> Datos guardados correctamente");
        } catch (IOException e) {
            System.err.println("<!> Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }

    @Override
    public AudioBook findById(Integer id) {
        List<AudioBook> models = findAll();
        for (AudioBook model : models) {
            if (Objects.equals(model.id, id)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public List<AudioBook> findAll() {
        try {
            File myObj = new File(nameFile);
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myReader.close();
                return gson.fromJson(data, typeList);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("<!> Ha ocurrido un error al obtener el listado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("<!> Ha ocurrido un error al crear el fichero.");
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Integer modelId) {
        List<AudioBook> newList = new ArrayList<>();
        List<AudioBook> models = findAll();
        for (AudioBook model : models) {
            if (!model.id.equals(modelId)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }

    @Override
    public void update(AudioBook model) {
        List<AudioBook> products = findAll();
        for (int i = 0; i < products.size(); i++) {
            AudioBook existingProduct = products.get(i);
            if (existingProduct.id.equals(model.id)) {
                products.set(i, model); // Actualizar el producto
                saveList(products); // Guardar la lista actualizada
                return;
            }
        }
        System.out.println("<!> AudioBook no encontrado para actualizar.");
    }
}
