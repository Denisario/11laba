package by.shestopalov.servlets;

import by.shestopalov.dbcontroller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet  extends HttpServlet {
    private String err;
    private UserController userController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("err", err);
        getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        try {
            this.userController=new UserController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getParameter("login").equals("")||
                    req.getParameter("pass").equals("")||
                    req.getParameter("rpass").equals("")) throw new Exception("All fields are required");
            if(!req.getParameter("pass").equals(req.getParameter("rpass"))) throw new Exception("incorrect pass");
            this.userController = new UserController();
            this.userController.registerUser(req.getParameter("login"), req.getParameter("pass"));
            resp.sendRedirect("/login.jsp");
        } catch (Exception e) {
            err=e.getMessage();
            req.setAttribute("err", err);
            getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
        }


    }
}
