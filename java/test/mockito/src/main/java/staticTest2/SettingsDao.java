package staticTest2;

public class SettingsDao {
    /**
     * DBより税率を取得して返す。
     *
     * @return 税率
     */
    public double getTaxRate() {
      return 0.5;
    }
   
    /**
     * DBより送料を取得して返す。
     *
     * @return 送料
     */
    public int getTransportation() {
      return 500;
    }
   
    /**
     * 通貨記号を返す。
     * @return 通貨記号
     */
    public static String getCurrencySign() {
      return "円";
    }
   }