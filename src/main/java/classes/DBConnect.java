package classes;

import java.sql.*;
import java.util.ResourceBundle;

/*class dlya soedinenniya s bd*/
public class DBConnect
{
    synchronized public Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String host = resource.getString("HOST");
        String driver = resource.getString("DRIVER");
        String user = resource.getString("USERNAME");
        String pass = resource.getString("PASSWORD");

        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Драйвер не загружен!");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(host, user, pass);
    }
}


