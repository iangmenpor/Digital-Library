package com.iesam.digitalLibrary.features.user.data.local;



import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserMemLocalDataSource implements DataSourceRepository{

    private Map<String, User> dataStore = new TreeMap<>();
    private static UserMemLocalDataSource instance = null;


    private UserMemLocalDataSource(){}
    public static UserMemLocalDataSource getInstance(){
        if (instance==null){
            instance = new UserMemLocalDataSource();
        }
        return instance;
    }
    @Override
    public void save(User user) {
        dataStore.put(user.id.toString(), user);
    }
    public void saveList(List<User> models) {
        for (User demo : models) {
            save(demo);
        }
    }

    @Override
    public User findById(Integer id) {
        return dataStore.get(id.toString());
    }
    public List<User> findAll() {
        return dataStore.values().stream().toList();
    }
    @Override
    public void delete(Integer modelId) {
        dataStore.remove(modelId.toString());
    }
}
