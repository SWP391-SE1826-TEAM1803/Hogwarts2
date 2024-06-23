package entity;

import java.sql.Date;

public class ClassCategoryMenu {
    private int CateID;
    private int MenuID;
    private String Date;
    private String Meal;

    public ClassCategoryMenu() {
    }

    public ClassCategoryMenu(int CateID, int MenuID, String Date, String Meal) {
        this.CateID = CateID;
        this.MenuID = MenuID;
        this.Date = Date;
        this.Meal = Meal;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int MenuID) {
        this.MenuID = MenuID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getMeal() {
        return Meal;
    }

    public void setMeal(String Meal) {
        this.Meal = Meal;
    }
}
