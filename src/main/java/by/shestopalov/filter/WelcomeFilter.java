package by.shestopalov.filter;

import by.shestopalov.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/welcome")
public class WelcomeFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if(User.isAuth==false) config.getServletContext().getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        else config.getServletContext().getRequestDispatcher("/welcome").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
