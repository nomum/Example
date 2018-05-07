
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
public class t01{

    public static void main(String args[]){
        try {
            Robot robot = new Robot();
            BufferedImage bi = robot.createScreenCapture(new Rectangle(800 , 600));
            ImageIO.write(bi, "jpeg", new File("test.jpeg"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}