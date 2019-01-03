      //sample.jetty
package sample.jetty;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet")
public class SampleServlet extends HttpServlet{


    private static final long serrialVersionID = 1L;


    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException{
        res.getWriter().println("war on jetty.");

    }
}

