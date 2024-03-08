import controller.UserController;

import java.util.List;
import java.util.Scanner;

import static controller.UserController.users;
import static view.View.*;


public class Main {
    private final static UserController userController  = new UserController();

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

      while (true) {
          try {
          switch (ui()) {
              case 1 -> {
                  viewAllUser();
              }
              case 2 -> {
                  userController.createUser();
                  System.out.println("Create User is successfully!");
                  System.out.println("~-".repeat(60));

              }
              case 3 -> {
                  searchUI();
              }
              case 4 -> {
                  System.out.print("Enter name to update: ");
                  String newName= scanner.nextLine();
                  userController.update(users,newName);


              }
              case 5 -> {
                    userController.deleteUser();
              }
              default -> System.out.println("No option please Choose again!");

          }
          }catch (Exception e){
              System.out.println(e.getMessage());
          }
      }

    }

}