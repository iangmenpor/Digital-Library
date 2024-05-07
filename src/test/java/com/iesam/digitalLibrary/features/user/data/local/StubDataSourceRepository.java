package com.iesam.digitalLibrary.features.user.data.local;

import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.List;

public class StubDataSourceRepository implements DataSourceRepository{
    @Override
    public void save(User model) {

    }

    @Override
    public User findById(Integer id) {
        User user = new User(1,"NameTest","SurnTest","dniTest","emailTest");
        return (user.id.equals(id)? user : null);
    }

    @Override
    public void delete(Integer modelId) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void saveList(List<User> models) {

    }
}
