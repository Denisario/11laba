package by.shestopalov.servlets;

import by.shestopalov.dbcontroller.ProductController;
import by.shestopalov.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProductController productController=new ProductController();
            ArrayList<Product> products=productController.getAllProducts();
            System.out.println(products.size());
            req.setAttribute("products", products);
            getServletContext().getRequestDispatcher("/welcome.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
