package com.scc.speiseplan.data;


import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class MyDBHandler {

    private static final String Tbl_USER = "scc.USER";
    private static final String Tbl_TOKEN = "scc.USER_TOKEN";
    private static final String Tbl_MENUITEM = "scc.MENUITEM";
    private static final String Tbl_MENUITEMSCHEDULE = "scc.MENUITEMSCHEDULE";
    private static final String Tbl_CUSTOMERORDER = "scc.CUSTOMER_ORDER";

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

   // private String mysql_url="jdbc:mysql://localhost:3306/scc";
    private String mysql_url="jdbc:mysql://mysql/scc";
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

    public MyDBHandler(String mysql_url){
        this.mysql_url=mysql_url;
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
                            " VALUES (?,?,?)" +
                            " ON DUPLICATE KEY UPDATE " +
                            " Token = ? " +
                            " , ValidFrom = ? "
            );
            stmt.setInt(1, UserId);
            stmt.setString(2,token);
            stmt.setTimestamp(3,ValidFrom);
            stmt.setString(4,token);
            stmt.setTimestamp(5,ValidFrom);

            System.out.println(stmt);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean login(int UserId, String password){
        boolean returnValue = false;
        try {
            stmt = con.prepareStatement(
                    "SELECT user.Password" +
                            " FROM "+ Tbl_USER +" user" +
                            " WHERE user.UserID = ? "
            );
            stmt.setInt(1,UserId);
            System.out.println(stmt);
            rs = stmt.executeQuery();
            while(rs.next()) {
                if (rs.getString("password").equals(password)) {
                    System.out.println(rs.getString("password"));
                    returnValue = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /*
    * checks whether token belongs to an admin and returns true or false
    */
    public boolean isAdmin(String token){
        boolean returnValue = false;
        try {
            stmt = con.prepareStatement(
                    "SELECT token.ValidFrom, User.isAdmin FROM " + Tbl_TOKEN + " token "
                            + " JOIN " + Tbl_USER  + " User "
                            + " ON User.UserID = token.UserID "
                            + " WHERE token.Token = ?"
            );
            stmt.setString(1,token);
            System.out.println(stmt);
            rs=stmt.executeQuery();

            while (rs.next()){
                System.out.println("ValidFrom (DB): "+rs.getTimestamp("ValidFrom") +
                        " cur timestamp: " +new Timestamp(new Date().getTime()) +
                        " cur timestamp - 30 mins: " +new Timestamp(new Date().getTime()-1800000));
                if (rs.getTimestamp("ValidFrom").after(new Timestamp(new Date().getTime()-1800000)) &&
                (rs.getInt("isAdmin")==1)){
                    returnValue=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    /**
     * checks whether token belongs to an User and returns true or false
     */
    public boolean isUser(String token){
        boolean returnValue = false;
        try {
            stmt = con.prepareStatement(
                    "SELECT token.ValidFrom FROM " + Tbl_TOKEN + " token "
                            + " WHERE token.Token = ?"
            );
            stmt.setString(1,token);
            System.out.println(stmt);
            rs=stmt.executeQuery();

            while (rs.next()){
                System.out.println("ValidFrom (DB): "+rs.getTimestamp("ValidFrom") +
                        " cur timestamp: " +new Timestamp(new Date().getTime()) +
                        " cur timestamp - 30 mins: " +new Timestamp(new Date().getTime()-1800000));
                if (rs.getTimestamp("ValidFrom").after(new Timestamp(new Date().getTime()-1800000))){
                    returnValue=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    //ToDO TEST
    public User getUserDataByToken(String token) {

        User user = new User();
        try {
            stmt = con.prepareStatement(
                    "SELECT user.UserID, Name,FamilyName,isAdmin,password, Token " +
                            " FROM " + Tbl_USER + " user" +
                            " LEFT JOIN " + Tbl_TOKEN + " token" +
                            " ON user.userid = token.userid" +
                            " WHERE token.Token = ? " );
            stmt.setString(1,token);
            System.out.println(stmt.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                    user.setUserId(rs.getInt("UserID"));
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



    // **************************** MenuItemList **************************************//
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
               System.out.println(menuItem.toString());
                }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return menuItemList;

    }

    public void updateMenuItem(int menuItemID,String Description, BigDecimal Costs){

        try {
            stmt = con.prepareStatement(
                    "UPDATE " + Tbl_MENUITEM +
                            " SET Description=?" +
                            " ,Costs=?" +
                            " WHERE MenuItemID = ?" );
            stmt.setString(1,Description);
            stmt.setBigDecimal(2,Costs);
            stmt.setInt(3,menuItemID);
            System.out.println(stmt.toString());
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertMenuItem(String Description, BigDecimal Costs){

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO " + Tbl_MENUITEM + "(Description, Costs)" +
                            " VALUES (?,?)");
            System.out.println(Description);
            System.out.println(Costs);
            stmt.setString(1,Description);
            stmt.setBigDecimal(2,Costs);
            System.out.println(stmt.toString());
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteMenuItem(int menuItemID){

        try {
            stmt = con.prepareStatement(
                    "DELETE FROM " + Tbl_MENUITEM +
                            " WHERE menuItemID = ?" );

            stmt.setInt(1,menuItemID);
            System.out.println(stmt.toString());
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //************************************ MenuItemSchedule *********************************/

    public void setMenuItemSchedule(int date, int position, int menuItemID) {
        try {
            // upsert in mysql
            // https://chartio.com/resources/tutorials/how-to-insert-if-row-does-not-exist-upsert-in-mysql/
            stmt = con.prepareStatement(
                    "INSERT INTO " + Tbl_MENUITEMSCHEDULE + "(Date,Position,MenuItemID)" +
                            " VALUES (?,?,?) "+
                            " ON DUPLICATE KEY UPDATE "+
                            " MenuItemID = ? " );
            stmt.setInt(1,date);
            stmt.setInt(2, position);
            stmt.setInt(3, menuItemID);
            stmt.setInt(4, menuItemID);

            System.out.println(stmt.toString());
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public ArrayList<MenuItemSchedule> getMenuItemSchedule(int startDate, int endDate){
        ArrayList menuItemScheduleList = new ArrayList<MenuItemSchedule>();
        try {
            stmt = con.prepareStatement(
                    "SELECT schedule.menuItemScheduleID, schedule.date, schedule.position, item.menuItemID, item.description, item.costs " +
                            "FROM " + Tbl_MENUITEMSCHEDULE + " schedule " +
                            " JOIN " + Tbl_MENUITEM + " item " +
                            " ON schedule.menuItemID = item.menuItemID " +
                            " WHERE date between ? and ? " );
            stmt.setInt(1,startDate);
            stmt.setInt(2, endDate);

            System.out.println(stmt.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                MenuItemSchedule menuItemSchedule = new MenuItemSchedule();
                    menuItemSchedule.setMenuItemScheduleID(rs.getInt("menuItemScheduleID"));
                    menuItemSchedule.setDate((Integer.valueOf(new SimpleDateFormat("YYYYMMDD").format(rs.getDate("date"))))); //huhhh hacky
                    menuItemSchedule.setPosition(rs.getInt("position"));
                    menuItemSchedule.setMenuItemID(rs.getInt("menuItemID"));
                    menuItemSchedule.setCosts(rs.getBigDecimal("costs"));
                    menuItemSchedule.setDescription(rs.getString("description"));
                menuItemScheduleList.add(menuItemSchedule);
                System.out.println(menuItemSchedule.toString());
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return menuItemScheduleList;

    }

    //************************************ Customer Order  *********************************/

    public void setMenuItemScheduleCustomerOrder(int userId, int date, int menuItemScheduleID) {
        try {
            // upsert in mysql
            // https://chartio.com/resources/tutorials/how-to-insert-if-row-does-not-exist-upsert-in-mysql/
            stmt = con.prepareStatement(
                    "INSERT INTO " + Tbl_CUSTOMERORDER + " (UserID,menuItemScheduleID,date) " +
                            " VALUES (?,?,?) "+
                            " ON DUPLICATE KEY UPDATE "+
                            " menuItemScheduleID = ? " );
            stmt.setInt(1,userId);
            stmt.setInt(2, menuItemScheduleID);
            stmt.setInt(3, date);
            stmt.setInt(4, menuItemScheduleID);

            System.out.println(stmt.toString());
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public ArrayList<MenuItemSchedule> getMenuItemScheduleCustomerOrder(int startDate, int endDate, int userId) {
        ArrayList menuItemScheduleList = new ArrayList<MenuItemSchedule>();
        try {
            stmt = con.prepareStatement(
                    "SELECT s.date, s.position, s.menuItemID " +
                            " FROM " + Tbl_CUSTOMERORDER + " o " +
                            " JOIN " + Tbl_MENUITEMSCHEDULE+ " s " +
                            " ON o.menuItemScheduleID = s.menuItemScheduleID " +
                            " WHERE 1 = 1" +
                            " AND o.UserID = ?" +
                            " AND o.date between ? and ? " );
            stmt.setInt(1,userId);
            stmt.setInt(2,startDate);
            stmt.setInt(3, endDate);

            System.out.println(stmt.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                MenuItemSchedule menuItemSchedule = new MenuItemSchedule();
                menuItemSchedule.setDate((Integer.valueOf(new SimpleDateFormat("YYYYMMDD").format(rs.getDate("date"))))); //huhhh hacky
                menuItemSchedule.setPosition(rs.getInt("position"));
                menuItemSchedule.setMenuItemID(rs.getInt("menuItemID"));
                menuItemScheduleList.add(menuItemSchedule);
                System.out.println(menuItemSchedule.toString());
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return menuItemScheduleList;
    }


}
