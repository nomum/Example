//package jp.co.toshi_sol.btr.util;
import javax.xml.bind.annotation.XmlEnumValue;
/**
 *キャラクターコード
 * @author 00181nomura
 *
 */
public enum CharacterCode {
	@XmlEnumValue("EUC_JP") EUC_JP("EUC-JP"),
	@XmlEnumValue("GBK") GBK("GBK"),
	@XmlEnumValue("EUC_KR") EUC_KR("EUC-KR"),
	@XmlEnumValue("UTF8") UTF8("UTF-8");

	/**
	 * 文字コード定義文字列
	 * <pre>
	 * 文字コード変換時の設定パラメータとして使用
	 * </pre>
	 */
	private String code;
	/**
	 * コンストラクタ
	 * @param code 文字コード
	 */
	CharacterCode(String code){
		this.code = code;
	}

	/**
	 * 文字コード文字列の取得
	 * @return 文字コード定義文字列
	 */
	public String forNmae() {
		return this.code;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.code;
	}

}