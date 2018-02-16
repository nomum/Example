package ex.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import ex.db.repository.MyDataRepository;
import ex.db.entity.MyData;

public class MyDataBean{

    @Autowired
    MyDataRepository repository;


    public String getTableTagById(Long id){
        MyData data = repository.findById(id);
        String result = ""  
            + "<tr><td>" + data.getName()  +  "</td>"
            + "<td>" + data.getMail() + "</td>"
            + "<td>" + data.getAge() + "</td>"
            + "<td>" + data.getMemo() + "</td></tr>"
            ;

        return result;
    }

}