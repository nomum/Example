package ex03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class SpringMain{

    public static void main(String[] args){
        SpringApplication.run(SpringMain.class,args);
    }
}


