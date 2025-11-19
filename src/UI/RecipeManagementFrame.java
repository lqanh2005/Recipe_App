package UI;
import Controller.RecipeController;
import java.awt.Dimension;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Recipe;
import DAO.RecipeDAO;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.Timer;


public class RecipeManagementFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecipeManagementFrame.class.getName());
    private final RecipeController recipeController = new RecipeController();
    private Timer searchTimer;
    public RecipeManagementFrame() throws Exception {
        initComponents();
        loadRecipeData();
        searchTimer = new Timer(200, e -> performDBSearch()); 
        searchTimer.setRepeats(false);
        popupMenu.setDefaultLightWeightPopupEnabled(false);
        popupMenu.setFocusable(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        scrollPaneRecipes = new javax.swing.JScrollPane();
        tblRecipes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        popupMenu.setFocusable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        scrollPaneRecipes.setPreferredSize(new java.awt.Dimension(780, 380));

        tblRecipes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRecipes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scrollPaneRecipes.setViewportView(tblRecipes);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách công thức");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Sửa");

        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnDetail.setText("Xem chi tiết");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel2.setText("Tìm kiếm:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(btnDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52)
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(67, 67, 67)
                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(64, 64, 64)
                        .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(129, 129, 129))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPaneRecipes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addComponent(scrollPaneRecipes, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDetail)
                    .addComponent(btnEdit)
                    .addComponent(btnDel))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    }//GEN-LAST:event_btnAddActionPerformed
    private int getSelectedRecipeId() {
        int selectedRow = tblRecipes.getSelectedRow();
        if (selectedRow >= 0) {
            Object idValue = tblRecipes.getValueAt(selectedRow, 0);
            try {
                if (idValue instanceof Integer) {
                    return (int) idValue;
                }
                return Integer.parseInt(idValue.toString());
            } catch (NumberFormatException ex) {
                return -1;
            }
        }
        return -1;
    }
    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int recipeId = getSelectedRecipeId();
        if (recipeId != -1) {
            int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa công thức ID " + recipeId + " không?","Xác nhận Xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    boolean success = recipeController.deleteRecipe(recipeId);
                    if (success) {
                        JOptionPane.showMessageDialog(this,"Đã xóa công thức ID: " + recipeId, "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        loadRecipeData();
                    } else {
                        JOptionPane.showMessageDialog(this,"Không tìm thấy công thức ID " + recipeId + " hoặc không thể xóa.","Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn công thức để xóa.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        int recipeId = getSelectedRecipeId();
        if (recipeId != -1) {
            try {
                Recipe detailRecipe = recipeController.getRecipeDetails(recipeId);
                if (detailRecipe != null) {
                    RecipeDetailFrame detailFrame = new RecipeDetailFrame(detailRecipe);
                    detailFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Không tìm thấy công thức có ID: " + recipeId,
                            "Lỗi Dữ liệu", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn một công thức để xem chi tiết.",
                    "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDetailActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchTimer.restart();
    }//GEN-LAST:event_txtSearchKeyReleased
   
    private void showSuggestion(List<String> list) {
        popupMenu.setVisible(false);
        popupMenu.removeAll();
        popupMenu.setFocusable(false);

        if (list.isEmpty()) return;

        JList<String> jList = new JList<>(list.toArray(new String[0]));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setFocusable(false);

        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s = jList.getSelectedValue();
                if (s != null) {
                    txtSearch.setText(s);
                    popupMenu.setVisible(false);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(jList);
        scroll.setPreferredSize(new Dimension(200, Math.min(list.size() * 25, 125)));
        scroll.setFocusable(false);

        popupMenu.add(scroll);

        // fix lỗi popup bị lệch, click không trúng
        Point p = txtSearch.getLocationOnScreen();
        popupMenu.show(txtSearch, 0, txtSearch.getHeight());
        popupMenu.setLocation(p.x, p.y + txtSearch.getHeight());
    }


    private void performDBSearch() {
        String query = txtSearch.getText();

        SwingWorker<List<String>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<String> doInBackground() throws Exception {
                return recipeController.recipeDAO.searchRecipesByIngredient(query);  // chạy SQL ở background
            }

            @Override
            protected void done() {
                try {
                    List<String> results = get();
                    showSuggestion(results); // cập nhật UI ở EDT
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        worker.execute();
    }
    private void loadRandomRecipe() {
        int randomId = recipeController.recipeDAO.getRandomRecipeId();
        if (randomId == -1) return;

        SwingWorker<Recipe, Void> worker = new SwingWorker<>() {

            @Override
            protected Recipe doInBackground() throws Exception {
                return recipeController.getRecipeDetails(randomId);
            }

            @Override
            protected void done() {
                try {
                    Recipe recipe = get();
                    if (recipe != null) {
                        RecipeDetailFrame detailFrame = new RecipeDetailFrame(recipe);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }
    private void showRandomRecipe(Recipe recipe) {
        
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new RecipeManagementFrame().setVisible(true);
            } catch (Exception ex) {
                System.getLogger(RecipeManagementFrame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
    }
    private void loadRecipeData() throws Exception {
        Object[] tableData = recipeController.getTableDataForUser(1);
        Object[][] data = (Object[][]) tableData[0];
        String[] columnNames = (String[]) tableData[1];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tblRecipes.setModel(model);        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane scrollPaneRecipes;
    private javax.swing.JTable tblRecipes;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
