package com.example.nicole.e_grocery;

/**
 * Created by Magerer on 2/16/2016.
 */
public class RowItem {
    private int image;
    private String menuName;


    public RowItem(int image, String Name) {
        this.image = image;
        this.menuName = Name;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
