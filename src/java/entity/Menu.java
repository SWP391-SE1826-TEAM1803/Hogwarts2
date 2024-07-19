package entity;

import java.util.ArrayList;

public class Menu {
    private int MenuID;
    private String Food;
    private ArrayList<String> nameFood;
    public Menu() {
    }

    public Menu(int MenuID, ArrayList<String> nameFood) {
        this.MenuID = MenuID;
        this.nameFood = nameFood;
    }

   
    
    public Menu(int MenuID, String Food) {
        this.MenuID = MenuID;
        this.Food = Food;
    }
    public ArrayList<String> getNameFood() {
        return nameFood;
    }

    public void setNameFood(ArrayList<String> nameFood) {
        this.nameFood = nameFood;
    }
    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int MenuID) {
        this.MenuID = MenuID;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String Food) {
        this.Food = Food;
    }
}
