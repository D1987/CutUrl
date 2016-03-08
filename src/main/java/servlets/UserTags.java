package servlets;

import classes.DBConnect;
import classes.References;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/UserTags")
public class UserTags extends HttpServlet
{
    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tags(req, resp);
    }

    @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tags(req, resp);
    }

    public void tags(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean where = Boolean.valueOf(req.getParameter("where"));
        String loginTags = req.getParameter("loginTags");
        String tag = req.getParameter("tag");
        Map<References, References> map = new HashMap<References, References>();
        DBConnect connect = new DBConnect();
        try
        {
             ps = connect.getConnection().prepareStatement("SELECT r.id_reference, u.login, r.cut_ref " +
                     " FROM reference r" +
                     " INNER JOIN users u" +
                     " ON r.id_users = u.id_users" +
                     " WHERE r.tag=?");
             ps.setString(1, tag);
             rs = ps.executeQuery();
             while (rs.next()) {
                 map.put(new References(rs.getInt(1)), new References(rs.getString(2), rs.getString(3)));
             }
             req.setAttribute("tags", map);
             req.setAttribute("tag", tag);

             //proverka na kakuyu stranicu otpravlyat
             if (where) {
                 String login = req.getParameter("login");
                 req.setAttribute("login",login);
                 req.setAttribute("loginTags",loginTags);
                 req.setAttribute("idU",req.getParameter("idU"));
                 req.getRequestDispatcher("sokr.jsp").forward(req, resp);

             }else {
                 req.setAttribute("login",loginTags);
                 int id = UserData.vuborkaIdU(req, resp);
                 req.getRequestDispatcher("userCabinet.jsp?idU="+id).forward(req, resp);
             }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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



