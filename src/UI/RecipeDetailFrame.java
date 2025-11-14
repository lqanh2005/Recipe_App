package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.Ingredient;
import model.Recipe;
import model.Recipe_Ingredient;
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
                return new ImageIcon(
                        icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)
                );
            }
        } catch (Exception e) {
            System.err.println("Lỗi tải ảnh từ đường dẫn: " + imagePath);
        }
        return null;
    }
    private void displayIngredients(ArrayList<Recipe_Ingredient> ingredients) {
        String[] columnNames = {"Ảnh", "Nguyên liệu", "Số lượng", "Đơn vị"};
        Object[][] data = new Object[ingredients.size()][columnNames.length];
        for (int i = 0; i < ingredients.size(); i++) {
            Recipe_Ingredient ri = ingredients.get(i);
            Ingredient ing = ri.getIngredient();
            data[i][0] = ing.getImageURL();
            data[i][1] = ing.getName();
            data[i][2] = ri.getQuantity();
            data[i][3] = ri.getUnit();
        }
        tblIngredients.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        tblIngredients.getColumnModel().getColumn(0).setCellRenderer(new IngredientImageRenderer());
        tblIngredients.setRowHeight(50);
        tblIngredients.getColumnModel().getColumn(0).setPreferredWidth(50);
        int tableHeight = tblIngredients.getRowCount() * tblIngredients.getRowHeight() + tblIngredients.getTableHeader().getPreferredSize().height;

        ingredientsScrollPane.setPreferredSize(new java.awt.Dimension(
                ingredientsScrollPane.getWidth(),
                tableHeight
        ));

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private void displaySteps(ArrayList<Step> steps) {
        stepsContainerPanel.setLayout(new BoxLayout(stepsContainerPanel, BoxLayout.Y_AXIS));
        for (Step step : steps) {
            JPanel stepPanel = new JPanel();
            stepPanel.setLayout(new BorderLayout(10, 5));
            stepPanel.setBorder(BorderFactory.createTitledBorder("Bước " + step.getStepNumber()));

            JLabel lblStepImage = new JLabel();
            lblStepImage.setIcon(loadImageIcon(step.getImageURL(), 100, 100)); 
            lblStepImage.setHorizontalAlignment(SwingConstants.CENTER);
            stepPanel.add(lblStepImage, BorderLayout.WEST);

            JTextArea txtDescription = new JTextArea();
            txtDescription.setEditable(false);
            txtDescription.setLineWrap(true);
            txtDescription.setWrapStyleWord(true);
            txtDescription.setText(step.getDescription());
            stepPanel.add(new JScrollPane(txtDescription), BorderLayout.CENTER);
            stepsContainerPanel.add(stepPanel);
            stepsContainerPanel.add(Box.createVerticalStrut(10));
        }
        stepsContainerPanel.revalidate();
        stepsContainerPanel.repaint();

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    class IngredientImageRenderer extends DefaultTableCellRenderer {

        private final JLabel label = new JLabel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (value instanceof String) {
                ImageIcon icon = loadImageIcon((String) value, 40, 40);
                label.setIcon(icon);
            } else {
                label.setIcon(null);
            }
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return label;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScrollPane = new javax.swing.JScrollPane();
        mainContentPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        ingredientsScrollPane = new javax.swing.JScrollPane();
        tblIngredients = new javax.swing.JTable();
        stepsContainerPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 700));

        mainContentPanel.setAlignmentX(0.0F);
        mainContentPanel.setAlignmentY(0.0F);
        mainContentPanel.setLayout(new javax.swing.BoxLayout(mainContentPanel, javax.swing.BoxLayout.Y_AXIS));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle.setText("Tên món ăn");
        lblTitle.setMaximumSize(new java.awt.Dimension(400, 300));
        mainContentPanel.add(lblTitle);

        lblTitle1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle1.setText("Mô tả");
        lblTitle1.setMaximumSize(new java.awt.Dimension(400, 300));
        lblTitle1.setMinimumSize(new java.awt.Dimension(400, 300));
        lblTitle1.setName(""); // NOI18N
        lblTitle1.setOpaque(true);
        lblTitle1.setPreferredSize(new java.awt.Dimension(400, 300));
        mainContentPanel.add(lblTitle1);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        txtDescription.setViewportView(jTextArea2);

        mainContentPanel.add(txtDescription);

        ingredientsScrollPane.setMaximumSize(new java.awt.Dimension(9999, 170));

        tblIngredients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblIngredients.setMaximumSize(new java.awt.Dimension(9999, 170));
        tblIngredients.setRowHeight(50);
        ingredientsScrollPane.setViewportView(tblIngredients);

        mainContentPanel.add(ingredientsScrollPane);

        stepsContainerPanel.setMaximumSize(new java.awt.Dimension(9999, 9999));
        stepsContainerPanel.setLayout(new javax.swing.BoxLayout(stepsContainerPanel, javax.swing.BoxLayout.Y_AXIS));
        mainContentPanel.add(stepsContainerPanel);

        mainScrollPane.setViewportView(mainContentPanel);

        getContentPane().add(mainScrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ingredientsScrollPane;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JPanel mainContentPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JPanel stepsContainerPanel;
    private javax.swing.JTable tblIngredients;
    private javax.swing.JScrollPane txtDescription;
    // End of variables declaration//GEN-END:variables
}
