package staticTest;

import junit.framework.TestCase;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Util.class)
public class UtilTest {

    @Test
    public void staticMethodTest1(){
        System.out.println("staticMethodTest1 - 1");
        PowerMockito.mockStatic(Util.class);
        System.out.println("staticMethodTest1 - 2");
        Mockito.when(Util.staticMethod("aa")).thenReturn("staticMethod aa");
        System.out.println("staticMethodTest1 - 3");
        //PowerMockito.verifyStatic(Util.class,Mockito.times(2));
        String r3 = UseUtil.aa("aa");
        String r3_2 = UseUtil.aa("aa");
        System.out.println("staticMethodTest1 - 4");
        //PowerMockito.verifyStatic(Util.class,Mockito.times(5));
        PowerMockito.verifyStatic(Mockito.times(3));
        //PowerMockito.verifyStatic(Util.class,Mockito.times(100));
        assertEquals(r3,"staticMethod aa");
        System.out.println("staticMethodTest1 - 5");
    }
    @Test
    public void staticMethodTest2(){
        PowerMockito.mockStatic(Util.class);
        Mockito.when(Util.staticMethod(Mockito.anyString())).thenReturn("ABC");
        //when(SettingsDao.getCurrencySign()).thenReturn(" JPY");
        assertEquals("ABC",Util.staticMethod("DEF"));
        assertEquals("ABC",Util.staticMethod("DEF"));
        //PowerMockito.verifyStatic(Mockito.times(3));
        PowerMockito.verifyStatic(Mockito.times(2));
        Util.staticMethod("DEF");

    }

    //@Test
    public void staticMethodTest() {
        PowerMockito.mockStatic(Util.class);
        Mockito.when(Util.staticMethod("hoge")).thenReturn("staticMethod hoge");
        Mockito.when(Util.staticMethod("uga")).thenReturn("staticMethod uga");
        Mockito.when(Util.staticMethod("aa")).thenReturn("staticMethod aa");

        String r1 = Util.staticMethod("hoge");
        String r2 = Util.staticMethod("uga");
        String r3 = UseUtil.aa("aa");


        PowerMockito.verifyStatic(Util.class,Mockito.times(1));
        Util.staticMethod("hoge");

        PowerMockito.verifyStatic(Util.class,Mockito.times(1));
        Util.staticMethod("uga");

        PowerMockito.verifyStatic(Util.class,Mockito.times(1));
        //UseUtil.aa("aa");

        assertEquals("staticMethod hoge", r1);
        assertEquals("staticMethod uga", r2);
    }


}