import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/home"},loadOnStartup = 1)
public class MyServlet extends HttpServlet {
    String data=new String();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Connection conn=null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        //String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }

        try {
            conn= DriverManager.getConnection(dbUrl);//"postgres", "yifei1024"
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Statement s=conn.createStatement();
            String sqlStr = "SELECT * FROM patients WHERE id>1;";
            ResultSet rset=s.executeQuery(sqlStr);
            while(rset.next()){
                data=data+"<H2><H2>"+rset.getInt("id")+" "+ rset.getString("familyname");
            }
            rset.close();
            s.close();
            conn.close();
        }
        catch (Exception e){
        }


        resp.setContentType("text/html");
        resp.getWriter().write("<HTML>" +
                "<HEAD>" +
                "<TITLE> Yifei_Home </TITLE>" +
                "</HEAD>" +
                "<BODY BGCOLOR=#cdd2e0>" +
                "<HR>" +
                "<H1>This website finally work</H1>" +
                "<H2>I'M SO HAPPY!!!^-^<H2>" +
                "<H2>this is testing<H2>" +
                "<H2>"+data+"<H2>" +
                "<HR>" +
                "</BODY>" +
                "</HTML>");



    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Gson gson=new Gson();

        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Patient p=gson.fromJson(reqBody,Patient.class);
        System.out.println("Patient name: "+p.patientName()+"\nPatient phone number: "+p.phoneNum()+"\nPatient id: "+p.patientId());
        resp.setContentType("application/json");
        resp.getWriter().write("Thank you client! "+reqBody);
    }

}
