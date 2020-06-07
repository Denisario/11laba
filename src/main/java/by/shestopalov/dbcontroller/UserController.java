package by.shestopalov.dbcontroller;

import by.shestopalov.model.User;
import by.shestopalov.util.ConnectorDB;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserController {
    private Connection connection;

    public UserController() throws IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, NoSuchMethodException, ClassNotFoundException {
        this.connection = ConnectorDB.getConnection();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public boolean registerUser(String username, String pass) throws Exception {
        PreparedStatement ps = connection.prepareStatement(SQLHelper.REGISTER_QUERY);

        if(getUserByUsername(username).isPresent()) throw new Exception("User with this username has been registered");

        ps.setString(1, username);
        ps.setString(2, md5Apache(pass));
        ps.executeUpdate();
        return true;
    }

    public Optional<User> getUserByUsername(String username) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQLHelper.GET_USER_BY_USERNAME);
        ps.setString(1, username);
        by.shestopalov.model.User user=null;
        ResultSet rs=ps.executeQuery();
        if(rs.last()){
            rs.first();
             user=new User();
            user.setName(rs.getString("USER_NAME"));
            user.setPassword(rs.getString("USER_PASS"));
            user.setRole(rs.getInt("USER_ROLE"));
            return Optional.of(user);
        }
        return Optional.ofNullable(user);
    }

    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }

}