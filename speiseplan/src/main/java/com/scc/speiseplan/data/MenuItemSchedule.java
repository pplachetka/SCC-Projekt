package com.scc.speiseplan.data;


import java.math.BigDecimal;

public class MenuItemSchedule {

    private int menuItemScheduleID;
    private int date;
    private int position;
    private int menuItemID;
    private String description;
    private BigDecimal costs;

    public int getMenuItemScheduleID() {
        return menuItemScheduleID;
    }

    public void setMenuItemScheduleID(int menuItemScheduleID) {
        this.menuItemScheduleID = menuItemScheduleID;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(int menuItemID) {
        this.menuItemID = menuItemID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCosts() {
        return costs;
    }

    public void setCosts(BigDecimal costs) {
        this.costs = costs;
    }

    @Override
    public String toString() {
        return "MenuItemSchedule{" +
                "menuItemScheduleID=" + menuItemScheduleID +
                ", date=" + date +
                ", position=" + position +
                ", menuItemID=" + menuItemID +
                '}';
    }
}
