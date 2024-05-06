package com.iesam.digitalLibrary.features.user.domain;

import java.util.List;

public interface UserRepository {

    void saveUser(User model);
    User getUser(Integer id);
    void deleteUser(Integer id);
    List<User> getUsers();
}
