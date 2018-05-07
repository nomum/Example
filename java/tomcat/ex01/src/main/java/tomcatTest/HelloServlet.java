package tomcatTest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res){
        //res.getWriter().write("<html><body>AAA</body></html>");
        try {
        res.getWriter().write("<html><body>AAA</body></html>");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}



