package com.example.nicole.e_grocery;

/**
 * Created by Nicole on 3/8/2016.
 */
public class RowItemGroceries {
    private int image;
    private String groceryName;
    private Double groceryPrice;
    private int groceryId;


    public RowItemGroceries(int image, String Name, int Id, Double price) {
        this.groceryId = Id;
        this.image = image;
        this.groceryName = Name;
        this.groceryPrice = price;

    }

    public Double getGroceryPrice() {
        return groceryPrice;
    }

    public void setGroceryPrice(Double groceryPrice) {
        this.groceryPrice = groceryPrice;
    }

    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMenuName() {
        return groceryName;
    }

    public void setMenuName(String menuName) {
        this.groceryName = menuName;
    }
}
