/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import model.Recipe;
import model.User;
import java.sql.*;
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
    public boolean updateRecipeInfo(Recipe recipe) throws SQLException {
        String sql = "UPDATE Recipe SET title = ?, imageURL = ?, description = ? WHERE recipeID = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        connection = DBConnection.getConnection();
        ps = connection.prepareStatement(sql);
        ps.setString(1, recipe.getTitle());
        ps.setString(2, recipe.getImageURL());
        ps.setString(3, recipe.getDescription());
        ps.setInt(4, recipe.getRecipeID());
        return ps.executeUpdate() > 0;
    }
    public int getOrCreateIngredientId(Ingredient ingredient) throws SQLException {
        String selectSql = "SELECT ingredientID, imageURL FROM Ingredient WHERE name = ?";
        Connection connection = null;
        PreparedStatement selectPs = null;
        ResultSet rs = null;
        connection = DBConnection.getConnection();
        selectPs = connection.prepareStatement(selectSql);
        selectPs.setString(1, ingredient.getName());
        rs = selectPs.executeQuery();

        if (rs.next()) {
            int existingId = rs.getInt("ingredientID");
            String existingImageUrl = rs.getString("imageURL");
            if (!ingredient.getImageURL().equals(existingImageUrl)) {
                updateIngredientImageUrl(existingId, ingredient.getImageURL());
            }
            return existingId;
        } else {
            return addIngredient(ingredient, connection);
        }
    }
    private void updateIngredientImageUrl(int ingredientID, String imageUrl) throws SQLException {
        String updateSql = "UPDATE Ingredient SET imageURL = ? WHERE ingredientID = ?";
        Connection connection = null;
        PreparedStatement updatePs = null;
        try {
            connection = DBConnection.getConnection();
            updatePs = connection.prepareStatement(updateSql);
            updatePs.setString(1, imageUrl);
            updatePs.setInt(2, ingredientID);
            updatePs.executeUpdate();
        } finally {
            try {
                if (updatePs != null) {
                    updatePs.close();
                }
            } catch (SQLException e) {
            }
            DBConnection.closeConnection(connection);
        }
    }
    private int addIngredient(Ingredient ingredient, Connection existingConnection) throws SQLException {
        String insertSql = "INSERT INTO Ingredient (name, imageURL) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement insertPs = null;
        ResultSet generatedKeys = null;
        int newId = -1;
            connection = DBConnection.getConnection();
            insertPs = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            insertPs.setString(1, ingredient.getName());
            insertPs.setString(2, ingredient.getImageURL());

            insertPs.executeUpdate();

            generatedKeys = insertPs.getGeneratedKeys();
            if (generatedKeys.next()) {
                newId = generatedKeys.getInt(1);
            }
            return newId;
    }
    public boolean deleteRecipeIngredientsByRecipeId(int recipeId) throws SQLException {
        String sql = "DELETE FROM Recipe_Ingredient WHERE recipeID = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, recipeId);
            ps.executeUpdate();
            return true;
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) {}
            DBConnection.closeConnection(connection);
        }
    }
    public boolean addRecipeIngredient(Recipe_Ingredient ri) throws SQLException {
        String sql = "INSERT INTO Recipe_Ingredient (recipeID, ingredientID, quantity, unit) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement ps = null;
        if (ri.getRecipe() == null || ri.getIngredient() == null) {
            return false;
        }
        connection = DBConnection.getConnection();
        ps = connection.prepareStatement(sql);

        ps.setInt(1, ri.getRecipe().getRecipeID());
        ps.setInt(2, ri.getIngredient().getIngredientID());
        ps.setInt(3, ri.getQuantity());
        ps.setString(4, ri.getUnit());

        return ps.executeUpdate() > 0;
    }
    
    public boolean deleteStepsByRecipeId(int recipeId) throws SQLException {
        String sql = "DELETE FROM Step WHERE recipeID = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, recipeId);
            ps.executeUpdate();
            return true;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
            DBConnection.closeConnection(connection);
        }
    }
    
    public boolean addStep(Step step) throws SQLException {
        String sql = "INSERT INTO Step (stepNumber, description, imageURL, recipeID) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement ps = null;

        if (step.getRecipe() == null) {
            return false;
        }

        connection = DBConnection.getConnection();
        ps = connection.prepareStatement(sql);

        ps.setInt(1, step.getStepNumber());
        ps.setString(2, step.getDescription());
        ps.setString(3, step.getImageURL());
        ps.setInt(4, step.getRecipe().getRecipeID());

        return ps.executeUpdate() > 0;
    }
    public int addRecipeInfo(Recipe recipe) throws SQLException {
        String sql = "INSERT INTO Recipe (title, imageURL, description, userID) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int newRecipeId = -1;
        if (recipe.getUser() == null) {
            System.err.println("Lỗi: Recipe không có UserID để thêm công thức.");
            return -1;
        }
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, recipe.getTitle());
            ps.setString(2, recipe.getImageURL());
            ps.setString(3, recipe.getDescription());
            ps.setInt(4, recipe.getUser().getUserID());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    newRecipeId = generatedKeys.getInt(1);
                }
            }

            return newRecipeId;

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm công thức mới: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        } 
    }
}
