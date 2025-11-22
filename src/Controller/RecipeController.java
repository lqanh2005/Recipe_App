package Controller;

import DAO.RecipeDAO;
import model.Recipe;
import java.util.ArrayList;
import model.Ingredient;
import model.Recipe_Ingredient;
import model.Step;

public class RecipeController {

    public RecipeDAO recipeDAO;
    private final String[] COLUMN_NAMES = {"ID", "Tên", "Mô tả"};
    public RecipeController() {
        this.recipeDAO = new RecipeDAO();
    }
    public Object[] getTableDataForUser(int userID) throws Exception {
        ArrayList<Recipe> recipes = recipeDAO.getRecipesByUserID(userID);
        Object[][] data = new Object[recipes.size()][COLUMN_NAMES.length];
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            String description = recipe.getDescription();
            if (description != null && description.length() > 50) {
                description = description.substring(0, 50) + "...";
            }
            data[i][0] = recipe.getRecipeID();
            data[i][1] = recipe.getTitle();
            data[i][2] = description;
        }
        return new Object[]{data, COLUMN_NAMES};
    }
    public boolean deleteRecipe(int recipeId) throws Exception {
        return recipeDAO.deleteRecipe(recipeId);
    }
    public Recipe getRecipeDetails(int recipeId) throws Exception {
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        if (recipe != null) {
            ArrayList<Step> steps = recipeDAO.getStepsByRecipeId(recipeId);
            recipe.setSteps(steps);
            ArrayList<Recipe_Ingredient> ingredients = recipeDAO.getIngredientsByRecipeId(recipeId);
            recipe.setIngredients(ingredients);
        }
        return recipe;
    }
    public boolean updateRecipe(Recipe recipe) throws Exception {
        int recipeId = recipe.getRecipeID();
        boolean success = recipeDAO.updateRecipeInfo(recipe);

        if (success) {
            recipeDAO.deleteRecipeIngredientsByRecipeId(recipeId);

            for (Recipe_Ingredient ri : recipe.getIngredients()) {
                Ingredient ingredient = ri.getIngredient();
                int ingredientId = recipeDAO.getOrCreateIngredientId(ingredient);
                ingredient.setIngredientID(ingredientId);
                recipeDAO.addRecipeIngredient(ri);
            }
            recipeDAO.deleteStepsByRecipeId(recipeId);
            int stepCounter = 1;
            for (Step step : recipe.getSteps()) {
                step.setStepNumber(stepCounter++);
                recipeDAO.addStep(step);
            }
        }

        return success;
    }
    public boolean addRecipe(Recipe recipe) throws Exception {

        int newRecipeId = recipeDAO.addRecipeInfo(recipe);

        if (newRecipeId > 0) {
            recipe.setRecipeID(newRecipeId);

            for (Recipe_Ingredient ri : recipe.getIngredients()) {
                Ingredient ingredient = ri.getIngredient();
                int ingredientId = recipeDAO.getOrCreateIngredientId(ingredient);
                ingredient.setIngredientID(ingredientId);
                ri.setRecipe(recipe);
                recipeDAO.addRecipeIngredient(ri);
            }

            int stepCounter = 1;
            for (Step step : recipe.getSteps()) {
                step.setStepNumber(stepCounter++);
                step.setRecipe(recipe);
                recipeDAO.addStep(step);
            }
            return true;
        }
        return false;
    }
}