package com.iesam.digitalLibrary.features.user.data.local;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalLibrary.features.loan.domain.Loan;
import com.iesam.digitalLibrary.features.user.domain.User;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserFileDataSource implements DataSourceRepository{

    private final String nameFile = "user.txt";

    private final Gson gson = new Gson();

    private final Type typeList = new TypeToken<ArrayList<User>>() {
    }.getType();
    @Override
    public void save(User model) {
        List<User> models = findAll();
        models.add(model);
        saveToFile(models);
    }
    @Override
    public void saveList(List<User> models) {
        saveToFile(models);
    }

    private void saveToFile(List<User> models) {
        try {
            FileWriter myWriter = new FileWriter(nameFile);
            myWriter.write(gson.toJson(models));
            myWriter.close();
            System.out.println("[OK] Datos guardados correctamente");
        } catch (IOException e) {
            System.err.println("[ERR] Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Integer id) {
        List<User> models = findAll();
        for (User model : models) {
            if (Objects.equals(model.id, id)) {
                return model;
            }
        }
        return null;
    }
    @Override
    public List<User> findAll() {
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
            System.out.println("[ERR] Ha ocurrido un error al obtener el listado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERR] Ha ocurrido un error al crear el fichero.");
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }
    @Override
    public void delete(Integer modelId) {
        List<User> newList = new ArrayList<>();
        List<User> models = findAll();
        for (User model : models) {
            if (!model.id.equals(modelId)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }
    @Override
    public void update(User model) {
        List<User> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            User existingUser = users.get(i);
            if (existingUser.id.equals(model.id)) {
                users.set(i, model); // Actualizar el producto
                saveList(users); // Guardar la lista actualizada
                return;
            }
        }
        System.out.println("[!] Usuario no encontrado para actualizar.");
    }

}
