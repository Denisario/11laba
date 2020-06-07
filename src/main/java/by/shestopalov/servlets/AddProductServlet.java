package by.shestopalov.servlets;

import by.shestopalov.dbcontroller.ProductController;
import by.shestopalov.dbcontroller.UserController;
import by.shestopalov.model.ProductCategory;
import by.shestopalov.util.ConnectorDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    ProductController productController=null;
    private ArrayList<ProductCategory> productCategories=null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            productCategories=productController.getAllCategories();
            req.setAttribute("categories", productCategories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id=productCategories.stream().filter(c->req.getParameter("category").equals(c.getName())).findAny().orElse(null).getId();
        try {
            System.out.println(id);
            productController.addProduct(req.getParameter("name"),
                                        id,
                                        Integer.parseInt(req.getParameter("amount")),
                                        Integer.parseInt(req.getParameter("price")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        try {
            productController= new ProductController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
