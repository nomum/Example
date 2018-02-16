package ex.mongo;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;

import ex.mongo.MyDataMongoRepository;
import ex.mongo.MyData2MongoRepository;
import ex.commons.entity.MyData2;
import ex.commons.entity.MyData2Child;
import ex.commons.entity.MyDataMongo;



@Controller
public class MongoController{
    @Autowired
    MyDataMongoRepository repository;
    @Autowired
    MyData2MongoRepository repository2;

    @RequestMapping(value="/mongo/" , method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){
        mav.setViewName("/mongo/index");
        mav.addObject("title" , "Find Page");
        mav.addObject("msg","MyDataMongoのサンプル");
        Iterable<MyDataMongo> list = repository.findAll();
        mav.addObject("dataList",list);
        return mav;
    }

    @RequestMapping(value="/mongo/" , method=RequestMethod.POST)
    @Transactional
    public ModelAndView form(
        @RequestParam("name") String name,
        @RequestParam("memo") String memo,
        ModelAndView mav
    ){
        MyDataMongo mydata = new MyDataMongo(name,memo);
        repository.save(mydata);
        return new ModelAndView("redirect:/mongo/");
    }

    @RequestMapping(value="/mongo/find" , method=RequestMethod.GET)
    public ModelAndView find(ModelAndView mav){
        mav.setViewName("/mongo/find");
        mav.addObject("title","find page");
        mav.addObject("msg" , "MyDataMongoのサンプル");
        mav.addObject("value","");
        List<MyDataMongo> list = repository.findAll();
        mav.addObject("dataList",list);
        return mav;

    }
    @RequestMapping(value="/mongo/find" , method=RequestMethod.POST)
    public ModelAndView search(
        @RequestParam("find") String param,
        ModelAndView mav
    ){
        mav.setViewName("/mongo/find");
        if(param == ""){
            mav = new ModelAndView("redirect:mongo/find");
        }else{
            mav.addObject("title","find result");
            mav.addObject("msg","「" + param + "」の検索結果");
            mav.addObject("value",param);
            List<MyDataMongo> list = repository.findByName(param);
            mav.addObject("dataList",list);
        }
        return mav;
    }
    @PostConstruct
    public void init(){
        System.out.println("[Start]MongoController - init");
        MyData2 data = new MyData2();
        data.setValue("aaa");
        MyData2Child child = new MyData2Child();
        child.setName("name1");
        child.setMemo("memo1");
        data.setChild(child);

        List<MyData2Child> list = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++){
            child = new MyData2Child();
            child.setName("name" + i);
            child.setMemo("memo" + i);
            list.add(child);
                
        }
        data.setChildList(list);
        
        repository2.save(data);
        System.out.println("[end]MongoController - init");
    }
}


