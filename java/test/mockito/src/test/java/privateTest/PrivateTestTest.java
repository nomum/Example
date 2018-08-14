package privateTest;

import org.junit.runner.RunWith;
import org.junit.Test;

import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateTest.class)
public class PrivateTestTest{


    @Test
    public void test() throws Exception{
        PrivateTest target = PowerMockito.spy(new PrivateTest());


        PowerMockito.when(target,"privateMethod",Mockito.anyString()).thenReturn("Test return ");
        String actual = target.publicMethod("test");
        //PowerMockito.verifyPrivate(target,Mockito.times(2)).invoke("privateMethod",Mockito.anyString());
        PowerMockito.verifyPrivate(target,Mockito.times(1)).invoke("privateMethod",Mockito.anyString());


        assertThat(actual,is("Call publicMethod val : test  -  Test return "));
    }



}
