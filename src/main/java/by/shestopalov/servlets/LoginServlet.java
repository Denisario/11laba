package by.shestopalov.servlets;

import by.shestopalov.dbcontroller.UserController;
import by.shestopalov.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    String err=null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("err", err);
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController userController= null;
        try {
            if(req.getParameter("login").equals("")||
                    req.getParameter("pass").equals("")) throw new Exception("All fields are required");
            userController = new UserController();

            User user=userController.getUserByUsername(req.getParameter("login")).orElseThrow(()->new Exception("User not found"));
            if(!(UserController.md5Apache(req.getParameter("pass")).equals(user.getPassword()))) throw new Exception("Incorrect pass");
            req.setAttribute("user", user.getName());
            User.isAuth=true;
            getServletContext().getRequestDispatcher("/welcome.jsp").forward(req, resp);
        } catch (Exception e) {
            err=e.getMessage();
            req.setAttribute("err", err);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }
    }
}
