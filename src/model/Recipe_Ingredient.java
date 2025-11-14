/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Recipe_Ingredient {
    private int quantity;
    private String unit;
    private Recipe recipe;
    private Ingredient ingredient;
    
    public Recipe_Ingredient(){};

    public Recipe_Ingredient(int quantity, String unit, Recipe recipe, Ingredient ingredient) {
        this.quantity = quantity;
        this.unit = unit;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "Recipe_Ingredient{" + "quantity=" + quantity + ", unit=" + unit + ", recipe=" + recipe + ", ingredient=" + ingredient + '}';
    }

    
}
