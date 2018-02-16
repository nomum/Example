package ex.commons.entity;

import java.util.Date;

import com.sun.javafx.beans.IDProperty;

//import org.springframework.data.annotation.Id;
import javax.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyDataMongo{

    @Id
    private String id;

    private String name;
    private String memo;
    private Date date;

    public MyDataMongo(String name , String memo){
        this.name = name;
        this.memo = memo;
    }
    
}