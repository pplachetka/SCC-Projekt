package com.scc.speiseplan.data;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {



        //// LOGIN bzw. vor jeder anderen Abfrage Authentifizierung
        // Authentification
        System.out.println("Test Authentification");
        System.out.println(new MyDBHandler().isUser(1,"admin",""));
        // Authorization
        System.out.println("Test Authorization");
        System.out.println(new MyDBHandler().isAdmin(1));

        //LOGIN
        System.out.println("Test set token");
        new MyDBHandler().setToken(1, "s");

        // get Userdata
        System.out.println("Test get userdata");
        String json_user = new ObjectMapper().writeValueAsString(new MyDBHandler().getUserData(1));
        System.out.println(json_user);

        // Daten holen //

        // DBHANDLER holt sich den benötigten Datensatz und schiebts in ne POJO,  Objectmapper wandelt POJO objekte in JSON Format um,

        //// USER ////////
        // GET

        /////// MENUITEMS /////////
        // MyDBHandler con_menu = new MyDBHandler();
        // GET
     //   String json_menulist = new ObjectMapper().writeValueAsString(new MyDBHandler().getMenuItemList());
    //    System.out.println(json_menulist);
        // SET

    }
}
