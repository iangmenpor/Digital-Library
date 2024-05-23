package com.iesam.digitalLibrary.features.user.data;

import com.iesam.digitalLibrary.features.user.data.local.DataSourceRepository;
import com.iesam.digitalLibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitalLibrary.features.user.domain.User;
import com.iesam.digitalLibrary.features.user.domain.UserRepository;

import java.util.List;


public class UserDataRepository implements UserRepository {

    private final DataSourceRepository memLocalDataSource;
    private final DataSourceRepository userFileLocalDataSource;

    public UserDataRepository(DataSourceRepository dataSourceRepository) {
        this.memLocalDataSource = UserMemLocalDataSource.getInstance();
        this.userFileLocalDataSource = dataSourceRepository;
    }

    @Override
    public void saveUser(User model) {
        userFileLocalDataSource.save(model);
    }

    @Override
    public User getUser(Integer id) {
        User user = memLocalDataSource.findById(id); // 1º Buscar en memoria
        if (user != null) {
            return user;
        }
        user = userFileLocalDataSource.findById(id); // 2ª Si no está en mem, buscar en fichero
        if (user != null) {
            memLocalDataSource.save(user); // Guardar en mem si lo encuentra
        }
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        memLocalDataSource.delete(id);
        userFileLocalDataSource.delete(id);// Asegurar que también se elimina de userFileLocalDataSource
    }

    @Override
    public List<User> getUsers() {
        List<User> users = memLocalDataSource.findAll();
        List<User> fileUsers = userFileLocalDataSource.findAll();
        if (!users.isEmpty() && users.size() == fileUsers.size()) {
            return users;
        }
        users = fileUsers;
        if (users != null && !users.isEmpty()) {
            memLocalDataSource.saveList(users);
        }
        return users;
    }

    @Override
    public void updateUser(User model) {
        memLocalDataSource.update(model);
        userFileLocalDataSource.update(model);
    }
}
