package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//class dlya vyborki poctovogo servisa, rabotaet s CSV
public class MailService {

    private String name;
    private String url;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    synchronized public boolean selectDomain(String mail){

        String domain;
        domain = parsingMail(mail);  //poluchaem domen 2 urovnya
        boolean b = false;

        DBConnect connect = new DBConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = connect.getConnection().prepareStatement("SELECT name,url FROM email_services WHERE domain=?");
            ps.setString(1,domain);
            rs = ps.executeQuery();

            while (rs.next()) {
                setName(rs.getString(1));
                setUrl(rs.getString(2));
                b=true;
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
        return b;
    }

    //poluchaem domen 2 urovnya
    synchronized public String parsingMail(String mail){
        String domain;
        String[] str = mail.split("@");
        domain = str[1];
        return domain;
    }
}
