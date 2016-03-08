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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        deletUser(request,response);
    }

    private void deletUser(HttpServletRequest request, HttpServletResponse response)
    {
        int idU = Integer.parseInt(request.getParameter("idU"));
        String mail = null;
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnect connect;
        connect = new DBConnect();
        try {

            ps = connect.getConnection().prepareStatement("SELECT mail FROM users WHERE id_users=?");
            ps.setInt(1, idU);
            rs = ps.executeQuery();

            while (rs.next()) {
                mail = rs.getString(1);
            }
            //inspect is copy ref.
            map = inspectIduIsNull(idU);
            // if 'no'delete user
            if (map==null || map.size()==0){
                // if it existance delete copy ref.
                ps = connect.getConnection().prepareStatement("UPDATE reference SET idU = ? WHERE idU = ?");
                ps.setString(1, null);
                ps.setInt(2, idU);
                ps.executeUpdate();

                ps = connect.getConnection().prepareStatement("DELETE FROM users WHERE id_users=?");
                ps.setInt(1, idU);
                ps.executeUpdate();

                UserMail.deleteUserMail(request, mail);
            }
            else {
                // if 'yes' update 'id_user' and delete user
                for (Map.Entry<Integer,Integer> entry:map.entrySet()){
                    ps = connect.getConnection().prepareStatement("UPDATE reference SET id_users = ?, idU = ? WHERE id_reference = ?");
                    ps.setInt(1, entry.getValue());
                    ps.setString(2, null);
                    ps.setInt(3, entry.getKey());
                    ps.executeUpdate();
                }
                ps = connect.getConnection().prepareStatement("DELETE FROM users WHERE id_users=?");
                ps.setInt(1, idU);
                ps.executeUpdate();
                UserMail.deleteUserMail(request, mail);
            }
            response.sendRedirect("sokr.jsp");

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

    private Map inspectIduIsNull(Integer idU) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnect connect = new DBConnect();
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        try
        {
            ps = connect.getConnection().prepareStatement("SELECT id_reference,idU FROM reference WHERE idU IS NOT NULL AND id_users=?");
            ps.setInt(1, idU);
            rs = ps.executeQuery();

            while (rs.next()) {
                map.put(rs.getInt(1),rs.getInt(2));
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
        return map;
    }
}

