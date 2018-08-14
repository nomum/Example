package privateTest;

public class PrivateTest{

    public String publicMethod(String str) throws Exception{
        System.out.println("Call publicMethod!");
        return "Call publicMethod val : " + str + "  -  " +  privateMethod(str);
    }
    private String privateMethod(String str) throws Exception {
        System.out.println("Call privateMethod!");
        throw new Exception();
        //return "Call private Method val :" + str;
    }


}