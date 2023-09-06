package helper;

import java.sql.DriverManager;


public class DatabaseConnection {
    private static String url = "jdbc:postgresql://localhost:5432/minisass";
    private static String userName = "postgres";
    private static String password = "root";
    private static java.sql.Connection cnx = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            cnx = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static java.sql.Connection getConn() {
        return cnx;
    }
}

