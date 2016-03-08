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

@WebServlet("/by/*")
public class ReferRedirectOut extends HttpServlet
{
   @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirect(req, resp);
    }

    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        redirect(req, resp);
    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp) {
        DBConnect connect = new DBConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PrintWriter out;
        int id = 0;
        String url = String.valueOf(req.getRequestURL().substring(7));
        try
        {
            ps = connect.getConnection().prepareStatement("SELECT cut_ref,id_reference FROM reference WHERE cut_ref=?");
            ps.setString(1, url);
            rs = ps.executeQuery();

           if (!rs.next()) {
               resp.sendRedirect("http://localhost:81/notFound.jsp");

            } else{
                rs = ps.executeQuery();
                while (rs.next()) {
                    id = rs.getInt(2);
                }
                resp.sendRedirect("http://localhost:81/ReferRedirect?id="+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
