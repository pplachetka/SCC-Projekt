package com.scc.speiseplan.data;

import java.util.ArrayList;

public class MenuItemScheduleCustomerOrderReceiver {
    private String token;
    private ArrayList<MenuItemSchedule> MenuItemSchedule;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<com.scc.speiseplan.data.MenuItemSchedule> getMenuItemSchedule() {
        return MenuItemSchedule;
    }

    public void setMenuItemSchedule(ArrayList<com.scc.speiseplan.data.MenuItemSchedule> menuItemSchedule) {
        MenuItemSchedule = menuItemSchedule;
    }
}
