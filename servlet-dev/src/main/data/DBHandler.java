package data;

import java.sql.*;

public class DBHandler {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBHandler() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch(Exception ex) {
            System.out.println("Error: " + ex);
        }
    }


}
