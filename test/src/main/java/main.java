package main.java;

import main.java.data.MenuItem;
import main.java.data.MyDBHandler;
import main.java.data.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws IOException {



        //// LOGIN bzw. vor jeder anderen Abfrage Authentifizierung
        // Authentification
        System.out.println(new MyDBHandler().isUser(1,"admin","2"));
        // Authorization
        System.out.println(new MyDBHandler().isAdmin(1));


        // Daten holen //

        // DBHANDLER holt sich den benötigten Datensatz und schiebts in ne POJO,  Objectmapper wandelt POJO objekte in JSON Format um,

        //// USER ////////
        // GET
        String json_user = new ObjectMapper().writeValueAsString(new MyDBHandler().getUserData("1", "admin"));
        System.out.println(json_user);

        /////// MENUITEMS /////////
        // MyDBHandler con_menu = new MyDBHandler();
        // GET
        String json_menulist = new ObjectMapper().writeValueAsString(new MyDBHandler().getMenuItemList());
        System.out.println(json_menulist);
        // SET

    }
}
