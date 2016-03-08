package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/*class ssylok*/
public class References {
    private Integer id;
    private String login;
    private String full_ref;
    private String cut_ref;
    private String description;
    private String count;
    private String tag;

    public References() {}

    public References(String cut_ref) {
        this.cut_ref = cut_ref;
    }

    public References(Integer id) {
        this.id = id;
    }

    public References(String login, String cut_ref) {
        this.login=login;
        this.cut_ref = cut_ref;
    }

    public References(String login, String full_ref, String cut_ref,  String description, String tag) {
        this.login=login;
        this.full_ref=full_ref;
        this.cut_ref=cut_ref;
        this.description = description;
        this.tag = tag;
    }

    public References(String cut_ref, String description, String count, String tag) {
        this.cut_ref = cut_ref;
        this.description = description;
        this.count = count;
        this.tag = tag;
    }

    public Integer getId() { return id; }

    public String getLogin() {
        return login;
    }

    public String getFull_ref() {
        return full_ref;
    }

    public String getCut_ref() {
        return cut_ref;
    }

    public String getDescription() {
        return description;
    }

    public String getCount() {
        return count;
    }

    public String getTag() {
        return tag;
    }

    /*vyborka vseh ssylok dlya zagruzki glavnoi stranicy*/
    synchronized public Map showAllRefer() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<Integer, References> map = new HashMap<Integer, References>();
        DBConnect connect = new DBConnect();
        try {
            ps = connect.getConnection().prepareStatement("SELECT r.id_reference, u.login, r.full_ref, r.cut_ref, r.description, r.tag" +
                    " FROM reference r" +
                    " INNER JOIN users u" +
                    " ON r.id_users = u.id_users");
            rs = ps.executeQuery();
            while (rs.next())
            {
                map.put(rs.getInt(1),new References(rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6)));
            }
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
                assert ps != null;
                ps.close();
                assert rs != null;
                rs.close();
                connect.getConnection().close();
            }catch (SQLException e){ e.printStackTrace();}
        }
        return map;
    }

    /*vyborka kollichestva ssylok i klikov animaciya*/
    synchronized public Map countRefAndClick(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        DBConnect connect = new DBConnect();

        int countReferences = 0;
        int countClick = 0;
        try {
            ps = connect.getConnection().prepareStatement("SELECT COUNT(*) FROM reference");
            rs = ps.executeQuery();
            while (rs.next())
            {
                countReferences = rs.getInt(1);
            }
            ps = connect.getConnection().prepareStatement("SELECT SUM(count) FROM reference");
            rs = ps.executeQuery();
            while (rs.next())
            {
                countClick = rs.getInt(1);
            }
            map.put(countReferences,countClick);
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
                assert ps != null;
                ps.close();
                assert rs != null;
                rs.close();
                connect.getConnection().close();
            }catch (SQLException e){ e.printStackTrace();}
        }
        return map;
    }
}
