package ex01;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;

public class main{
    public static void main(String[] args){
        System.out.println("Start main");

        try{
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8091);
            Context context = tomcat.addWebapp("/",".");
            //Context context = tomcat.addWebapp("app",".");

            tomcat.addServlet(context , "TestServlet", new TestServlet01());
            context.addServletMappingDecoded("/test01","TestServlet");
            tomcat.start();
            tomcat.getServer().await();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}