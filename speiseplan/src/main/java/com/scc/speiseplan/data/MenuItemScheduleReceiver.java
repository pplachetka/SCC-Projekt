package com.scc.speiseplan.data;

import java.util.ArrayList;

public class MenuItemScheduleReceiver {

    private int token;
    private ArrayList<MenuItemSchedule> MenuItemSchedule;

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public ArrayList<com.scc.speiseplan.data.MenuItemSchedule> getMenuItemSchedule() {
        return MenuItemSchedule;
    }

    public void setMenuItemSchedule(ArrayList<com.scc.speiseplan.data.MenuItemSchedule> menuItemSchedule) {
        MenuItemSchedule = menuItemSchedule;
    }

    @Override
    public String toString() {
        return "MenuItemScheduleReceiver{" +
                "token=" + token +
                ", MenuItemSchedule=" + MenuItemSchedule +
                '}';
    }
}
