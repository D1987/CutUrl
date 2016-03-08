package servlets;

import classes.DBConnect;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/UserAvtoriz")
public class UserAvtoriz extends HttpServlet {
    @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        avtorizaciya(req, resp);
    }
    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        avtorizaciya(req, resp);
    }

    public void avtorizaciya(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String login = req.getParameter("login");
        String passw = req.getParameter("password");
        PrintWriter out;

        String password = UserRegistrac.md5Apache(passw);

        //proverka na pustye polya
        if ((login.equals("") || login.equals(null))) {
            out = resp.getWriter();
            out.print("pustoiLogin");
        }else if(password.equals("") || password.equals(null)){
            out = resp.getWriter();
            out.print("pustoiPassword");
        }
        else
        {
            PreparedStatement ps = null;
            ResultSet rs = null;
            int idU = 0;
            DBConnect connect = new DBConnect();
            try
            {
                ps = connect.getConnection().prepareStatement("SELECT id_users,login,password FROM users WHERE login=? AND password=?");
                ps.setString(1, login);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (!rs.next()) {
                    out = resp.getWriter();
                    out.print("true");
                } else {
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        idU = rs.getInt(1);
                        login = rs.getString(2);
                    }
                    String id = String.valueOf(idU);
                    out = resp.getWriter();
                    out.print(id+"/"+login);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try{
                    if (ps != null) {
                        ps.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                    connect.getConnection().close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
