package com.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDatabase {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/camel";
    private static final String USER = "root";
    private static final String PASS = "thanhxk2003";

    private Connection connection;

    // Khởi tạo kết nối cơ sở dữ liệu
    public OrderDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } catch (SQLException e) {
            System.err.println("Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Đóng kết nối cơ sở dữ liệu
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Kết nối đã được đóng.");
            }
        } catch (SQLException e) {
            System.err.println("Không thể đóng kết nối: " + e.getMessage());
        }
    }

    // Phương thức thực thi câu lệnh INSERT, UPDATE, DELETE
    public int executeUpdate(String sql, Object[] params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Phương thức thực thi câu lệnh SELECT
    public List<Map<String, Object>> executeQuery(String sql, Object[] params) {
        List<Map<String, Object>> result = new ArrayList<>();
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            if (params != null) {
                setParameters(stmt, params);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = resultSetToMap(rs);
                    result.add(row);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    // Phương thức hỗ trợ thiết lập các tham số vào PreparedStatement
    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }

    // Chuyển đổi ResultSet thành Map (giả sử có phương thức này để lấy dữ liệu từ
    // ResultSet)
    private Map<String, Object> resultSetToMap(ResultSet rs) throws SQLException {
        Map<String, Object> row = new HashMap<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            Object value = rs.getObject(i); // Lấy giá trị từ cột
            System.out.println("Column: " + columnName + " Value: " + value); // Debugging
            row.put(columnName, value); // Lưu vào Map
        }
        return row;
    }

    // Phương thức để thêm đơn hàng (ví dụ)
    public int addOrder(int id, String date, int totalMoney, String productsJson) {
        String sql = "INSERT INTO orders (id,date, totalMoney, idProduct) VALUES (?,?, ?, ?)";
        Object[] params = new Object[] { id, date, totalMoney, productsJson };
        return executeUpdate(sql, params);
    }

    // Phương thức để lấy tất cả đơn hàng
    public List<Map<String, Object>> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return executeQuery(sql, null);
    }

    public List<Map<String, Object>> getOrderById(int idString) {
        String sql = "SELECT * FROM orders where id = ?";
        Object[] params = new Object[] { idString };
        return executeQuery(sql, params);
    }

    public List<Map<String, Object>> getProductsById(int idString) {
        String sql = "SELECT * FROM products where id = ?";
        Object[] params = new Object[] { idString };
        return executeQuery(sql, params);
    }
}
