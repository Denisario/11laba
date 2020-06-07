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
    private ProductController productController;
    private ArrayList<ProductCategory> productCategories;

    @Override
    public void init() throws ServletException {
        try {
            this.productController= new ProductController();
            this.productCategories=new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.productCategories=productController.getAllCategories();
            req.setAttribute("categories", this.productCategories);
            getServletContext().getRequestDispatcher("/addProduct.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id=this.productCategories.stream().filter(c->req.getParameter("category").equals(c.getName())).findAny().orElse(null).getId();
        try {
            System.out.println(id);
            this.productController.addProduct(req.getParameter("name"),
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
}
