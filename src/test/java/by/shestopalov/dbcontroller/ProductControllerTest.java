package by.shestopalov.dbcontroller;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProductControllerTest {
    private ProductController productController=null;
    @BeforeTest
    private void setProductControllerContext() throws IllegalAccessException, InstantiationException, SQLException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        productController=new ProductController();
    }

    @Test
    public void getCountOfProductsTest() throws SQLException {
        Assert.assertEquals(productController.getAllProducts().size(), 3);
    }

    @Test
    public void getProductByIdTest() throws SQLException {
        Assert.assertEquals(productController.getProductById(5).isPresent(), false);
    }
}
