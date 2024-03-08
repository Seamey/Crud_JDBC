package model;

import java.util.List;

public interface UserServeice {
     List<User> getAllUser();
     User searchUserById(Integer id);
     void createUser(User user);

     User deleteUser(User user);

     User updateUser(User  user);


}


