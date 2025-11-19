/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import model.Recipe;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import model.Ingredient;
import model.Recipe_Ingredient;
import model.Step;

/**
 *
 * @author lqanh
 */

public class RecipeDAO {
    public ArrayList<Recipe> getRecipesByUserID(int userID) {
        String sql= "SELECT r.recipeID, r.title, r.imageURL, r.description, u.userID, u.username FROM Recipe r JOIN User u ON r.userID = u.userID WHERE r.userID = ?";
        ArrayList<Recipe> recipes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("username"));

                Recipe recipe = new Recipe();
                recipe.setRecipeID(rs.getInt("recipeID"));
                recipe.setTitle(rs.getString("title"));
                recipe.setImageURL(rs.getString("imageURL"));
                recipe.setDescription(rs.getString("description"));
                recipe.setUser(user);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy Recipe theo User ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {}
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {}
            DBConnection.closeConnection(connection);
        }
        return recipes;
    }
    public boolean deleteRecipe(int recipeId) throws SQLException {
        String sql = "DELETE FROM Recipe WHERE recipeID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, recipeId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; 
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException e) {}
            DBConnection.closeConnection(connection);
        }
    }
    public Recipe getRecipeById(int recipeId) throws SQLException {
        String sql = "SELECT r.recipeID, r.title, r.imageURL, r.description, u.userID, u.username FROM Recipe r JOIN User u ON r.userID = u.userID WHERE r.recipeID = ?";
        Recipe recipe = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, recipeId);
        rs = preparedStatement.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setUserID(rs.getInt("userID"));
            user.setUsername(rs.getString("username"));
            recipe = new Recipe();
            recipe.setRecipeID(rs.getInt("recipeID"));
            recipe.setTitle(rs.getString("title"));
            recipe.setImageURL(rs.getString("imageURL"));
            recipe.setDescription(rs.getString("description"));
            recipe.setUser(user);
        }
        return recipe;
    }
    public ArrayList<Step> getStepsByRecipeId(int recipeId) throws SQLException {
        String sql= "SELECT stepID, stepNumber, description, imageURL FROM Step WHERE recipeID = ? ORDER BY stepNumber ASC";
        ArrayList<Step> steps = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, recipeId);
        rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Step step = new Step();
            step.setStepID(rs.getInt("stepID"));
            step.setStepNumber(rs.getInt("stepNumber"));
            step.setDescription(rs.getString("description"));
            step.setImageURL(rs.getString("imageURL"));
            steps.add(step);
        }
        return steps;
    }
    public ArrayList<Recipe_Ingredient> getIngredientsByRecipeId(int recipeId) throws SQLException {
        String sql= "SELECT ri.quantity, ri.unit, i.ingredientID, i.name, i.imageURL FROM Recipe_Ingredient ri JOIN Ingredient i ON ri.ingredientID = i.ingredientID WHERE ri.recipeID = ?";
        ArrayList<Recipe_Ingredient> ingredients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, recipeId);
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientID(rs.getInt("ingredientID"));
            ingredient.setName(rs.getString("name"));
            ingredient.setImageURL(rs.getString("imageURL"));
            Recipe_Ingredient ri = new Recipe_Ingredient();
            ri.setQuantity(rs.getInt("quantity"));
            ri.setUnit(rs.getString("unit"));
            ri.setIngredient(ingredient);
            ingredients.add(ri);
        }
        return ingredients;
    }
    public List<String> getRecipeFromDB(String query){
        List<String> result = new ArrayList<>();
        String sql = "SELECT title FROM recipe WHERE title LIKE ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

           stmt.setString(1, "%" + query + "%"); // tìm tên chứa query
           ResultSet rs = stmt.executeQuery();

           while (rs.next()) {
               result.add(rs.getString("title"));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
        return result;
    }
    public List<String> searchRecipesByIngredient(String keyword) {
        List<String> result = new ArrayList<>();

        String sql = """
            SELECT DISTINCT r.title
            FROM Recipe r
            JOIN Recipe_Ingredient ri ON r.recipeID = ri.recipeID
            JOIN Ingredient i ON ri.ingredientID = i.ingredientID
            WHERE i.name LIKE ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int getRecipeCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS c FROM Recipe";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("c");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public int getRandomRecipeId() {
        int count = getRecipeCount();
        if (count == 0) return -1;

        return (int)(Math.random() * count) + 1;  // random 1 → count
    }
}
