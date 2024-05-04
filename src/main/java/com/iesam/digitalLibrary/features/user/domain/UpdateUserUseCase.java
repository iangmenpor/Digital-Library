package com.iesam.digitalLibrary.features.user.domain;

public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User model){
        userRepository.updateUser(model);
    }
}
