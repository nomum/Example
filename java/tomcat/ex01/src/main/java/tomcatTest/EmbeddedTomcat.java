package tomcatTest;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcat {
    private Tomcat tomcat;
    private boolean temporaryBaseDir = false;
    private String baseDir;
    private int port;
    private Context context;
    public Tomcat getTomcat(){
        return this.tomcat;
    }
    protected EmbeddedTomcat(Tomcat tomcat) {
        this.tomcat = tomcat;
    }

    public static EmbeddedTomcat create() {
        return new EmbeddedTomcat(new Tomcat());
    }

    public static EmbeddedTomcat create(Tomcat tomcat) {
        return new EmbeddedTomcat(tomcat);
    }

    public EmbeddedTomcat port(int port) {
        tomcat.setPort(port);
        this.port = port;
        return this;
    }

    public EmbeddedTomcat temporaryBaseDir() {
        temporaryBaseDir = true;
        return baseDir(createTemporaryPath("tomcat", Integer.toString(port)));
    }

    public EmbeddedTomcat baseDir(String path) {
        tomcat.setBaseDir(path);
        baseDir = path;
        return this;
    }

    public EmbeddedTomcat webapp(String contextPath, String docBase) {
        try {
            if (context == null) {
                context = tomcat.addWebapp(contextPath, docBase);
            } else {
                throw new IllegalArgumentException("Only support, one webapp.");
            }
            return this;
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public EmbeddedTomcat start() {
        try {
            tomcat.start();
            return this;
        } catch (LifecycleException e) {
            throw new IllegalStateException(e);
        }
    }

    public EmbeddedTomcat await() {
        tomcat.getServer().await();
        return this;
    }

    public void shutdown() {
        try {
            tomcat.stop();
            tomcat.destroy();

            if (temporaryBaseDir) {
                Files.walkFileTree(Paths.get(baseDir), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path directory, IOException e) throws IOException {
                        Files.delete(directory);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (LifecycleException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String toAbsolutePath(String path) {
        return Paths.get(path).toAbsolutePath().toString();
    }

    public static String createTemporaryPath(String prefix, String suffix) {
        try {
            File tempDir = File.createTempFile(prefix, suffix);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}