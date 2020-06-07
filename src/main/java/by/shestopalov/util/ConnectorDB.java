package by.shestopalov.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {

    public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        return DriverManager.getConnection(url, user, pass);
    }
}