package com.scc.speiseplan.testClient;

import com.scc.speiseplan.data.MyDBHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class DBtest {

    public static void main(String[] args) throws IOException {

        //direct access to DB
        String mysql_url = "jdbc:mysql://localhost:3306/scc";


        ////// LOGIN bzw. vor jeder anderen Abfrage Authentifizierung ////////
        // Authentification
        System.out.println("Test Authentification");
        System.out.println(new MyDBHandler(mysql_url).login(1,"admin"));
        // Authorization
        System.out.println("Test Authorization");
        System.out.println(new MyDBHandler(mysql_url).isAdmin("as"));

        //LOGIN
        //System.out.println("Test set token");
        //new MyDBHandler(mysql_url).setToken(1, "s");

        // get Userdata
        System.out.println("Test get userdata");
        String json_user = new ObjectMapper().writeValueAsString(new MyDBHandler(mysql_url).getUserDataByToken("44khbfcj5gkrg0ee5oaj696bope"));
        System.out.println(json_user);


        /////// MENUITEMS /////////

        ///// create / update / delete MenuItem /////////

        new MyDBHandler(mysql_url).insertMenuItem("huehnchen",new BigDecimal("3.00"));
        new MyDBHandler(mysql_url).insertMenuItem("huehnchen",new BigDecimal("3.00"));
        new MyDBHandler(mysql_url).deleteMenuItem(4);
        new MyDBHandler(mysql_url).updateMenuItem(5,"kein hühnchen", new BigDecimal("2.00"));

        // GET
        String json_menulist =new ObjectMapper().writeValueAsString(new MyDBHandler(mysql_url).getMenuItemList());
        System.out.println(json_menulist);

        /////// MENUSCHEDULE //////
        //// create / update MenuItemSchedule ////
        new MyDBHandler(mysql_url).setMenuItemSchedule(20190101,1,1);
        new MyDBHandler(mysql_url).setMenuItemSchedule(20190101,1,2);
        new MyDBHandler(mysql_url).setMenuItemSchedule(20190101,2,2);

        System.out.println("Test get MenuItemSchedule");
        String json_schedule = new ObjectMapper().writeValueAsString(new MyDBHandler(mysql_url).getMenuItemSchedule(20190101,20200202));
        System.out.println(json_schedule);


        ////// Admin Scheduling  ///////


        ///// User scheduling //////
        
    }
}
