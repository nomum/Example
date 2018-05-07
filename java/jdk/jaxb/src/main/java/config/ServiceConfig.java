package config;

//import jp.co.toshi_sol.btr.util.CharacterCode;
import javax.xml.bind.annotation.XmlAttribute;
/**
 * サービスごとの設定
 * @author 00181nomura
 *
 */
public class ServiceConfig {
	// FIXME getter/setterを作る

	/**
	 * 国コード
	 */
	//@XmlAttribute
	private String countryCode;
	public String getCountryCode(){
		return this.countryCode;
	}
	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;

	}
	private CharacterCode sourceCaharacterCode ;
	public  CharacterCode getSourceCaharacterCode(){
		return  this.sourceCaharacterCode;
	}
	public void set setSourceCaharacterCode( CharacterCode sourceCaharacterCode){
		this.sourceCaharacterCode =  sourceCaharacterCode;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("ServiceConfig------------------------")
		.append("countryCode : " + countryCode + "\r\n");
		return sb.toString();
	}
	/**
	 * 原文文字コード
	 */
	//private CharacterCode originalDocumentCharacterCode;
	/**
	 * 翻訳文文字コード
	 */
	//private CharacterCode TranslationDOcumentCharacterCode;
}
