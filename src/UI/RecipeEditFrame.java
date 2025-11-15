/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Ingredient;
import model.Recipe;
import model.Recipe_Ingredient;
import model.Step;

/**
 *
 * @author nguye
 */
public class RecipeEditFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecipeEditFrame.class.getName());
    private final Recipe recipe;
    public RecipeEditFrame(RecipeManagementFrame parent, Recipe recipe) {
        this.recipe = recipe;
        initComponents();
        txtID.setEditable(false);
        initializeTableModels();
        populateDetails();
    }
    private void populateDetails() {
        this.setTitle("Chỉnh sửa: " + recipe.getTitle());
        lblEdit.setText("Sửa công thức: " + recipe.getTitle());
        txtID.setText(String.valueOf(recipe.getRecipeID()));
        txtName.setText(recipe.getTitle());
        txtDesc.setText(recipe.getDescription());
        txtImagePath.setText(recipe.getImageURL());
        loadIngredientTable(recipe.getIngredients());
        loadStepTable(recipe.getSteps());
    }
    private void initializeTableModels() {
        DefaultTableModel ingredientModel = new DefaultTableModel(new Object[]{"ID Nguyên liệu", "Tên", "Số lượng", "Đơn vị", "Đường dẫn Ảnh"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 1 && column <= 4;
            }
        };
        tblIngredients.setModel(ingredientModel);
        DefaultTableModel stepModel = new DefaultTableModel(new Object[]{"STT", "Mô tả Bước", "Đường dẫn Ảnh"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >=1 && column <=2;
            }
        };
        tblSteps.setModel(stepModel);
    }
     
    private void loadIngredientTable(ArrayList<Recipe_Ingredient> ingredients) {
        DefaultTableModel model = (DefaultTableModel) tblIngredients.getModel();
        model.setRowCount(0);
        if (ingredients != null) {
            for (Recipe_Ingredient ri : ingredients) {
                Ingredient ing = ri.getIngredient();
                model.addRow(new Object[]{
                    ing.getIngredientID(),
                    ing.getName(),
                    ri.getQuantity(),
                    ri.getUnit(),
                    ing.getImageURL()
                });
            }
        }
    }
    
    private void loadStepTable(ArrayList<Step> steps) {
        DefaultTableModel model = (DefaultTableModel) tblSteps.getModel();
        model.setRowCount(0);
        if (steps != null) {
            for (Step step : steps) {
                model.addRow(new Object[]{
                    step.getStepNumber(),
                    step.getDescription(),
                    step.getImageURL()
                });
            }
        }
    }
    private String promptForImagePath(String dialogTitle) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        int result = fileChooser.showOpenDialog(this);
        return (result == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile().getAbsolutePath() : "";
    }
        
    private ArrayList<Recipe_Ingredient> extractIngredientsFromTable() {
        DefaultTableModel model = (DefaultTableModel) tblIngredients.getModel();
        ArrayList<Recipe_Ingredient> ingredients = new ArrayList<>();
        Recipe currentRecipeInstance = this.recipe;

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                int ingredientId = (Integer) model.getValueAt(i, 0);
                String name = (String) model.getValueAt(i, 1);
                String quantityStr = String.valueOf(model.getValueAt(i, 2));
                String unit = (String) model.getValueAt(i, 3);
                String imageUrl = (String) model.getValueAt(i, 4);

                if (name == null || name.trim().isEmpty() || quantityStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nguyên liệu hàng " + (i + 1) + " bị thiếu Tên hoặc Số lượng.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return new ArrayList<>();
                }

                Ingredient ingredient = new Ingredient();
                ingredient.setIngredientID(ingredientId);
                ingredient.setName(name.trim());
                ingredient.setImageURL(imageUrl != null ? imageUrl : "");

                Recipe_Ingredient ri = new Recipe_Ingredient();
                ri.setRecipe(currentRecipeInstance);
                ri.setIngredient(ingredient);
                ri.setQuantity(Integer.parseInt(quantityStr.trim()));
                ri.setUnit(unit != null ? unit.trim() : "");

                ingredients.add(ri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ingredients;
    }
    private ArrayList<Step> extractStepsFromTable() {
        DefaultTableModel model = (DefaultTableModel) tblSteps.getModel();
        ArrayList<Step> steps = new ArrayList<>();
        Recipe currentRecipeInstance = this.recipe;

        for (int i = 0; i < model.getRowCount(); i++) {
            int stepNum = i + 1;
            String description = (String) model.getValueAt(i, 1);
            String imageUrl = (String) model.getValueAt(i, 2);

            if (description == null || description.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bước nấu số " + stepNum + " bị thiếu Mô tả.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return new ArrayList<>();
            }

            Step step = new Step();
            step.setStepID(0);
            step.setRecipe(currentRecipeInstance);
            step.setStepNumber(stepNum);
            step.setDescription(description.trim());
            step.setImageURL(imageUrl != null ? imageUrl : "");

            steps.add(step);
        }
        return steps;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblEdit = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtImagePath = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIngredients = new javax.swing.JTable();
        btnAddIngredient = new javax.swing.JButton();
        btnDelIngredient = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSteps = new javax.swing.JTable();
        btnAddStep = new javax.swing.JButton();
        btnDelStep = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 700));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblEdit.setText("Sửa công thức");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 56, 0, 0);
        getContentPane().add(lblEdit, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 44, 0, 0);
        getContentPane().add(txtID, gridBagConstraints);

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 157;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 6, 0, 0);
        getContentPane().add(txtName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 334;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 6, 0, 42);
        getContentPane().add(txtImagePath, gridBagConstraints);

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 692;
        gridBagConstraints.ipady = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 44, 0, 42);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        tblIngredients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên nguyên liệu", "Đường dẫn ảnh"
            }
        ));
        jScrollPane2.setViewportView(tblIngredients);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 692;
        gridBagConstraints.ipady = 116;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 44, 0, 42);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        btnAddIngredient.setText("Thêm nguyên liệu");
        btnAddIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddIngredientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 36, 0, 0);
        getContentPane().add(btnAddIngredient, gridBagConstraints);

        btnDelIngredient.setText("Xóa nguyên liệu");
        btnDelIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelIngredientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 58, 0, 0);
        getContentPane().add(btnDelIngredient, gridBagConstraints);

        tblSteps.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Đường dẫn ảnh", "Mô tả bước"
            }
        ));
        jScrollPane3.setViewportView(tblSteps);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 692;
        gridBagConstraints.ipady = 316;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 44, 0, 42);
        getContentPane().add(jScrollPane3, gridBagConstraints);

        btnAddStep.setText("Thêm bước");
        btnAddStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStepActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 46, 0, 0);
        getContentPane().add(btnAddStep, gridBagConstraints);

        btnDelStep.setText("Xóa bước");
        btnDelStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelStepActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 78, 0, 0);
        getContentPane().add(btnDelStep, gridBagConstraints);

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 16, 15, 0);
        getContentPane().add(btnSave, gridBagConstraints);

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 15, 0);
        getContentPane().add(btnCancel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnDelStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelStepActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblSteps.getModel();
        int selectedRow = tblSteps.getSelectedRow();

        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bước nấu cần xóa.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDelStepActionPerformed

    private void btnAddIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddIngredientActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(this, "Nhập Tên Nguyên liệu:", "Thêm Nguyên liệu", JOptionPane.QUESTION_MESSAGE);
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        String quantity = JOptionPane.showInputDialog(this, "Nhập Số lượng:", "Thêm Nguyên liệu", JOptionPane.QUESTION_MESSAGE);
        if (quantity == null || quantity.trim().isEmpty()) {
            return;
        }
        String unit = JOptionPane.showInputDialog(this, "Nhập Đơn vị (vd: g, ml):", "Thêm Nguyên liệu", JOptionPane.QUESTION_MESSAGE);
        if (unit == null) {
            return;
        }
        String imagePath = promptForImagePath("Chọn Ảnh Nguyên liệu");

        DefaultTableModel model = (DefaultTableModel) tblIngredients.getModel();
        model.addRow(new Object[]{0, name, quantity, unit, imagePath});
    
    }//GEN-LAST:event_btnAddIngredientActionPerformed

    private void btnDelIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelIngredientActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblIngredients.getModel();
        int selectedRow = tblIngredients.getSelectedRow();

        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu cần xóa.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDelIngredientActionPerformed

    private void btnAddStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStepActionPerformed
        // TODO add your handling code here:
        String description = JOptionPane.showInputDialog(this, "Nhập Mô tả Bước Nấu:", "Thêm Bước Nấu", JOptionPane.QUESTION_MESSAGE);
        if (description == null || description.trim().isEmpty()) {
            return;
        }
        String imagePath = promptForImagePath("Chọn Ảnh Bước Nấu");
        DefaultTableModel model = (DefaultTableModel) tblSteps.getModel();
        int nextStepNumber = model.getRowCount() + 1;
        model.addRow(new Object[]{nextStepNumber, description, imagePath});
    }//GEN-LAST:event_btnAddStepActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String title = txtName.getText().trim();
        String description = txtDesc.getText().trim();
        String imagePath = txtImagePath.getText().trim();

        ArrayList<Recipe_Ingredient> ingredients = extractIngredientsFromTable();
        ArrayList<Step> steps = extractStepsFromTable();

        if (title.isEmpty() || description.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ Tên, Mô tả, Nguyên liệu và Bước nấu.", "Lỗi Lưu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        recipe.setTitle(title);
        recipe.setDescription(description);
        recipe.setImageURL(imagePath);
        recipe.setIngredients(new ArrayList<>(ingredients));
        recipe.setSteps(new ArrayList<>(steps));

        try {
            boolean success = new Controller.RecipeController().updateRecipe(recipe);

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật công thức thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật công thức thất bại (DAO trả về false).", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            logger.severe("Lỗi hệ thống khi cập nhật công thức: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống khi lưu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddIngredient;
    private javax.swing.JButton btnAddStep;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelIngredient;
    private javax.swing.JButton btnDelStep;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEdit;
    private javax.swing.JTable tblIngredients;
    private javax.swing.JTable tblSteps;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtImagePath;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
