package ex03.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ex03.repository.MyDataRepository;
import ex03.entity.MyData;

@Controller
public class HelloController{

    @Autowired
    MyDataRepository repository;

    @RequestMapping(value="/" ,method=RequestMethod.GET)
    public ModelAndView index(
            @ModelAttribute("formModel") MyData myData,
            ModelAndView mav
        ){
        System.out.println("[START]/ - GET");
        mav.setViewName("index");
        mav.addObject("msg","this is sample content.");
        Iterable<MyData> list = repository.findAll();
        mav.addObject("datalist",list);
        list.forEach(val ->{
            System.out.println(val.getName());
        });
        //System.out.println("list size : " + list.length());
        System.out.println("[END]/ - GET");
        return mav;
    }
    @RequestMapping(value="/" , method=RequestMethod.POST)
    public ModelAndView form(
        @ModelAttribute("formModel") MyData myData,
        ModelAndView mav
    ){
        System.out.println("[START]/ - POST");
        repository.saveAndFlush(myData);
        System.out.println("[START]/ - POST");
        return new ModelAndView("redirect:/");
    }
}