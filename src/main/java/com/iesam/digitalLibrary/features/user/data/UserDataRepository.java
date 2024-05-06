package com.iesam.digitalLibrary.features.user.data;

import com.iesam.digitalLibrary.features.user.data.local.DataSourceRepository;
import com.iesam.digitalLibrary.features.user.domain.User;
import com.iesam.digitalLibrary.features.user.domain.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class UserDataRepository implements UserRepository {

    private final DataSourceRepository dataSourceRepository;

    public UserDataRepository(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }


    @Override
    public void saveUser(User model) { dataSourceRepository.save(model);}

    @Override
    public User getUser(Integer id) {
        return dataSourceRepository.findById(id);
    }
  
    @Override
    public void deleteUser(Integer id) {
        dataSourceRepository.delete(id);
    }

    @Override
    public List<User> getUsers() {
        return dataSourceRepository.findAll();
    }
  
    @Override
    public void updateUser(User model) {
        List<User> userList = new ArrayList<>(dataSourceRepository.findAll());
        for (int i=0; i < userList.size(); i++){
            User user1 = userList.get(i);
            if (model.id.equals(user1.id)){
                userList.set(i,model);
                dataSourceRepository.saveList(userList);
                return;
            }
        }
    }
}
