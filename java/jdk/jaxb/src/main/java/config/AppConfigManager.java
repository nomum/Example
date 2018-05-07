package config;

import java.io.File;

import javax.xml.bind.JAXB;

public class AppConfigManager{


	static AppConfig config ;

	/**
	 * 初期化は何も指定しないとデフォルトのAppConfig.xml
	 */
	public static void init() {
		readFile("AppConfig.xml");
	}
	/**
	 * パスの指定もできるようにしておこう。
	 */
	public static void init(String filePath) {
		readFile(filePath);
	}
	private static void readFile(String filePath) {
		config = JAXB.unmarshal(new File(filePath), AppConfig.class);

	}
	public static AppConfig getConfig() {
		return config;
	}


	@Override
	public String toString() {
		return "AppConfigManager [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	//new抑止
	private AppConfigManager() {

	}



}