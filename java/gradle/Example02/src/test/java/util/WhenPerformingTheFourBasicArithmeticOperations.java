package util;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import static org.junit.Assert.*;


@RunWith(Enclosed.class)
public class WhenPerformingTheFourBasicArithmeticOperations{
  public static class InAddition{
    @Test
    public void showIdReturnTheSumOfNumbersWhickAreNumericString(){
        assertEquals(new BigDecimal(2),BasicArithmeticOperations.plus("1","1"));
    }
  }
}
