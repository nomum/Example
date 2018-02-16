package ex.configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import ex.service.service.MyDataService;
@Controller
public class HelloController4{

    @Autowired
    MyDataBean myDataBean;
    @Autowired
    private MyDataService service;
    @RequestMapping(value = "/conf/{id}" , method = RequestMethod.GET)
    public ModelAndView indexById(@PathVariable long id , ModelAndView mav){
        mav.setViewName("/conf/pickup");
        mav.addObject("title","Pickup Page");
        String table = "<table>"
            + myDataBean.getTableTagById(id)
            + "</table>";
            mav.addObject("msg","pickup data id = " + id);
            mav.addObject("data",table);
            return mav;
    }

    @RequestMapping(value="/page/{num}" , method=RequestMethod.GET)
    public ModelAndView page(@PathVariable Integer num,ModelAndView mav){
        Page<MyData> page = service.getMyDataInPage(num);
        mav.setViewName("page");
        mav.addObject("title","find Page");
        mav.addObject("msg" , "");
        mav.addObject("pagenumber",num);
        mav.addObject("dataList",page);
        return mav;
    }
}