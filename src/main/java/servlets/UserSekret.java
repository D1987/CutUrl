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

@WebServlet("/UserSekret")
public class UserSekret extends HttpServlet
{
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        vspomnit(request,response);
    }

    public void vspomnit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        PrintWriter out;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if ((mail.equals("") || mail.equals(null)))
        {
            out = resp.getWriter();
            out.print("true");
        }
        else if (!UserRegistrac.testMail(mail)) {
            out = resp.getWriter();
            out.print("neKorrektno");
        }
        else if(!UserRegistrac.proverkaMail(mail)){
            out = resp.getWriter();
            out.print("mailNo");
        }
        else if(password.equals("") || password.equals(null)){
            out = resp.getWriter();
            out.print("password");
        }
        else if(UserRegistrac.proverkaPass(password)){
            out = resp.getWriter();
            out.print("passwordIs");
        }
        else if(loginEqPassw(password)){
            out = resp.getWriter();
            out.print("loginEqPassw");
        }
        else if(password.length() < 5){
            out = resp.getWriter();
            out.print("passwordDlinna");
        }
        else
        {
            DBConnect connect = new DBConnect();
            try {
                String login = null;
                String passw = UserRegistrac.md5Apache(password);

                ps = connect.getConnection().prepareStatement("UPDATE users SET password=? WHERE mail=?");
                ps.setString(1, passw);
                ps.setString(2, mail);
                ps.executeUpdate();

                ps = connect.getConnection().prepareStatement("SELECT * FROM users WHERE mail=?");
                ps.setString(1, mail);

                rs = ps.executeQuery();
                while (rs.next())
                {
                    login = rs.getString(2);
                }
                UserMail.sekret(mail, login, password);
                out = resp.getWriter();
                out.print("proverMail");

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

    private boolean loginEqPassw(String password) {
        boolean pr = false;
        DBConnect connect = new DBConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String login = null;

        try
        {
            ps = connect.getConnection().prepareStatement("SELECT login,password FROM users WHERE password=?");
            ps.setString(1, password);
            rs = ps.executeQuery();

                while (rs.next()) {
                        login = rs.getString(1);
                }
                if (password.equals(login)){
                    pr = true;
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
        return pr;
    }
}
