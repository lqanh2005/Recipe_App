package Controller;

import DAO.RecipeDAO;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import model.Recipe;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import model.Ingredient;
import model.Recipe_Ingredient;
import model.Step;

public class RecipeController {

    private final RecipeDAO recipeDAO;
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
    public Object[] getIngredientTableData(ArrayList<Recipe_Ingredient> ingredients) {
        String[] INGREDIENT_COLUMNS = {"Ảnh", "Nguyên liệu", "Số lượng", "Đơn vị"};
        Object[][] data = new Object[ingredients.size()][INGREDIENT_COLUMNS.length];

        for (int i = 0; i < ingredients.size(); i++) {
            Recipe_Ingredient ri = ingredients.get(i);
            Ingredient ing = ri.getIngredient();

            data[i][0] = ing.getImageURL();
            data[i][1] = ing.getName();
            data[i][2] = ri.getQuantity();
            data[i][3] = ri.getUnit();
        }
        return new Object[]{data, INGREDIENT_COLUMNS};
    }
    public ImageIcon getScaledImageIcon(String imagePath, int width, int height) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        try {
            File file = new File(imagePath);
            URL url = file.toURI().toURL();
            ImageIcon icon = new ImageIcon(url);
            if (icon.getIconWidth() > 0) {
                return new ImageIcon(
                        icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)
                );
            }
        } catch (MalformedURLException e) {
        }
        return null;
    }
    
}