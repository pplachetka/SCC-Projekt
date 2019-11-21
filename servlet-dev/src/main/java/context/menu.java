package context;

public class menu {

    private String mealName;
    private double price;
    private int id;

    public menu(String mealName, double price) {
        this.mealName = mealName;
        this.price = price;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }


}
