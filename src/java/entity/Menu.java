package entity;

public class Menu {
    private int MenuID;
    private String Food;

    public Menu() {
    }

    public Menu(int MenuID, String Food) {
        this.MenuID = MenuID;
        this.Food = Food;
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
