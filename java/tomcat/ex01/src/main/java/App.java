import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;

import tomcatTest.EmbeddedTomcat;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    public String getGreeting() {
        return "Hello world.";
    }
    static final String[] silences = new String[] {
        "org.apache.coyote.http11.Http11NioProtocol",
        "org.apache.catalina.core.StandardService",
        "org.apache.catalina.core.StandardEngine",
        "org.apache.catalina.startup.ContextConfig",
        "org.apache.catalina.core.ApplicationContext",
        "org.apache.catalina.core.AprLifecycleListener"
    };
    public static void main(String[] args) {
        
        EmbeddedTomcat tomcat = EmbeddedTomcat
                .create()
                .port(9000)
                .temporaryBaseDir()
                .webapp("", EmbeddedTomcat.toAbsolutePath("src/main/webapp"))
                .start();
        
        System.out.println("port : " + tomcat.getTomcat().getConnector().getLocalPort());
        tomcat.getTomcat().getServer().await();


        //System.out.println(new App().getGreeting());
        /*
        try{
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(9000);
            //tomcat.setHostName("127.0.0.1");
            

            Context context = tomcat.addWebapp("", ".");
            Tomcat.addServlet(context, "simpleServlet", new TestServlet01());
            context.addServletMappingDecoded("/t01", "simpleServlet");
    
            for (String s : silences) {
                Logger logger = Logger.getLogger(s);
                logger.setLevel(Level.FINEST);
            }

            tomcat.start();
            System.out.println("tomcat started");
            System.out.println(tomcat.getServer().getAddress() );
            System.out.println("port : " + tomcat.getConnector().getLocalPort());
            tomcat.getServer().await();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        */
        
    }
}
class TestServlet01 extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res){
        System.out.println("[Start]TestServlet01-doGet");
        try{
            res.getWriter().write("<html>");
            res.getWriter().write("<body>AAAA</body>");
            res.getWriter().write("</html>");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}