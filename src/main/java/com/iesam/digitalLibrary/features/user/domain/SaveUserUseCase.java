package com.iesam.digitalLibrary.features.user.domain;

public class SaveUserUseCase {

    private final UserRepository userRepository;

    public SaveUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user){
        userRepository.saveUser(user);
    }
}
