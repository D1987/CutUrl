package servlets;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

@WebServlet("/UserMail")
public class UserMail extends HttpServlet
{
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        registrUserMail(request);
    }
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        registrUserMail(request);
    }

    protected static void registrUserMail(HttpServletRequest request) throws ServletException, IOException
    {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        final String login = resource.getString("MAIL");
        final String password = resource.getString("PASSWORDMAIL");
        Properties props;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getParameter("mail")));
            message.setSubject("Welcome to sokr.by");
            String passw = UserRegistrac.md5Apache(request.getParameter("password"));
            message.setText("Thank you for registering in our service. Keep this letter in it shows your username and password." +
                    " Login: " + request.getParameter("login") + " password: " + request.getParameter("password") + "\n" + "\n" +
                    "To complete the registration please go to: http://localhost:81/firstEnter.jsp?login="+ request.getParameter("login") + "&hash="
                    + passw + "&mail=" + request.getParameter("mail"));
            Transport.send(message);
        }
        catch (AddressException e) {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    protected static void updateUserMail(HttpServletRequest request, String log, String pass) throws ServletException, IOException
    {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        final String login = resource.getString("MAIL");
        final String password = resource.getString("PASSWORDMAIL");
        Properties props;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse((String) request.getAttribute("mail")));
            message.setSubject("Sokr.by update person data");

            if (log.equals(null) || log.equals("")) {
                message.setText("New password: " + pass);
                Transport.send(message);
            }
            else if(pass.equals(null)  || pass.equals(""))
            {
                message.setText("New login: " + log);
                Transport.send(message);
            }
            else
            {
                message.setText("New login: " + log + " password: " + pass);
                Transport.send(message);
            }
        }
        catch (AddressException e) {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    protected static void deleteUserMail(HttpServletRequest request, String mail) throws ServletException, IOException
    {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        final String login = resource.getString("MAIL");
        final String password = resource.getString("PASSWORDMAIL");
        Properties props;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Delete akkaunt");
            message.setText(request.getParameter("login") + " we are very sorry that You are leaving us. Your akkaunt deleted. ");
            Transport.send(message);
        }  catch (AddressException e) {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    protected static void sekret(String mail,String loginN,String passwordN) throws ServletException, IOException
    {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        final String login = resource.getString("MAIL");
        final String password = resource.getString("PASSWORDMAIL");
        Properties props;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Forgot password?");
            message.setText("Your new password. Login: " + loginN + " password: " + passwordN);
            Transport.send(message);
        }  catch (AddressException e) {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
