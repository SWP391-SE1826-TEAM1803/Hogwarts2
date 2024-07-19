package entity;

import java.util.ArrayList;

public class ClassCategoryListMenu {
    private String cateName;
    private String date;
    private String meal;
    private String food;
    private ArrayList nameFood;
    public ClassCategoryListMenu() {
    }

    public ClassCategoryListMenu(String cateName, String date, String meal, String food) {
        this.cateName = cateName;
        this.date = date;
        this.meal = meal;
        this.food = food;
    }

    public ClassCategoryListMenu(String cateName, String date, String meal, ArrayList nameFood) {
        this.cateName = cateName;
        this.date = date;
        this.meal = meal;
        this.nameFood = nameFood;
    }

    public ArrayList getNameFood() {
        return nameFood;
    }

    public void setNameFood(ArrayList nameFood) {
        this.nameFood = nameFood;
    }

    
    

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}
