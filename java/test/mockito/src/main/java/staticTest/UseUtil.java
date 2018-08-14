package staticTest;


public class UseUtil{

    public static String aa(String val){
        System.out.println("Call UseUtil - aa");
        return Util.staticMethod(val);
    }

}