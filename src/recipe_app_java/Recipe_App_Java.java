package recipe_app_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Recipe_App_Java {
    public static void main(String[] args) {
        String url = "jdbc:mysql://26.16.121.242:3306/recipe_app";
        String user = "teammate2";
        String pass = "matkhau123";

        try {
            // Nạp driver (bắt buộc nếu chưa load tự động)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Kết nối thành công qua Radmin VPN!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không tìm thấy driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi kết nối!");
            e.printStackTrace();
        }
    }
}
