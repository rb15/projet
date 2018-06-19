package sample;

import java.sql.*;

public class sqlcnx {
    public static Connection cnx() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection cnn = DriverManager.getConnection("jdbc:sqlite:projet.sqlite");
            return cnn;
        } catch (Exception e) {
            System.out.println("errerur cnx");
            return null;

        }
    }

}
