package repository;

import model.User;
import utils.SQLUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class UserRepository {
    private static Connection connectionDatabase() throws SQLException{
        return DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password")
        );
    }
    public static List<User> getAllUser(){
        List<User> users = new ArrayList<>();
        PropertiesLoader.loaderProperties();
        try(

                Statement statement = connectionDatabase().createStatement();
                ResultSet resultSet = statement.executeQuery(SQLUtils.getAll);
                )
        {
            while (resultSet.next()){
                users.add(
                        new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_uuid"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_password"),
                                resultSet.getString("user_email"),
                                resultSet.getBoolean("is_deleted"),
                                resultSet.getBoolean("is_verified")
                        )
                );
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

     return users;
    }
    public static User searchUserById(Integer id){

        try(
                PreparedStatement preparedStatement = connectionDatabase().prepareStatement(SQLUtils.search);
        ){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    return  new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_uuid"),
                            resultSet.getString("user_name"),
                            resultSet.getString("user_password"),
                            resultSet.getString("user_email"),
                            resultSet.getBoolean("is_deleted"),
                            resultSet.getBoolean("is_verified")
                    );
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }catch (Exception e){
            System.out.println("Here is error : "+e.getMessage());
        }
        return null;
    }
    public static void createUser(User user){
        PropertiesLoader.loaderProperties();
        try(
                Statement statement = connectionDatabase().createStatement();
                PreparedStatement preparedStatement = connectionDatabase().prepareStatement(SQLUtils.insertNewPerson);

        ) {
          preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserUuid());
          preparedStatement.setString(3,user.getUserEmail());
          preparedStatement.setBoolean(4,user.getIsDeleted());
                  preparedStatement.setString(5,user.getUserPassword());
            preparedStatement.setBoolean(6,user.getIsVerified());


            int affectedRTow = preparedStatement.executeUpdate();
            if (affectedRTow>0){
                System.out.println("Created Successfully!");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static User deleteUser(User user){

        PropertiesLoader.loaderProperties();
        try (
                PreparedStatement preparedStatement = connectionDatabase().prepareStatement(SQLUtils.deletePersonById)
                ){
            preparedStatement.setInt(1,user.getUserId());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0 ){
                System.out.println("User deleted successfully");
            } else {
                System.out.println("User not found in database");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    public static User updateUser(User user){
        try(
                PreparedStatement preparedStatement = connectionDatabase().prepareStatement(SQLUtils.updatePerson)
                ){
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2,user.getUserUuid());
            preparedStatement.setBoolean(3,user.getIsDeleted());
            preparedStatement.setString(4,user.getUserEmail());
            preparedStatement.setString(5, user.getUserPassword());
            preparedStatement.setBoolean(6,user.getIsVerified());

            int affectedRTow = preparedStatement.executeUpdate();
            if (affectedRTow>0){
                System.out.println(" User Updated Succesfully !!");
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
