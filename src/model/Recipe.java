/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author lqanh
 */
public class Recipe {
        private int recipeID;
        private String title;
        private String imageURL;
        private String description;
        private User user;

        private ArrayList<Step> steps;
        private ArrayList<Recipe_Ingredient> ingredients;

        public Recipe() {
            this.steps = new ArrayList<>();
            this.ingredients = new ArrayList<>();
        }

        public Recipe(int recipeID, String title, String imageURL, String description, User user) {
            this.recipeID = recipeID;
            this.title = title;
            this.imageURL = imageURL;
            this.description = description;
            this.user = user;
            this.steps = new ArrayList<>();
            this.ingredients = new ArrayList<>();
        }

        // Getters and Setters
        public int getRecipeID() {
            return recipeID;
        }

        public void setRecipeID(int recipeID) {
            this.recipeID = recipeID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public ArrayList<Step> getSteps() {
            return steps;
        }

        public void setSteps(ArrayList<Step> steps) {
            this.steps = steps;
        }

        public ArrayList<Recipe_Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(ArrayList<Recipe_Ingredient> ingredients) {
            this.ingredients = ingredients;        }
    @Override
    public String toString() {
        return "Recipe{" + "recipeID=" + recipeID + ", title=" + title + ", description=" + description + ", imageURL=" + imageURL + ", user=" + user + '}';
    }
   
}
