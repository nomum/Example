package staticTest2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//import dao.SettingsDao;

//PowerMock利用
@RunWith(PowerMockRunner.class)
//SettingsDaoに対して利用
@PrepareForTest(SettingsDao.class)
public class CalcLogicMockItTest {
 @Test
 public void testTestCalcPrice() {
   CalcLogic logic = spy(new CalcLogic());
   SettingsDao dao = spy(new SettingsDao());

   when(dao.getTaxRate()).thenReturn(0.03);
   when(dao.getTransportation()).thenReturn(200);

   when(logic.getInstance()).thenReturn(dao);
   int calcPrice = logic.calcPrice(200);
   assertEquals(406, calcPrice);
 }
 @Test
 public void testTestCalcPriceStatic() {
   CalcLogic logic = spy(new CalcLogic());

   //SettingsDaoに対して利用する宣言
   //全メソッドに対してスタブを利用する宣言(設定を忘れると呼び出し結果でnullが返ったりする）
   //ミスで呼ばれたりすると困るような重要なメソッドがあるような場合には保険になるかも
   PowerMockito.mockStatic(SettingsDao.class);
   //設定をしたメソッドにだけスタブを利用する宣言
   //明示的に出来て便利だが、ミスで実メソッドが呼ばれて困る事があるかも
   //PowerMockito.spy(SettingsDao.class);

   when(SettingsDao.getCurrencySign()).thenReturn(" JPY");

   when(logic.calcPrice(200)).thenReturn(2999);
   //特定の引数に意味が無いような場合、以下にしておくとどん引数でもという条件となる
   //when(logic.calcPrice(anyInt())).thenReturn(2999);

   String calcPrice = logic.calcDispPrice(200);

   assertEquals("2999 JPY", calcPrice);

   //以下モックに対する検証
   //getCurrencySignが2回呼ばれたことを検証(1回しか呼ばれないので当然失敗する)
   //SettingsDao.getCurrencySign();
   PowerMockito.verifyStatic(times(2));
   SettingsDao.getCurrencySign();
   
 }
}