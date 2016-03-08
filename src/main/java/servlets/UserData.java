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

@WebServlet("/UserData")
public class UserData extends HttpServlet {
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        vuborkaPersonData(request);
    }

    public Map vuborkaPersonData(HttpServletRequest req)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String idU =  (String)req.getAttribute("id");
        int id = Integer.parseInt(idU);
        Map<Integer, References> map = new HashMap<Integer, References>();
        DBConnect connect = new DBConnect();
        try {
            //select ref this user
            ps = connect.getConnection().prepareStatement("SELECT r.id_reference, r.cut_ref, r.description, r.count, r.tag FROM reference r WHERE id_users=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                map.put(rs.getInt(1), new References(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            //select ref that repeating another user
            ps = connect.getConnection().prepareStatement("SELECT r.id_reference, r.cut_ref, r.description, r.count, r.tag FROM reference r WHERE idU=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                map.put(rs.getInt(1), new References(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        catch (SQLException e)
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
        return  map;
    }

    /*vyborka id user po login*/
    public static int vuborkaIdU(HttpServletRequest req, HttpServletResponse resp)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String login = (String) req.getAttribute("login");
        int id = 0;

        DBConnect connect = new DBConnect();
        try {
            ps = connect.getConnection().prepareStatement("SELECT id_users FROM users WHERE login=?");
            ps.setString(1, login);
            rs = ps.executeQuery();

            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (SQLException e)
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
        return  id;
    }

    // vyborka maila po id
    public static String vuborkaMailPoId(int idU)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String mail = null;
        DBConnect connect = new DBConnect();
        try {
            ps = connect.getConnection().prepareStatement("SELECT mail FROM users WHERE id_users=?");
            ps.setInt(1, idU);
            rs = ps.executeQuery();

            while (rs.next())
            {
                mail = rs.getString(1);
            }
        }
        catch (SQLException e)
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
        return  mail;
    }
}
