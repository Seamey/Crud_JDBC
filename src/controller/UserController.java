package controller;

import model.User;
import model.UserServeice;
import model.UserServiceImp;
import repository.UserRepository;
import view.View;

import java.util.List;

public class UserController {
    private static final UserServeice userServeice = new UserServiceImp();
    public  static List<User> users = userServeice.getAllUser();

    public List<User> getAllUser(){
        return  userServeice.getAllUser();

    }
    public User searchById(Integer id){
        User user = userServeice.searchUserById(id);
        return user;
    }
    public void createUser(){
        User newuser = View.Create();
        UserRepository.createUser(newuser);

    }
    public void update(List<User> user,String name) {

        User updateUser = View.Create();
        userServeice.updateUser(updateUser);

    }
    public void deleteUser(){
        User user = View.deleteUser(UserController.users);

        userServeice.deleteUser(user);
    }

}




