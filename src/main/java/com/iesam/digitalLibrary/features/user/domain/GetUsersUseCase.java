package com.iesam.digitalLibrary.features.user.domain;
import java.util.List;
public class GetUsersUseCase {

    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute(){
        return userRepository.getUsers();
    }
}
