package a;
import jp.go.fss.btr.LogTest;
import jp.go.fss.btr.log.AccesslogUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.mock.web.MockHttpServletRequest;


public class JavaMain{

    
    public static void main(String[] args){
        
        LogTest t = new LogTest();
        t.logTest();
/*
        HttpServletRequest request = new HttpServletRequest(){
            //HttpServletRequest内のabstractメソッド<T>upgrade(Class<T>)をオーバーライドしません
            @Override
            public String upgrade(Class<String> handlerClass){
            //public <T extends HttpUpgradeHandler> upgrade(Class<T> handlerClass){
                return null;
            }
            
        };
        AccesslogUtil.putAccessLog(request, "ABCDEFG", "VWZ");
        */
        MockHttpServletRequest req = new MockHttpServletRequest("POST","http://test.jp");
        //req.setParamter();
        AccesslogUtil.putAccessLog(req, "ABCDEFG", "VWZ");

    }
}
/*
class aa  extends HttpUpgradeHandler{

}*/