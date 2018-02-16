package ex.db.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import ex.db.repository.MyDataRepository;
import ex.db.entity.MyData;

@Controller
public class HelloController{

    @Autowired
    MyDataRepository repository;

    @RequestMapping(value="/db/" ,method=RequestMethod.GET)
    public ModelAndView index(
            @ModelAttribute("formModel") MyData myData,
            ModelAndView mav
        ){
        System.out.println("[START]/db/ - GET");
        mav.setViewName("/db/index");
        mav.addObject("msg","this is sample content.");
        //Iterable<MyData> list = repository.findAll();
        Iterable<MyData> list = repository.findAllOrderByName();
        
        mav.addObject("datalist",list);
        list.forEach(val ->{
            System.out.println(val.getName());
        });
        //System.out.println("list size : " + list.length());
        System.out.println("[END]/db/ - GET");
        return mav;
    }

    @Transactional(readOnly=false)
    @RequestMapping(value="/db/" , method=RequestMethod.POST)
    public ModelAndView form(
        @ModelAttribute("formModel") @Validated MyData myData,
        BindingResult result,
        ModelAndView mav
    ){
        ModelAndView res = null;
        if (!result.hasErrors()){
            //エラーなしの場合登録
            repository.saveAndFlush(myData);
            res = new ModelAndView("redirect:/db/");
        }else {
            mav.setViewName("/db/index");
            mav.addObject("msg","sorry, error is occured....");
            Iterable<MyData> list = repository.findAll();
            res = mav;
        }
        return  res;
        /*
        System.out.println("[START]/db/ - POST");
        repository.saveAndFlush(myData);
        System.out.println("[START]/db/ - POST");
        return new ModelAndView("redirect:/db/");*/
    }

    @RequestMapping(value="/db/edit/{id}" , method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData mydata , @PathVariable long id , ModelAndView mav){
        mav.setViewName("edit");
        mav.addObject("title","edit mydata.");
        MyData data = repository.findById((Long)id);
        mav.addObject("formModel",data);
        return mav;
    }
    @RequestMapping(value="/db/edit" , method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav){
        repository.saveAndFlush(mydata);
        return new ModelAndView("redirect:/db/");
    }

    @RequestMapping(value="/db/delete/{id}" , method =RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id , ModelAndView mav){
        mav.setViewName("/db/delelte");
        mav.addObject("title","delte mydata.");
        MyData data = repository.findById((long)id);
        mav.addObject("formModel",data);
        return mav;
    }
    @RequestMapping(value="/db/delete" ,method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView remove(@RequestParam long id,ModelAndView mav){
        repository.delete(id);
        return new ModelAndView("redirect:/db");
    }
    /**
     * 初期化処理
     */
    //@PostConstruct
    public void init()
    {

    }
}