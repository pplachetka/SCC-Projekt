package com.scc.speiseplan.data;

import java.math.BigDecimal;

public class MenuItem {

    private String MenuItemID;
    private String Description;
    private BigDecimal Costs;

    public String getMenuItemID() {
        return MenuItemID;
    }

    public void setMenuItemID(String menuItemID) {
        MenuItemID = menuItemID;
    }



    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public BigDecimal getCosts() {
        return Costs;
    }

    public void setCosts(BigDecimal costs) {
        Costs = costs;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "MenuItemID='" + MenuItemID + '\'' +
                ", Description='" + Description + '\'' +
                ", Costs=" + Costs +
                '}';
    }
}
