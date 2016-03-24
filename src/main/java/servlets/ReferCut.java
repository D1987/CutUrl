package servlets;

import classes.DBConnect;
import classes.QRCodeGeneratoe;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/ReferCut")
public class ReferCut extends HttpServlet {

    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");
        String ssylka = request.getParameter("ssylka");
        String tag = request.getParameter("tag");
        Map<Integer, Integer> map;
        PrintWriter out;
        int id;
        int idU = 0;
        int idR = 0;

        if ((ssylka.equals("") || ssylka.equals(null))) {
            out = response.getWriter();
            out.print("pustayaSsylka");
        }
        else if(!provURL(ssylka)) {
        out = response.getWriter();
        out.print("neCorr");
        }
        else if(tag.length()>20) {
            out = response.getWriter();
            out.print("dlinna");
        }
        else if(tag.contains("#") || tag.contains(" ")) {
            out = response.getWriter();
            out.print("invalidChar");
        }
        else
        {
            String sokr = sokrashtel();
            File file = QRCodeGeneratoe.qrGeneration(sokr);
            request.setAttribute("login",login);
            id = UserData.vuborkaIdU(request,response);

            //proverka ili ref existance
            map = provRef(ssylka);

            for (Map.Entry entry: map.entrySet()) {
                idR = (Integer)entry.getKey();
                idU = (Integer)entry.getValue();
            }
                if (idR!=0)//ref existance
                {
                    //inspection whose ref
                    if (idU!=id)
                    {
                        insertCopyRef(request, response, ssylka, id, idR);
                    }
                }
                else {insertRefrence(request, response, ssylka, sokr,tag, file, id, idR);}
        }
    }
    //proverka ili long ref existane
    public Map provRef(String ssylka) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnect connect = new DBConnect();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        try
        {
            ps = connect.getConnection().prepareStatement("SELECT id_reference,id_users FROM reference WHERE full_ref=?");
            ps.setString(1, ssylka);
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

    public String sokrashtel() {
    String  sokr = "localhost:81/by/";
    char symbol;
       while (sokr.length() < 22) {
            int chislo = (int) (Math.random() * 122);
            if ((chislo >= 0 && chislo <= 9) || (chislo >= 65 && chislo <= 90) || (chislo >= 97 && chislo <= 122)) {
                if (chislo >= 0 && chislo <= 9)             //dlya chisel
                {
                    sokr += String.valueOf(chislo);
                } else                                      //dlya vseh
                {
                    symbol = (char) chislo;
                    sokr += symbol;
                }
            }
        }
        return sokr;
    }

    public void insertRefrence(HttpServletRequest req, HttpServletResponse resp,String ssylka,String sokr,String tag, File file,int id,int idR) throws ServletException, IOException {
        String login = req.getParameter("login");
        PrintWriter out;
        PreparedStatement ps = null;

        DBConnect connect = new DBConnect();
        try
        {
            InputStream inputStream = new FileInputStream(file);
            ps = connect.getConnection().prepareStatement("INSERT INTO reference(full_ref,cut_ref,description,tag,qrcode,id_users) VALUES (?,?,?,?,?,?)");
            ps.setString(1,ssylka);
            ps.setString(2,sokr);
            ps.setString(3,req.getParameter("description"));
            ps.setString(4,tag);
            ps.setBlob(5, inputStream);
            ps.setInt(6, id);
            ps.executeUpdate();

            ps = connect.getConnection().prepareStatement("UPDATE reference SET idU=? WHERE id_reference=?");
            ps.setInt(1, id);
            ps.setInt(2, idR);
            ps.executeUpdate();

            req.setAttribute("login", login);
            req.setAttribute("ref", sokr);
            int idUser = UserData.vuborkaIdU(req, resp);
            out = resp.getWriter();
            out.print(idUser);
        }
        catch (SQLException e)
        {
            if (e.getSQLState().equals("23000"))
            {
                doPost(req,resp);
            }
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

    public void insertCopyRef(HttpServletRequest req, HttpServletResponse resp,String sokr,int id,int idR)
    {
        String login = req.getParameter("login");
        PrintWriter out;
        PreparedStatement ps = null;

        DBConnect connect = new DBConnect();
        try
        {
            ps = connect.getConnection().prepareStatement("UPDATE reference SET idU=? WHERE id_reference=?");
            ps.setInt(1, id);
            ps.setInt(2, idR);
            ps.executeUpdate();

            req.setAttribute("login", login);
            req.setAttribute("ref", sokr);
            int idUser = UserData.vuborkaIdU(req, resp);
            out = resp.getWriter();
            out.print(idUser);
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
    public boolean provURL(String ssylka) {
        Pattern p = Pattern.compile(".*https://.*|.*http://.*");
        Matcher m = p.matcher(ssylka);
        return m.matches();
    }
}
