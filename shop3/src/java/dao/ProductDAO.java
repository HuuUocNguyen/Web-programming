package dao;

import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

public class ProductDAO {

    // get danh sách sản phẩm dựa vào mã danh mục
    public ArrayList<Product> getListProductByCategory(long category_id) throws SQLException {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM product WHERE category_id = '" + category_id + "'";
        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getLong("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setProductImage(rs.getString("product_image"));
            product.setProductPrice(rs.getDouble("product_price"));
            product.setProductDescription(rs.getString("product_description"));
            list.add(product);
        }
        return list;
    }

    // display detail product
    public Product getProduct(long productID) throws SQLException {
        // tạo kết nối đến cơ sở dữ liệu MySQL
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM product WHERE product_id = '" + productID + "'";
        PreparedStatement ps = connection.prepareCall(sql);
        // thực hiện gọi lệnh executeQuery() để trả về dữ liệu.
        // Sau khi trả về dữ liệu từ đối tượng ResultSet thì sẽ gán các thuộc tính vào trong đối tượng Product
        ResultSet rs = ps.executeQuery();
        Product product = new Product();
        while (rs.next()) {
            product.setProductID(rs.getLong("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setProductImage(rs.getString("product_image"));
            product.setProductPrice(rs.getDouble("product_price"));
            product.setProductDescription(rs.getString("product_description"));
        }

        return product;
    }

    // lấy danh sách sản phẩm - phân trang hiển thị sản phẩm. Phương thức này nhận vào 3 tham số, đầu tiên là mã danh mục sản phẩm, tiếp theo là 2 tham số giá trị bắt đầu lấy dữ liệu và số kết quả trả về
    public ArrayList<Product> getListProductByPages(long categoryID, int firstResult, int maxResult) throws SQLException {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM product WHERE category_id = '" + categoryID + "' limit ?,?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, firstResult);
        ps.setInt(2, maxResult);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getLong("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setProductImage(rs.getString("product_image"));
            product.setProductPrice(rs.getDouble("product_price"));
            product.setProductDescription(rs.getString("product_description"));
            list.add(product);
        }
        return list;
    }
    // tính tổng sản phẩm
    public int countProductByCategory(long categoryID) throws SQLException{
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT count(product_id) FROM product WHERE category_id = '"+categoryID+"' ";
        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        int count=0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
    public static void main(String[] args) throws SQLException {
        ProductDAO dao = new ProductDAO();
        //System.out.println(dao.countProductByCategory(1));
    }

}
