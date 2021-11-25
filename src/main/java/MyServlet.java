import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/home"},loadOnStartup = 1)
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<HTML>" +
                "<HEAD>" +
                "<TITLE> Yifei_Home </TITLE>" +
                "</HEAD>" +
                "<BODY BGCOLOR=#cdd2e0>" +
                "<HR>" +
                "<H1>This website finally work</H1>" +
                "<H2>I'M SO HAPPY!!!^-^ <H2>" +
                "<HR>" +
                "</BODY>" +
                "</HTML>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.setContentType("text/html");
        resp.getWriter().write("Thank you client! "+reqBody);
    }

}
