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

@WebServlet("/ReferDelete")
public class ReferDelete extends HttpServlet
{
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        deleteRef(request,response);
    }
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        deleteRef(request,response);
    }

    public void deleteRef(HttpServletRequest req, HttpServletResponse resp)
    {
        String id = String.valueOf(req.getParameter("id"));
        String idUser = String.valueOf(req.getParameter("idUser"));
        String idUs = null;
        int whoseIdU;
        DBConnect connect = new DBConnect();
        PreparedStatement ps = null;
        
        //inspect whose ref
        whoseIdU = whoseRef(Integer.parseInt(id));
        try
        {
            //if owner ref
            if (whoseIdU==Integer.parseInt(idUser)){
                //if existence copy ref
                int idU=provCopyRef(Integer.parseInt(id));
                if (idU!=0){
                    ps = connect.getConnection().prepareStatement("UPDATE reference SET id_users = ?, idU = ? WHERE id_reference = ?");
                    ps.setInt(1, idU);
                    ps.setString(2, idUs);
                    ps.setInt(3, Integer.parseInt(id));
                    ps.executeUpdate();
                }
                //if dont existence copy ref
                else {
                    ps = connect.getConnection().prepareStatement("DELETE FROM reference WHERE id_reference=?");
                    ps.setString(1,id);
                    ps.executeUpdate();
                }
            }
            //if dont owner ref
            else {
                ps = connect.getConnection().prepareStatement("UPDATE reference SET idU = ? WHERE id_reference = ?");
                ps.setString(1, idUs);
                ps.setInt(2, Integer.parseInt(id));
                ps.executeUpdate();
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
                connect.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

        private int whoseRef(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnect connect = new DBConnect();
        int idU = 0;
        try
        {
            ps = connect.getConnection().prepareStatement("SELECT id_users FROM reference WHERE id_reference=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                idU=rs.getInt(1);
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
        return idU;
    }

    private int provCopyRef(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnect connect = new DBConnect();
        int idU = 0;
        try
        {
            ps = connect.getConnection().prepareStatement("SELECT idU FROM reference WHERE id_reference=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                idU=rs.getInt(1);
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
        return idU;
    }
}
