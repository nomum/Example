package sample.jetty;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HelloServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException{
        res.getWriter().println("Hello world!");
    }

}
