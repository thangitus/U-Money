package com.itus.u_money.view.model;

import java.util.Date;

public class EventItem {
    int icon;
    String name;
    Date date;

    public EventItem() {}

    public EventItem(int icon, String name, Date date) {
        this.icon = icon;
        this.name = name;
        this.date = date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
