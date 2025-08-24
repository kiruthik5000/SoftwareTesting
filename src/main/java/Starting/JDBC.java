package Starting;

import java.sql.*;

public class JDBC {
    Connection conn;
    Statement smt;
    String url = "jdbc:mysql://localhost:3306/lab", user = "root", pass = "root";

    JDBC() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            smt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectAll() {
        String q = "select * from product";
        try {
            ResultSet rs = smt.executeQuery(q);

            while (rs.next()) {
                int id = rs.getInt(1);
                String category = rs.getString(2);
                String name = rs.getString(3);
                System.out.println(id + " -> " + category + " -> " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(int id, String category, String name) {
        try {
            PreparedStatement q = conn.prepareStatement(
                    "insert into product(id, product_category, product_name) values (?, ?, ?);"
            );
            q.setInt(1, id);
            q.setString(2, category);
            q.setString(3, name);

            q.executeUpdate(); // FIXED
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(int id, String category, String name) {
        try {
            PreparedStatement q = conn.prepareStatement(
                    "update product set product_category = ?, product_name = ? where id = ?;"
            );
            q.setString(1, category);
            q.setString(2, name);
            q.setInt(3, id);

            q.executeUpdate(); // Use executeUpdate for DML
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(int id) {
        try {
            PreparedStatement q = conn.prepareStatement("delete from product where id = ?");
            q.setInt(1, id);

            q.executeUpdate(); // Use executeUpdate for DML
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        jdbc.selectAll();
        jdbc.insert(2, "Laptop", "Dell");
        jdbc.selectAll();
        jdbc.update(2, "Laptop", "Lenovo");
        jdbc.selectAll();
        jdbc.delete(2);
        jdbc.selectAll();
    }
}
