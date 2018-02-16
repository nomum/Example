package ex.mongo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import ex.commons.entity.MyData2;



public interface MyData2MongoRepository 
    extends MongoRepository<MyData2 , String>{

    //public List<MyData2> findByName(String name);
}