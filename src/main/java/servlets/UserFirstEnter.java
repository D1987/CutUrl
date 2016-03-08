package servlets;

import classes.DBConnect;
import classes.References;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UserFirstEnter")
public class UserFirstEnter extends HttpServlet {
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        proverkaVhoda(request,response);
    }

    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        proverkaVhoda(request,response);
    }

    public static void proverkaVhoda(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");
        int idU = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PrintWriter out;
        DBConnect connect = new DBConnect();

        try {
            ps = connect.getConnection().prepareStatement("INSERT INTO users(login,password,mail) VALUES (?,?,?)");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, mail);
            ps.executeUpdate();

            ps = connect.getConnection().prepareStatement("SELECT id_users FROM users WHERE login=?");
            ps.setString(1, login);
            rs = ps.executeQuery();

            while (rs.next()) {
                idU = rs.getInt(1);
            }
            out = resp.getWriter();
            out.print(idU);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                connect.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();

           }
        }
    }
}
