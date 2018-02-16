package ex.db2.controler;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
//import com.sun.tools.classfile.Module_attribute;

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

import ex.db.entity.MyData;
import ex.db2.dao.MyDataDaoImpl;

@Controller
public class HelloController2{

    //@Autowired
    //MyDataRepository2 repository;

    @PersistenceContext
    EntityManager entityManager;

    //@Autowired
    MyDataDaoImpl dao;


    @RequestMapping(value="/db2/" , method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){
        mav.setViewName("/db2/index");
        mav.addObject("msg","MyDataのサンプル");
        Iterable<MyData> list = dao.getAll();
        mav.addObject("dataList" ,list);
        return mav;
    }

    @RequestMapping(value="/db2/find" , method=RequestMethod.GET)
    public ModelAndView find(ModelAndView mav){
        mav.setViewName("/db2/find");
        mav.addObject("title","Find Page");
        mav.addObject("msg","MyDataのサンプルです。");
        mav.addObject("value","");
        Iterable<MyData> list = dao.getAll();
        mav.addObject("dataList",list);
        return mav;

    }

    @RequestMapping(value = "/db2/find" , method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request , ModelAndView mav){
        mav.setViewName("/db2/find");
        String param = request.getParameter("fstr");
        if (param == ""){
            mav = new ModelAndView("redirect:/db2/find");
        }else {
            mav.addObject("title","Find result");
            mav.addObject("msg","「" + param + "」の検索結果");
            mav.addObject("value",param);
            List<MyData> list = dao.find(param);
            mav.addObject("dataList",list);
        }
        return mav;
    }

    // @RequestMapping(value="/db2/" ,method=RequestMethod.GET)
    // public ModelAndView index(
    //         @ModelAttribute("formModel") MyData myData,
    //         ModelAndView mav
    //     ){
    //     System.out.println("[START]/db2/ - GET");
    //     mav.setViewName("/db2/index");
    //     mav.addObject("msg","this is sample content.");
    //     Iterable<MyData> list = repository.findAll();
    //     mav.addObject("datalist",list);
    //     list.forEach(val ->{
    //         System.out.println(val.getName());
    //     });
    //     //System.out.println("list size : " + list.length());
    //     System.out.println("[END]/db2/ - GET");
    //     return mav;
    // }

    // @Transactional(readOnly=false)
    // @RequestMapping(value="/db2/" , method=RequestMethod.POST)
    // public ModelAndView form(
    //     @ModelAttribute("formModel") @Validated MyData myData,
    //     BindingResult result,
    //     ModelAndView mav
    // ){
    //     ModelAndView res = null;
    //     if (!result.hasErrors()){
    //         //エラーなしの場合登録
    //         repository.saveAndFlush(myData);
    //         res = new ModelAndView("redirect:/db2/");
    //     }else {
    //         mav.setViewName("/db2/index");
    //         mav.addObject("msg","sorry, error is occured....");
    //         Iterable<MyData> list = repository.findAll();
    //         res = mav;
    //     }
    //     return  res;
    //     /*
    //     System.out.println("[START]/db/ - POST");
    //     repository.saveAndFlush(myData);
    //     System.out.println("[START]/db/ - POST");
    //     return new ModelAndView("redirect:/db/");*/
    // }

    // @RequestMapping(value="/db2/edit/{id}" , method=RequestMethod.GET)
    // public ModelAndView edit(@ModelAttribute MyData mydata , @PathVariable long id , ModelAndView mav){
    //     mav.setViewName("edit");
    //     mav.addObject("title","edit mydata.");
    //     MyData data = repository.findById((Long)id);
    //     mav.addObject("formModel",data);
    //     return mav;
    // }
    // @RequestMapping(value="/db2/edit" , method = RequestMethod.POST)
    // @Transactional(readOnly = false)
    // public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav){
    //     repository.saveAndFlush(mydata);
    //     return new ModelAndView("redirect:/db2/");
    // }

    // @RequestMapping(value="/db2/delete/{id}" , method =RequestMethod.GET)
    // public ModelAndView delete(@PathVariable int id , ModelAndView mav){
    //     mav.setViewName("/db2/delelte");
    //     mav.addObject("title","delte mydata.");
    //     MyData data = repository.findById((long)id);
    //     mav.addObject("formModel",data);
    //     return mav;
    // }
    // @RequestMapping(value="/db2/delete" ,method=RequestMethod.POST)
    // @Transactional(readOnly=false)
    // public ModelAndView remove(@RequestParam long id,ModelAndView mav){
    //     repository.delete(id);
    //     return new ModelAndView("redirect:/db2");
    // }
     /**
     * 初期化処理
     */
    @PostConstruct
    public void init()
    {
        this.dao = new MyDataDaoImpl(entityManager);
    }
}