package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import model.Recipe;
import model.Step;

public class RecipeDetailFrame extends javax.swing.JFrame {
    private final Recipe recipe;
    public RecipeDetailFrame(Recipe recipe) {
        this.recipe = recipe;
        initComponents();
        this.setLocationRelativeTo(null);
        if (this.recipe != null) {
            populateDetails();
        } else {
            this.setTitle("Lỗi: Không tìm thấy công thức");
        }
    }
    private void populateDetails() {
        this.setTitle("Chi tiết: " + recipe.getTitle());
        lblTitle.setText(recipe.getTitle());
        lblTitle1.setIcon(loadImageIcon(recipe.getImageURL(), 400, 300));
        lblTitle1.setText(null);
        jTextArea2.setText(recipe.getDescription());
        txtDescription.setViewportView(jTextArea2);
        displayIngredients(recipe.getIngredients());
        displaySteps(recipe.getSteps());
    }
    private ImageIcon loadImageIcon(String imagePath, int width, int height) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        try {
            File file = new File(imagePath);
            URL url = file.toURI().toURL();
            ImageIcon icon = new ImageIcon(url);

            if (icon.getIconWidth() > 0) {
                return new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)
                );
            }
        } catch (Exception e) {
            System.err.println("Lỗi tải ảnh từ đường dẫn: " + imagePath);
        }
        return null;
    }
    void displayIngredients(java.util.ArrayList<model.Recipe_Ingredient> ingredients) {
        for (model.Recipe_Ingredient ri : ingredients) {
            model.Ingredient ing = ri.getIngredient();
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new java.awt.BorderLayout(10, 5)); 
            itemPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));

            JLabel lblImage = new JLabel();
            lblImage.setIcon(loadImageIcon(ing.getImageURL(), 40, 40));
            itemPanel.add(lblImage, java.awt.BorderLayout.WEST);

            JTextArea txtDetail = new JTextArea();
            txtDetail.setEditable(false);
            txtDetail.setLineWrap(true);
            txtDetail.setWrapStyleWord(true);

            String detailText = String.format("%s - %d %s",
                    ing.getName(),
                    ri.getQuantity(),
                    ri.getUnit());
            txtDetail.setText(detailText);

            itemPanel.add(new JScrollPane(txtDetail), java.awt.BorderLayout.CENTER);
            itemPanel.setPreferredSize(new Dimension(700,100));
            itemPanel.setMaximumSize(new Dimension(700,100));
            ingredientsPanel.add(itemPanel);
            ingredientsPanel.add(javax.swing.Box.createVerticalStrut(5));
        }

        ingredientsPanel.revalidate();
        ingredientsPanel.repaint();
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private void displaySteps(ArrayList<Step> steps) {
        for (Step step : steps) {
            JPanel stepPanel = new JPanel();
            stepPanel.setLayout(new BorderLayout(10, 5));
            stepPanel.setBorder(BorderFactory.createTitledBorder("Bước " + step.getStepNumber()));

            JLabel lblStepImage = new JLabel();
            lblStepImage.setIcon(loadImageIcon(step.getImageURL(), 100, 100)); 
            stepPanel.add(lblStepImage, BorderLayout.WEST);

            JTextArea txtDescription = new JTextArea();
            txtDescription.setEditable(false);
            txtDescription.setLineWrap(true);
            txtDescription.setWrapStyleWord(true);
            txtDescription.setText(step.getDescription());
            stepPanel.add(new JScrollPane(txtDescription), BorderLayout.CENTER);
            stepPanel.setPreferredSize(new Dimension(700,200));
            stepPanel.setMaximumSize(new Dimension(700,200));
            stepsContainerPanel.add(stepPanel);
            stepsContainerPanel.add(Box.createVerticalStrut(10));
        }
        stepsContainerPanel.revalidate();
        stepsContainerPanel.repaint();
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainContentPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        ingredientsPanel = new javax.swing.JPanel();
        stepsContainerPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 700));

        jScrollPane1.setOpaque(true);

        mainContentPanel.setAlignmentX(0.0F);
        mainContentPanel.setAlignmentY(0.0F);
        mainContentPanel.setMinimumSize(new java.awt.Dimension(700, 700));
        mainContentPanel.setLayout(new javax.swing.BoxLayout(mainContentPanel, javax.swing.BoxLayout.Y_AXIS));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Tên món ăn");
        lblTitle.setToolTipText("");
        lblTitle.setAlignmentX(0.5F);
        lblTitle.setAlignmentY(0.0F);
        lblTitle.setOpaque(true);
        lblTitle.setPreferredSize(new java.awt.Dimension(90, 20));
        mainContentPanel.add(lblTitle);

        lblTitle1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTitle1.setAlignmentX(0.5F);
        lblTitle1.setAlignmentY(0.0F);
        lblTitle1.setAutoscrolls(true);
        lblTitle1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTitle1.setMaximumSize(new java.awt.Dimension(400, 300));
        lblTitle1.setMinimumSize(new java.awt.Dimension(400, 300));
        lblTitle1.setName(""); // NOI18N
        lblTitle1.setOpaque(true);
        lblTitle1.setPreferredSize(new java.awt.Dimension(400, 300));
        mainContentPanel.add(lblTitle1);

        txtDescription.setMaximumSize(new java.awt.Dimension(700, 100));
        txtDescription.setPreferredSize(new java.awt.Dimension(700, 120));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        txtDescription.setViewportView(jTextArea2);

        mainContentPanel.add(txtDescription);

        ingredientsPanel.setAlignmentY(0.0F);
        ingredientsPanel.setMaximumSize(new java.awt.Dimension(9999, 9999));
        ingredientsPanel.setLayout(new javax.swing.BoxLayout(ingredientsPanel, javax.swing.BoxLayout.Y_AXIS));
        mainContentPanel.add(ingredientsPanel);

        stepsContainerPanel.setMaximumSize(new java.awt.Dimension(9999, 9999));
        stepsContainerPanel.setLayout(new javax.swing.BoxLayout(stepsContainerPanel, javax.swing.BoxLayout.Y_AXIS));
        mainContentPanel.add(stepsContainerPanel);

        jScrollPane1.setViewportView(mainContentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ingredientsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JPanel mainContentPanel;
    private javax.swing.JPanel stepsContainerPanel;
    private javax.swing.JScrollPane txtDescription;
    // End of variables declaration//GEN-END:variables
}
