/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lqanh
 */
public class Ingredient {
    private int ingredientID;
    private String name;
    private String imageURL;
    
    public Ingredient(){};

    public Ingredient(int ingredientID, String name, String imageURL) {
        this.ingredientID = ingredientID;
        this.name = name;
        this.imageURL = imageURL;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "ingredientID=" + ingredientID + ", name=" + name + ", imageURL=" + imageURL + '}';
    }
    
}
