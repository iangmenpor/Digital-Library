package com.iesam.digitalLibrary.features.user.domain;

public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Integer id){
        userRepository.deleteUser(id);
    }
}
