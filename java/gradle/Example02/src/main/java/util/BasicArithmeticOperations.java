package util;

import java.math.BigDecimal;

/**
 * 高精度の四則演算を行うライブラリ
 * <p>
 * {@link java.lang.String}又は、{@link java.math.BigDecimal}を行います
 *
 **/
public class BasicArithmeticOperations{
  static public BigDecimal plus(String a , String b){
    return plus(new BigDecimal(a) , new BigDecimal(b));
  }
  static public BigDecimal plus(BigDecimal a , BigDecimal b){
    return a.add(b);
  }
}
