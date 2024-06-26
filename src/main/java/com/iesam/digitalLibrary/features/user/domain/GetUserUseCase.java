package com.iesam.digitalLibrary.features.user.domain;

public class GetUserUseCase {

    private final UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Integer id){
        return userRepository.getUser(id);
    }
}
