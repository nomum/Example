
package example01.json;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloJson{

    @RequestMapping("/helloJson")
    public Map<String,String> hello(){
        return Collections.singletonMap("message", "hello world! json");
    }
}
