package com.iesam.digitalLibrary.features.user.domain;

public interface UserRepository {

    void saveUser(User model);
    User getUser(Integer id);
    void deleteUser(Integer id);
}
