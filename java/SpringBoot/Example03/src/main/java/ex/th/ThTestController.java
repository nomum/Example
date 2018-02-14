package ex.th;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ex.db.repository.MyDataRepository;
import ex.db.entity.MyData;

@Controller
public class ThTestController{

    @RequestMapping(value="thtest1" )
    public String index(){
        return "/th/test1";
    }

    @RequestMapping(value="thtest2/{id}")
    public ModelAndView test2(@PathVariable int id , ModelAndView mav){
        mav.setViewName("/th/test2");
        mav.addObject("id",id);
        mav.addObject("check" , id % 2 == 0);
        mav.addObject("trueVal","Even number!");
        mav.addObject("falseVal" , "Odd number...");
        return mav;


    }

    @RequestMapping(value="thtest3/{month}")
    public ModelAndView test3(@PathVariable int month , ModelAndView mav){
        mav.setViewName("/th/test3");
        int m = Math.abs(month) % 12;
        m = m == 0 ? 12 : m;
        mav.addObject("month",m);
        mav.addObject("check",Math.floor(m /3));
        return mav;
    }

    @RequestMapping(value="thtest4")
    public ModelAndView test4(ModelAndView mav){
        mav.setViewName("/th/test4");
        ArrayList<String[]> data = new ArrayList<String[]>();
        data.add(new String[]{"taro","taro@yamda","0907999-999"});
        data.add(new String[]{"hanako","hanako@flower","0907999-111"});
        data.add(new String[]{"hanako2","hanako2@flower","0907999-222"});
        mav.addObject("data" , data);
        return mav;
    }

    @RequestMapping(value="thtest5/{num}")
    public ModelAndView test5(@PathVariable int num , ModelAndView mav){
        mav.setViewName("/th/test5");
        mav.addObject("num",num);
        if(num >= 0){
            mav.addObject("check","num >= data.size() ? 0 : num");

        }else{
            mav.addObject("check","num <= data.size() ` -1 ? 0 : num * -1");
        }

        ArrayList<DataObject> data = new ArrayList<DataObject>();

        data.add(new DataObject(0,"taro","taro@yamada"));
        data.add(new DataObject(0,"taro2","taro2@yamada"));
        data.add(new DataObject(0,"taro3","taro3@yamada"));
        mav.addObject("data",data);
        return mav;
 
    }

    @RequestMapping(value="thtest6/{tax}")
    public ModelAndView test6(@PathVariable int tax , ModelAndView mav){
        mav.setViewName("th/test6");
        mav.addObject("tax",tax);
        return mav;
    }

    @RequestMapping(value="thtest7")
    public ModelAndView test7(ModelAndView mav){
        mav.setViewName("th/test7");
        return mav;
    }
}