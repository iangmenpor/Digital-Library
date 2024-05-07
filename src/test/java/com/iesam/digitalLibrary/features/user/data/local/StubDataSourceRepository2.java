package com.iesam.digitalLibrary.features.user.data.local;

import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class StubDataSourceRepository2 implements DataSourceRepository{

    List<User> userList;

    public StubDataSourceRepository2(){
        this.userList = new ArrayList<>();
    }
    @Override
    public void save(User model) {
        userList.add(model);
    }

    @Override
    public User findById(Integer id) {
        for (User user : userList) {
            if (user.id.equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer modelId) {
        User userToDelete = null;
        for (User user : userList) {
            if (user.id.equals(modelId)) {
                userToDelete = user;
                break;
            }
        }
        if (userToDelete != null) {
            userList.remove(userToDelete);
        }
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void saveList(List<User> models) {
        for (User users : models){
            save(users);
        }
    }
}
