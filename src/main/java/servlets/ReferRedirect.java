package servlets;

import classes.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//servlet dlya perehoda po ssylke iznutri prilozeniya
@WebServlet("/ReferRedirect")
public class ReferRedirect extends HttpServlet
{
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        redirect(request, response);
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer countNew = 0;
        String c;
        String id = request.getParameter("id");
        int id1 = Integer.parseInt(id);
        countNew++;

        DBConnect connect = new DBConnect();
        try {
            ps = connect.getConnection().prepareStatement("SELECT full_ref FROM reference WHERE id_reference=?");
            ps.setInt(1, id1);
            rs = ps.executeQuery();

            while (rs.next())
            {
                response.sendRedirect(rs.getString(1));
            }

            ps = connect.getConnection().prepareStatement("SELECT count FROM reference WHERE id_reference=?");
            ps.setInt(1, id1);
            rs = ps.executeQuery();

            while (rs.next())
            {
                countNew += Integer.parseInt(rs.getString(1));
            }

            c = String.valueOf(countNew);

            ps = connect.getConnection().prepareStatement("UPDATE reference SET count=? WHERE id_reference=?");
            ps.setString(1, c);
            ps.setInt(2, id1);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
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
