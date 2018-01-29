package BackboneEx.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ex01 {


	@RequestMapping("/")
	public String start() {
		return "index";
	}

}
