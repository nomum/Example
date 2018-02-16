package ex.mongo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import ex.commons.entity.MyDataMongo;



public interface MyDataMongoRepository 
    extends MongoRepository<MyDataMongo , String>{

    public List<MyDataMongo> findByName(String name);
}