package com.iesam.digitalLibrary.features.user.data.local;

import com.iesam.digitalLibrary.features.user.domain.User;

public interface DataSourceRepository {

    void save (User model);


    User findById(Integer id);

}
