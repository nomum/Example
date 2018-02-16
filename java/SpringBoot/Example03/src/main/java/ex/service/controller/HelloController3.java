package ex.service.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ex.db.entity.MyData;
import ex.db.repository.MyDataRepository;
import ex.service.service.MyDataService;

@Controller
public class HelloController3{

    @Autowired
    MyDataRepository repository;

    @Autowired
    private MyDataService service;

    // @RequestMapping(value = "/svc/find" , method = RequestMethod.GET)
    // public ModelAndView index(ModelAndView mav){
    //     mav.setViewName("/svc/index");
    //     mav.addObject("title","find Page");
    //     mav.addObject("msg","MyDataのサンプルです。");
    //     List<MyData> list = service.getAll();
    //     mav.addObject("dataList",list);
    //     return mav;

    // }

    // @RequestMapping(value = "/svc/find" , method = RequestMethod.GET)
    // public ModelAndView find(ModelAndView mav){
    //     mav.setViewName("/svc/find");
    //     mav.addObject("title","find page");
    //     mav.addObject("msg","MyDataのサンプルです。");
    //     mav.addObject("value","");
    //     List<MyData> list = service.getAll();
    //     mav.addObject("dataList",list);
    //     return mav;
    // }
    
    // @RequestMapping(value="/svc/find" , method=RequestMethod.POST)
    // public ModelAndView search(HttpServletRequest request , ModelAndView mav){
    //     mav.setViewName("/svc/find");
    //     String param = request.getParameter("fstr");
    //     if(param == ""){
    //         mav = new ModelAndView("redirect:/svc/find");
    //     }else{
    //         mav.addObject("title","find result");
    //         mav.addObject("msg","「" + param + "」の検索結果");
    //         mav.addObject("value",param);
    //         List<MyData> list = service.find(param);
    //         mav.addObject("dataList",list);
    //     }
    //     return mav;
    // }
    @PostConstruct
    public void init(){

    }
}