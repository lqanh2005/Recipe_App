/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lqanh
 */
public class Step {
    private int stepID;
    private int stepNumber;
    private String description;
    private String imageURL;
    private Recipe recipe;
    
    public Step(){};

    public Step(int stepID, int stepNumber, String description, String imageURL, Recipe recipe) {
        this.stepID = stepID;
        this.stepNumber = stepNumber;
        this.description = description;
        this.imageURL = imageURL;
        this.recipe = recipe;
    }

    public int getStepID() {
        return stepID;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Step{" + "stepID=" + stepID + ", stepNumber=" + stepNumber + ", description=" + description + ", imageURL=" + imageURL + ", recipe=" + recipe + '}';
    }
    
    
    
    
}
