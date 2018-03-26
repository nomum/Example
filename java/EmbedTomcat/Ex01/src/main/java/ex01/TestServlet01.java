package ex01;

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet01 extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res){
        System.out.println("[Start]TestServlet01 - doGet");
        try{
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<html><body><h1>Hello world!</h1></body></html>");
            out.flush();
            out.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}