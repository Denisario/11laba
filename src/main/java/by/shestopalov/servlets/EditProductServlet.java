package by.shestopalov.servlets;

import by.shestopalov.dbcontroller.ProductController;
import by.shestopalov.model.Product;
import by.shestopalov.model.ProductCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
    private Product product;
    private ProductController productController;

    @Override
    public void init() throws ServletException {
        try {
            this.productController=new ProductController();
            this.product=new Product();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.product.setName(req.getParameter("name"));
            ProductCategory productCategory=new ProductCategory();
            productCategory.setId(1);
            this.product.setProductCategory(productCategory);
            this.product.setAmount(Long.parseLong(req.getParameter("amount")));
            this.product.setPrice(Long.parseLong(req.getParameter("price")));
            this.productController.updateProduct(this.product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.product = this.productController.getProductById(Long.parseLong(req.getParameter("id"))).orElseThrow(() -> new Exception("Product not found"));
            getServletContext().getRequestDispatcher("/editProduct.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
