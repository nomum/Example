package staticTest2;


public class CalcLogic {
    /**
     * 設定の税率で金額を求め、送料を付与して返す。
     *
     * @param price
     *            金額
     * @return 合計金額
     */
    public int calcPrice(int price) {
      // インラインでインスタンスを取得しているのでMoc化できない。
      // SettingsDao settingsDao = new SettingsDao();
      // メソッド化
   
      SettingsDao settingsDao = getInstance();
      return (int) Math.floor(price * (1 + settingsDao.getTaxRate()))
          + settingsDao.getTransportation();
    }
   
    /**
     * 設定の税率で金額を求め、送料を含んだ料金に関して、文字列表現を返す(通貨記号を付与する)。
     *
     * @param price
     *            金額
     * @return 合計金額(通貨記号付きの文字列表現)
     */
    public String calcDispPrice(int price) {
      return calcPrice(price) + SettingsDao.getCurrencySign();
    }
   
    /**
     * 設定情報取得Daoを返却する。
     *
     * @return
     */
    protected SettingsDao getInstance() {
      return new SettingsDao();
    }
   }