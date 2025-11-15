/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Ingredient;
import model.Recipe;
import model.Recipe_Ingredient;
import model.Step;

/**
 *
 * @author nguye
 */
public class RecipeDetailsFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecipeDetailsFrame.class.getName());

    /**
     * Creates new form RecipeDetailsFrame
     */
    private CardLayout cardLayout;
    private final List<String> CARD_NAMES = Arrays.asList("OVERVIEW", "INGREDIENTS", "STEPS");
    private int currentCardIndex = 0;
    private Recipe recipe;
    public RecipeDetailsFrame(Recipe recipe) {
        this.recipe= recipe;
        initComponents();
        initializeCardLogic();
    }
    private void initializeCardLogic() {
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, CARD_NAMES.get(currentCardIndex));
        updateCardTitle(CARD_NAMES.get(currentCardIndex));
        populateDetails();
    }
    private void updateCardTitle(String cardName) {
        String title;
        switch (cardName) {
            case "OVERVIEW":
                title = "1. Tổng quan & Mô tả";
                break;
            case "INGREDIENTS":
                title = "2. Danh sách Nguyên liệu";
                break;
            case "STEPS":
                title = "3. Hướng dẫn các bước";
                break;
            default:
                title = "Chi tiết công thức";
        }
        lblCardTitle.setText(title);
    }
    private void populateDetails() {
        this.setTitle("Chi tiết: " + recipe.getTitle());
        lblTitle.setText(recipe.getTitle());
        lblImage.setIcon(loadImageIcon(recipe.getImageURL(), 400, 300)); 
        txtDesc.setText(recipe.getDescription());
        displayIngredients(recipe.getIngredients());
        displaySteps(recipe.getSteps());
    }
    private ImageIcon loadImageIcon(String imagePath, int maxWidth, int maxHeight) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        try {
            File file = new File(imagePath);
            if (!file.exists()) {
                System.err.println("Lỗi: Không tìm thấy file ảnh tại đường dẫn: " + imagePath);
                return null;
            }
            URL url = file.toURI().toURL();
            ImageIcon originalIcon = new ImageIcon(url);

            int originalWidth = originalIcon.getIconWidth();
            int originalHeight = originalIcon.getIconHeight();

            double widthRatio = (double) maxWidth / originalWidth;
            double heightRatio = (double) maxHeight / originalHeight;

            double ratio = Math.min(widthRatio, heightRatio);

            int newWidth = (int) (originalWidth * ratio);
            int newHeight = (int) (originalHeight * ratio);

            return new ImageIcon(originalIcon.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH));

        } catch (Exception e) {
            System.err.println("Lỗi tải hoặc resize ảnh từ đường dẫn: " + imagePath + ". Chi tiết: " + e.getMessage());
        }

        return null;
    }
    void displayIngredients(ArrayList<Recipe_Ingredient> ingredients) {
        ingredientsPanel.removeAll();

        if (ingredients == null) {
            return;
        }

        for (model.Recipe_Ingredient ri : ingredients) {
            Ingredient ing = ri.getIngredient();

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

            String detailText = String.format("%s - %d %s",ing.getName(),ri.getQuantity(),ri.getUnit());
            txtDetail.setText(detailText);

            itemPanel.add(new JScrollPane(txtDetail), java.awt.BorderLayout.CENTER);

            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
            itemPanel.setPreferredSize(new Dimension(700, 100));

            ingredientsPanel.add(itemPanel);
            ingredientsPanel.add(Box.createVerticalStrut(5));
        }

        ingredientsPanel.revalidate();
        ingredientsPanel.repaint();
    }
    
    private void displaySteps(ArrayList<Step> steps) {
        stepsPanel.removeAll();
        if (steps == null) {
            return;
        }

        for (model.Step step : steps) {
            JPanel stepPanel = new JPanel();
            stepPanel.setLayout(new BorderLayout(10, 5));
            stepPanel.setBorder(BorderFactory.createTitledBorder("Bước " + step.getStepNumber()));

            JLabel lblStepImage = new JLabel();
            lblStepImage.setIcon(loadImageIcon(step.getImageURL(), 400, 400));
            stepPanel.add(lblStepImage, BorderLayout.WEST);

            JTextArea txtDescription = new JTextArea();
            txtDescription.setEditable(false);
            txtDescription.setLineWrap(true);
            txtDescription.setWrapStyleWord(true);
            txtDescription.setText(step.getDescription());

            stepPanel.add(new JScrollPane(txtDescription), BorderLayout.CENTER);

            stepPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
            stepPanel.setPreferredSize(new Dimension(700, 200));

            stepsPanel.add(stepPanel);
            stepsPanel.add(Box.createVerticalStrut(10));
        }

        stepsCard.revalidate();
        stepsCard.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        lblCardTitle = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        overviewCard = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        ingredientsCard = new javax.swing.JPanel();
        scrollIngredients = new javax.swing.JScrollPane();
        ingredientsPanel = new javax.swing.JPanel();
        stepsCard = new javax.swing.JPanel();
        stepsContainerPanel = new javax.swing.JScrollPane();
        stepsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 700));

        headerPanel.setLayout(new java.awt.BorderLayout());

        lblCardTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCardTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardTitle.setText("jLabel1");
        lblCardTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        headerPanel.add(lblCardTitle, java.awt.BorderLayout.CENTER);

        btnPrev.setText("<=");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        headerPanel.add(btnPrev, java.awt.BorderLayout.WEST);

        btnNext.setText("=>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        headerPanel.add(btnNext, java.awt.BorderLayout.EAST);

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        cardPanel.setLayout(new java.awt.CardLayout());

        overviewCard.setLayout(new javax.swing.BoxLayout(overviewCard, javax.swing.BoxLayout.Y_AXIS));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setText("jLabel1");
        lblTitle.setAlignmentX(0.5F);
        lblTitle.setAlignmentY(0.0F);
        overviewCard.add(lblTitle);

        lblImage.setAlignmentX(0.5F);
        lblImage.setAlignmentY(0.0F);
        overviewCard.add(lblImage);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDesc.setEditable(false);
        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        overviewCard.add(jScrollPane1);

        cardPanel.add(overviewCard, "OVERVIEW");

        ingredientsCard.setLayout(new java.awt.BorderLayout());

        ingredientsPanel.setLayout(new javax.swing.BoxLayout(ingredientsPanel, javax.swing.BoxLayout.Y_AXIS));
        scrollIngredients.setViewportView(ingredientsPanel);

        ingredientsCard.add(scrollIngredients, java.awt.BorderLayout.CENTER);

        cardPanel.add(ingredientsCard, "INGREDIENTS");

        stepsCard.setLayout(new java.awt.BorderLayout());

        stepsPanel.setLayout(new javax.swing.BoxLayout(stepsPanel, javax.swing.BoxLayout.Y_AXIS));
        stepsContainerPanel.setViewportView(stepsPanel);

        stepsCard.add(stepsContainerPanel, java.awt.BorderLayout.CENTER);

        cardPanel.add(stepsCard, "STEPS");

        getContentPane().add(cardPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        currentCardIndex = (currentCardIndex - 1 + CARD_NAMES.size()) % CARD_NAMES.size();
        cardLayout.previous(cardPanel);
        updateCardTitle(CARD_NAMES.get(currentCardIndex));
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        currentCardIndex = (currentCardIndex + 1) % CARD_NAMES.size();
        cardLayout.next(cardPanel);
        updateCardTitle(CARD_NAMES.get(currentCardIndex));
    }//GEN-LAST:event_btnNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel ingredientsCard;
    private javax.swing.JPanel ingredientsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCardTitle;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel overviewCard;
    private javax.swing.JScrollPane scrollIngredients;
    private javax.swing.JPanel stepsCard;
    private javax.swing.JScrollPane stepsContainerPanel;
    private javax.swing.JPanel stepsPanel;
    private javax.swing.JTextArea txtDesc;
    // End of variables declaration//GEN-END:variables
}
