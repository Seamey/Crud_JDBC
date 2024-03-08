package utils;

public class SQLUtils {
    public static final String getAll = """ 
            SELECT * FROM users
            """;
    public static final String search = """ 
            SELECT * FROM users WHERE user_id = ?
            """;
    public static final String insertNewPerson= """
                INSERT INTO users ("user_name","user_email","user_password","is_deleted","is_verified") VALUES(?,?,?,?,?);
                """;

    public  static final String deletePersonById = """
                delete from users where user_id = ?
                """;

    public  static final String updatePerson= """
                update users set  user_name=?,user_email=?,user_password=?,is_deleted=?,is_verified
                where user_id = ?
                """;

}


