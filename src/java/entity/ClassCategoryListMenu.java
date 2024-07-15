package entity;

public class ClassCategoryListMenu {
    private String cateName;
    private String date;
    private String meal;
    private String food;

    public ClassCategoryListMenu() {
    }

    public ClassCategoryListMenu(String cateName, String date, String meal, String food) {
        this.cateName = cateName;
        this.date = date;
        this.meal = meal;
        this.food = food;
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
