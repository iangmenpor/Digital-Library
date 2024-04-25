package com.iesam.digitalLibrary.features.user.data;

import com.iesam.digitalLibrary.features.user.data.local.DataSourceRepository;
import com.iesam.digitalLibrary.features.user.domain.User;
import com.iesam.digitalLibrary.features.user.domain.UserRepository;


public class UserDataRepository implements UserRepository {

    private final DataSourceRepository dataSourceRepository;

    public UserDataRepository(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }


    @Override
    public void saveUser(User model) { dataSourceRepository.save(model);}
}
