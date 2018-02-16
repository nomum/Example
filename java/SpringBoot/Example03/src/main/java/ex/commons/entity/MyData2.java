package ex.commons.entity;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import ex.commons.entity.MyData2Child;
import lombok.Data;

@Data
@Document(collection="myData2")
public class MyData2{

    @Id
    private String id;

    private String value;
    private MyData2Child child;
    private List<MyData2Child> childList;
}