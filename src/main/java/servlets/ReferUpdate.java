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
import java.sql.SQLException;

@WebServlet("/ReferUpdate")
public class ReferUpdate extends HttpServlet {
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateRef(request, response);
    }
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateRef(request, response);
    }

    private void updateRef(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        int idR = Integer.parseInt(req.getParameter("idR"));
        String description = req.getParameter("description");
        String tag = req.getParameter("tag");
        PreparedStatement ps = null;
        PrintWriter out;

        if (description.equals("") && tag.equals(""))
        {
            out = resp.getWriter();
            out.print("pustyePolya");
        }
        else {

            DBConnect connect = new DBConnect();
            try {

                if (description.equals("")) {
                    ps = connect.getConnection().prepareStatement("UPDATE reference SET tag=? WHERE id_reference=?");
                    ps.setString(1, tag);
                    ps.setInt(2, idR);
                    ps.executeUpdate();
                    req.setAttribute("login", login);
                    int idU = UserData.vuborkaIdU(req, resp);
                    out = resp.getWriter();
                    out.print(idU);
                }
                else if (tag.equals(""))
                {
                    ps = connect.getConnection().prepareStatement("UPDATE reference SET description=? WHERE id_reference=?");
                    ps.setString(1, description);
                    ps.setInt(2, idR);
                    ps.executeUpdate();
                    req.setAttribute("login", login);
                    int idU = UserData.vuborkaIdU(req, resp);
                    out = resp.getWriter();
                    out.print(idU);

                }
                else {
                    ps = connect.getConnection().prepareStatement("UPDATE reference SET description=?, tag=? WHERE id_reference=?");
                    ps.setString(1, description);
                    ps.setString(2, tag);
                    ps.setInt(3, idR);
                    ps.executeUpdate();
                    req.setAttribute("login", login);
                    int idU = UserData.vuborkaIdU(req, resp);
                    out = resp.getWriter();
                    out.print(idU);
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
                    connect.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
