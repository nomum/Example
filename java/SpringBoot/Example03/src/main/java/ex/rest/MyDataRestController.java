package ex.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ex.db.entity.MyData;
import ex.service.service.MyDataService;

@RestController
public class MyDataRestController{

    @Autowired
    private MyDataService service;

    @RequestMapping("/rest")
    public List<MyData> restAll(){
        return service.getAll();
    }
    @RequestMapping("/rest/{num}")
    public MyData restBy(@PathVariable int num){
        return service.get(num);
    }
}