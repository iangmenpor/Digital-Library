package com.iesam.digitalLibrary.features.user.data.local;



import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StubUserMemLocalDataSource implements DataSourceRepository{

    private final Map<String, User> dataStore = new TreeMap<>();
    private static StubUserMemLocalDataSource instance = null;


    private StubUserMemLocalDataSource(){}
    public static StubUserMemLocalDataSource getInstance(){
        if (instance==null){
            instance = new StubUserMemLocalDataSource();
        }
        return instance;
    }
    @Override
    public void save(User user) {
        dataStore.put(user.id.toString(), user);
    }
    @Override
    public void saveList(List<User> models) {
        for (User demo : models) {
            save(demo);
        }
    }

    @Override
    public User findById(Integer id) {
        return dataStore.get(id.toString());
    }
    @Override
    public List<User> findAll() {
        return dataStore.values().stream().toList();
    }
    @Override
    public void delete(Integer modelId) {
        dataStore.remove(modelId.toString());
    }
}
