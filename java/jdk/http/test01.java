
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;

public class test01{

    public static void main(String[] args){
        try{
            System.out.println("Start");


            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy", 80));
            URL url = new URL("http://www.yahoo.co.jp");
            HttpURLConnection con = (HttpURLConnection)url.openConnection(proxy);
            con.setRequestMethod("GET");
            con.connect();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK || con.getResponseCode() == 301){
                System.out.println("Request OK");
                StringBuffer result = new StringBuffer();
                final InputStream in = con.getInputStream();
                String encoding = con.getContentEncoding();
                if(null == encoding){
                    encoding = "UTF-8";
                }
                final InputStreamReader inReader = new InputStreamReader(in, encoding);
                final BufferedReader bufReader = new BufferedReader(inReader);
                String line = null;
                while((line = bufReader.readLine()) != null) {
                    result.append(line).append("\r\n");
                }
                bufReader.close();
                inReader.close();
                in.close();
                System.out.println(result);

            }else {
                System.out.println("Request error : " + con.getResponseCode());
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}