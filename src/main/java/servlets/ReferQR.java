package servlets;

import classes.DBConnect;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ReferQR")
public class ReferQR extends HttpServlet {

    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        response.setContentType("image/gif");
        DBConnect connect = new DBConnect();
        try {
            ps = connect.getConnection().prepareStatement("SELECT qrcode FROM reference WHERE id_reference=?");
            ps.setLong(1, Long.valueOf(request.getParameter("id")));
            rs = ps.executeQuery();

            String imgLen="";
            if(rs.next()){
                imgLen = rs.getString(1);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                int len = imgLen.length();
                byte [] rb = new byte[len];
                InputStream readImg = rs.getBinaryStream(1);
                int index = readImg.read(rb, 0, len);
                response.reset();
                response.setContentType("image/jpg");
                response.getOutputStream().write(rb,0,len);
                response.getOutputStream().flush();
            }
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
