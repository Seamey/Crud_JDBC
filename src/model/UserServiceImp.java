package model;

import repository.UserRepository;

import java.util.List;

public class UserServiceImp implements UserServeice {
    @Override
    public List<User> getAllUser() {
        return UserRepository.getAllUser();
    }

    @Override
    public User searchUserById(Integer id) {
         return UserRepository.searchUserById(id);
    }

    @Override
    public void createUser(User user) {
        UserRepository.createUser(user);
    }


    @Override
    public User deleteUser(User user) {
        return UserRepository.deleteUser(user);
    }

    @Override
    public User updateUser(User user) {
        return UserRepository.updateUser(user);
    }


}
