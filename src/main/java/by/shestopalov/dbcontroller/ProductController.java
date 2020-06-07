package by.shestopalov.dbcontroller;

import by.shestopalov.model.Product;
import by.shestopalov.model.ProductCategory;
import by.shestopalov.util.ConnectorDB;
import jdk.jfr.Category;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ProductController {
    private Connection connection;

    public ProductController() throws IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, NoSuchMethodException, ClassNotFoundException {
        this.connection = ConnectorDB.getConnection();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public ArrayList<ProductCategory> getAllCategories() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQLHelper.GET_ALL_PRODUCT_CATEGORIES);
        ResultSet categorySet=ps.executeQuery();
        return convert(categorySet);
    }

    public void addProduct(String name, long categoryId, int amount, int price) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQLHelper.ADD_PRODUCT);
        ps.setString(1,name);
        ps.setLong(2,categoryId);
        ps.setInt(3,amount);
        ps.setInt(4,price);
        ps.executeUpdate();
    }

    public ArrayList<Product> getAllProducts() throws SQLException {
        PreparedStatement ps=connection.prepareStatement(SQLHelper.GET_ALL_PRODUCTS);
        ResultSet result=ps.executeQuery();
        return convertProducts(result);
    }

    public Optional<Product> getProductById(long id) throws SQLException{
        PreparedStatement ps=connection.prepareStatement(SQLHelper.GET_PRODUCT_BY_ID);
        ps.setLong(1,id);
        ResultSet product=ps.executeQuery();
        return convertProduct(product);
    }

    public void updateProduct(Product product) throws SQLException {
        PreparedStatement ps=connection.prepareStatement(SQLHelper.UPDATE_PRODUCT);
        ps.setString(1,product.getName());
        ps.setLong(2, product.getProductCategory().getId());
        ps.setLong(3, product.getAmount());
        ps.setLong(4, product.getPrice());
        ps.setLong(5, product.getId());
        ps.executeUpdate();
    }

    private ArrayList<ProductCategory> convert(ResultSet categorySet) throws SQLException {
        ArrayList<ProductCategory> categories=new ArrayList<>();

        while (categorySet.next()){
            ProductCategory productCategory=new ProductCategory();
            productCategory.setId(categorySet.getLong("CATEGORY_ID"));
            productCategory.setName(categorySet.getString("CATEGORY_NAME"));
            categories.add(productCategory);
        }
        return categories;
    }

    private ArrayList<Product> convertProducts(ResultSet productSet) throws SQLException {
        ArrayList<Product> products=new ArrayList<>();

        while (productSet.next()) {
            Product product=new Product();
            product.setId(productSet.getLong("PRODUCT_ID"));
            product.setName(productSet.getString("PRODUCT_NAME"));
            ProductCategory productCategory=new ProductCategory();
            productCategory.setName(productSet.getString("CATEGORY_NAME"));
            product.setProductCategory(productCategory);
            product.setAmount(productSet.getLong("PRODUCT_AMOUNT"));
            product.setPrice(productSet.getLong("PRODUCT_PRICE"));

            products.add(product);
        }

        return products;
    }

    private Optional<Product> convertProduct(ResultSet productResult) throws SQLException {
        Product product = null;
        while (productResult.next()) {
            product=new Product();
            product.setId(productResult.getLong("PRODUCT_ID"));
            product.setName(productResult.getString("PRODUCT_NAME"));
            ProductCategory productCategory=new ProductCategory();
            productCategory.setId(productResult.getLong("PRODUCT_CATEGORY_ID"));
            product.setProductCategory(productCategory);
            product.setAmount(productResult.getLong("PRODUCT_AMOUNT"));
            product.setPrice(productResult.getLong("PRODUCT_PRICE"));
        }

        return Optional.ofNullable(product);
    }


}
