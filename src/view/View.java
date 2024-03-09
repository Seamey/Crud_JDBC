package view;

import controller.UserController;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class View {
    private static final UserController userController = new UserController();

    public static int ui() {

        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        System.out.println("=".repeat(30)+" Menu "+"=".repeat(30));
        table.addCell("     1.Display Data         ");
        table.addCell("     2.Create            ");
        table.addCell("     3.Search User BY Id        ");
        table.addCell("     4.Update            ");
        table.addCell("     5.Delete            ");
        System.out.println(table.render());

        System.out.println("====== Choose your Option: ");
        return new Scanner(System.in).nextInt();
    }
    public static void viewAllUser(){
        try {
            Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

            List<User> users = userController.getAllUser();

            table.addCell("  USER ID  ");
            table.addCell("  USER UUID  ");
            table.addCell("     USER NAME     ");
            table.addCell("     USER EMAIL     ");
            table.addCell("     USER PASSWORD     ");
            table.addCell("     USER IS DELETED     ");
            table.addCell("     USER IS VERIFY     ");
            for (User user : users) {
                table.addCell(String.valueOf(user.getUserId()), cellStyle);
                table.addCell(user.getUserUuid(), cellStyle);
                table.addCell(user.getUserName(), cellStyle);
                table.addCell(user.getUserEmail(), cellStyle);
                table.addCell(user.getUserPassword(),cellStyle);
                table.addCell(String.valueOf(user.getIsDeleted()), cellStyle);
                table.addCell(String.valueOf(user.getIsVerified()), cellStyle);
            }
            System.out.println(table.render());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void  searchUI(){
        try{
            System.out.print(">>>>> Enter User id: ");
            Integer id = new Scanner(System.in).nextInt();
            Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

            List<User> users = Collections.singletonList(userController.searchById(id));

            table.addCell("  User id  ".toUpperCase(Locale.ROOT));
            table.addCell("  User uuid ".toUpperCase(Locale.ROOT));
            table.addCell("  User Name ".toUpperCase(Locale.ROOT));
            table.addCell("  User Email ".toUpperCase(Locale.ROOT));
            table.addCell("  User Password ".toUpperCase(Locale.ROOT));
            table.addCell("  User is Deleted ".toUpperCase(Locale.ROOT));
            table.addCell("  User is Verifried ".toUpperCase(Locale.ROOT));
            for (User user : users) {
                table.addCell(String.valueOf(user.getUserId()), cellStyle);
                table.addCell(user.getUserUuid(),cellStyle);
                table.addCell(user.getUserName(), cellStyle);
                table.addCell(user.getUserEmail(), cellStyle);
                table.addCell(user.getUserPassword(), cellStyle);
                table.addCell(String.valueOf(user.getIsDeleted()), cellStyle);
                table.addCell(String.valueOf(user.getIsVerified()), cellStyle);
            }
            System.out.println(table.render());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
        public static   User Create(){
            Scanner scanner = new Scanner(System.in);

            User newUser = new User();
//            Random random = new Random();
            UUID uuid = UUID.randomUUID();
            String newUUID = uuid.toString().substring(0,9);

            try{
                System.out.println("Enter Username : ");
                newUser.setUserName(scanner.nextLine());
                System.out.println("Enter User Email: ");
                newUser.setUserEmail(scanner.nextLine());
                System.out.println("Enter User Password : ");
                newUser.setUserPassword(scanner.nextLine());
                System.out.println("Enter Verify (Ture/False):");
                newUser.setIsDeleted(Boolean.parseBoolean(scanner.nextLine()));
                newUser.setIsDeleted(false);
                newUser.setUserUuid(newUUID);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return newUser;
        }


        public static User deleteUser(List<User> users){
            System.out.print("Enter id to delete: ");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());
            for (User user : users) {
                if(user.getUserId().equals(id)){
                    users.remove(user);
                    return users.get(0);
                }
            }
          return null;

        }

}
