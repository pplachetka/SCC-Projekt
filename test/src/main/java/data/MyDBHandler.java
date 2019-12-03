package main.java.data;


import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class MyDBHandler {

    private static final String Tbl_USER = "scc.USER";
    private static final String Tbl_TOKEN = "scc.USER_TOKEN";
    private static final String Tbl_MENUITEM = "scc.MENUITEM";

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    // private String mysql_url="jdbc:mysql://localhost:3306/scc";
    private String mysql_url="jdbc:mysql://localhost/scc";
    private String mysql_user="UserX";
    private String mysql_pw="123456";


    public MyDBHandler() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mysql_url,mysql_user,mysql_pw);

        } catch(SQLException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void setToken(int UserId, String token){

        Timestamp ValidFrom =  Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
        try{
            stmt = con.prepareStatement(
                    "INSERT INTO "+Tbl_TOKEN + " (UserId, Token, ValidFrom)" +
                            " VALUES ('"+UserId+"','"+token+"',?)"

            );
            stmt.setTimestamp(1,ValidFrom);
            System.out.println(stmt);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUser(int UserId, String password, String token){
        boolean returnValue = false;
        //takes UserId and either token or password to authenticate to system
        try {
            stmt = con.prepareStatement(
                    "SELECT user.Password, IFNULL(token.Token,-1) as Token, token.ValidFrom" +
                            " FROM "+ Tbl_USER +" user" +
                            " LEFT JOIN "+ Tbl_TOKEN +" token" +
                            " ON token.UserID = user.UserID" +
                            " WHERE user.UserID = ? "
            );
            stmt.setInt(1,UserId);
            System.out.println(stmt);
            rs = stmt.executeQuery();

            while(rs.next()){
                if (rs.getString("Password").equals(password) || rs.getString("Token").equals(token)){
                    returnValue = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public boolean isAdmin(int UserId){
        boolean returnValue = false;
        try {
            stmt = con.prepareStatement(
                    "SELECT isAdmin FROM "+ Tbl_USER + " WHERE UserID = ?"
            );
            stmt.setInt(1,UserId);
            System.out.println(stmt);
            rs = stmt.executeQuery();
            while(rs.next()){
                if (rs.getInt("isAdmin") == 1 ){
                    returnValue = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public User getUserData(int UserId) {

        User user = new User();
        try {
            stmt = con.prepareStatement(
                    "SELECT Name,FamilyName,isAdmin,password, Token " +
                            " FROM " + Tbl_USER + " user" +
                            " LEFT JOIN " + Tbl_TOKEN + " token" +
                            " ON user.userid = token.userid" +
                            " WHERE user.UserId = ?" +
                            " ORDER BY token.ValidFrom DESC " +
                            " LIMIT 1");
            stmt.setInt(1,UserId);
            System.out.println(stmt.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                user.setUserId(UserId);
                user.setName(rs.getString("Name"));
                user.setFamilyName(rs.getString("FamilyName"));
                user.setIsAdmin(rs.getInt("isAdmin"));
                user.setToken(rs.getString("Token"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }


    public ArrayList<MenuItem> getMenuItemList(){
        ArrayList menuItemList = new ArrayList<MenuItem>();

        try {
            stmt = con.prepareStatement(
                    "SELECT MenuItemID, Description, Costs " +
                            " FROM " + Tbl_MENUITEM );
            System.out.println(stmt.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setMenuItemID(rs.getString("MenuItemID"));
                menuItem.setDescription(rs.getString("Description"));
                menuItem.setCosts(rs.getBigDecimal("Costs"));
                menuItemList.add(menuItem);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return menuItemList;

    }

    public void setMenuItem(int menuItemID,String Description, BigDecimal Costs){

        try {
            stmt = con.prepareStatement(
                    "UPDATE " + Tbl_MENUITEM +
                            " SET Description=?" +
                            " ,Costs=?" +
                            "WHERE MenuItemID = ?" );
            stmt.setString(1,Description);
            stmt.setBigDecimal(2,Costs);
            stmt.setInt(1,menuItemID);
            System.out.println(stmt.toString());
            stmt.executeQuery();

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


}
