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

@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet
{
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        update(request,response);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginOld = req.getParameter("loginOld");
        String login = req.getParameter("login");
        String passw = req.getParameter("password");
        String passw1 = req.getParameter("password1");
        String idU = req.getParameter("idU");
        PrintWriter out;
        PreparedStatement ps = null;

        if(UserRegistrac.proverkaLogin(login)){
            out = resp.getWriter();
            out.print("loginIs");
        }
        else if(!UserRegistrac.test(login) && !(login.equals(""))) {
            out = resp.getWriter();
            out.print("anotherSymDlinna");
        }
        else if(passw.length() >= 1 && passw.length() < 5){
            out = resp.getWriter();
            out.print("passwordDlinna");
        }
        else if (passw.equals(login) && !(passw.equals(""))) {
            out = resp.getWriter();
            out.print("passwordLogin");
        }
        else if(!passw.equals(passw1)) {
            out = resp.getWriter();
            out.print("neEq");
        }
        else if (login.equals("") && passw.equals("")) {
            out = resp.getWriter();
            out.print("pusto");
        }
        else
        {
            DBConnect connect = new DBConnect();
            try {
                if (login.equals("")) {
                    String password = UserRegistrac.md5Apache(passw);
                    ps = connect.getConnection().prepareStatement("UPDATE users SET password=? WHERE login=?");
                    ps.setString(1, password);
                    ps.setString(2, loginOld);
                    ps.executeUpdate();
                    req.setAttribute("mail",UserData.vuborkaMailPoId(Integer.parseInt(idU)));
                    UserMail.updateUserMail(req,login,passw);
                }
                else if (passw.equals(""))
                {
                    ps = connect.getConnection().prepareStatement("UPDATE users SET login=? WHERE login=?");
                    ps.setString(1, login);
                    ps.setString(2, loginOld);
                    ps.executeUpdate();
                    req.setAttribute("mail",UserData.vuborkaMailPoId(Integer.parseInt(idU)));
                    UserMail.updateUserMail(req,login,passw);
                }
                else{
                    String password = UserRegistrac.md5Apache(passw);
                    ps = connect.getConnection().prepareStatement("UPDATE users SET login=?, password=? WHERE login=?");
                    ps.setString(1, login);
                    ps.setString(2, password);
                    ps.setString(3, loginOld);
                    ps.executeUpdate();
                    req.setAttribute("mail",UserData.vuborkaMailPoId(Integer.parseInt(idU)));
                    UserMail.updateUserMail(req,login,passw);
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
