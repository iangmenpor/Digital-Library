package com.iesam.digitalLibrary.features.user.data.local;

import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.List;

public interface DataSourceRepository {

    void save (User model);
    User findById(Integer id);
    void delete(Integer modelId);
    List<User> findAll();
}
