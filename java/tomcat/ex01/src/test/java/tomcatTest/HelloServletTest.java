package tomcatTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServletTest {
    private static EmbeddedTomcat tomcat;
/*
    @BeforeClass
    public static void setUpClass() {
        tomcat = EmbeddedTomcat
                .create()
                .port(8080)
                .temporaryBaseDir()
                .webapp("/", EmbeddedTomcat.toAbsolutePath("src/main/webapp"))
                .start();
    }

    @AfterClass
    public static void tearDownClass() {
        tomcat.shutdown();
    }

    @Test
    public void testServlet1() throws IOException {
        HttpURLConnection conn =
                (HttpURLConnection) URI
                        .create("http://localhost:8080/hello?name=Tomcat")
                        .toURL()
                        .openConnection();
        try (InputStreamReader isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            assertThat(reader.readLine())
                    .isEqualTo("Hello Tomcat!!");
        } finally {
            conn.disconnect();
        }
    }

    @Test
    public void testServlet2() throws IOException {
        HttpURLConnection conn =
                (HttpURLConnection) URI
                        .create("http://localhost:8080/hello")
                        .toURL()
                        .openConnection();
        try (InputStreamReader isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            assertThat(reader.readLine())
                    .isEqualTo("Hello World!!");
        } finally {
            conn.disconnect();
        }
    }
    */
}