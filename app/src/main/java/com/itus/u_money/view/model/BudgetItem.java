package com.itus.u_money.view.model;

public class BudgetItem {
    int icon;
    String type;
    long amount;

    public BudgetItem(int icon, String name, long amount) {
        this.icon = icon;
        this.type = name;
        this.amount = amount;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
