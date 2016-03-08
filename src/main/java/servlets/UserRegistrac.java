package servlets;

import classes.DBConnect;
import classes.MailService;
import org.apache.commons.codec.digest.DigestUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/UserRegistrac")
public class UserRegistrac extends HttpServlet
{
    @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        insertUser(req, resp);
    }
    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        insertUser(req, resp);
    }

    public void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String mail = req.getParameter("mail");
        PrintWriter out;

        //proverka sushestvovaniya parol/login/mail v base
        if(proverkaMail(mail)){
            out = resp.getWriter();
            out.print("mailIs");
        }
        else if ((mail.equals("") || mail.equals(null))) {
            out = resp.getWriter();
            out.print("mail");
        }
        else if (!testMail(mail)) {
            out = resp.getWriter();
            out.print("netTakoyiPochty");
        }
        else if (!new MailService().selectDomain(mail)) {
            out = resp.getWriter();
            out.print("notMail");
        }
        else if(proverkaLogin(login)){
            out = resp.getWriter();
            out.print("loginIs");
        }
        else if(login.equals("") || login.equals(null)){
            out = resp.getWriter();
            out.print("login");
        }
        else if(!test(login)) {
            out = resp.getWriter();
            out.print("anotherSymDlinna");
        }
        else if(password.equals("") || password.equals(null)){
            out = resp.getWriter();
            out.print("password");
        }
        else if(password.length() < 5){
            out = resp.getWriter();
            out.print("passwordDlinna");
        }
        else if(password.equals(login)) {
            out = resp.getWriter();
            out.print("passwordLogin");
        }
        else if(password2.equals("") || password2.equals(null)) {
            out = resp.getWriter();
            out.print("password2");
        }
        else if(!password.equals(password2)) {
            out = resp.getWriter();
            out.print("passwordPass2");
        }else{UserMail.registrUserMail(req);}
    }

    public static boolean proverkaMail(String mail) {
        boolean pr = false;
        DBConnect connect = new DBConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = connect.getConnection().prepareStatement("SELECT mail FROM users WHERE mail=?");
            ps.setString(1,mail);
            rs = ps.executeQuery();

            if (rs.next()){
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

    public static boolean proverkaLogin(String login) {
        boolean pr = false;
        DBConnect connect = new DBConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = connect.getConnection().prepareStatement("SELECT login FROM users WHERE login=?");
            ps.setString(1,login);
            rs = ps.executeQuery();
            if (rs.next()){
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

    public static boolean proverkaPass(String password) {
        boolean pr = false;
        DBConnect connect = new DBConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String vremPass = md5Apache(password);

        try
        {
            ps = connect.getConnection().prepareStatement("SELECT password FROM users WHERE password=?");
            ps.setString(1,vremPass);
            rs = ps.executeQuery();
            if (rs.next()){
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

    public static boolean test(String testString){
        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{3,30}$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public static boolean testMail(String testString){
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    //shifr parolya
    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }
}
