package config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * アプリケーションコンフィグ
 * @author 00181nomura
 *
 */
public class AppConfig{
    // FIXME getter/setterを作る
    private String name;
    public String  getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
   /**
     * サービス設定
     * <pre>
     * キー：国コード
     * データ：サービス設定
     * </pre>
     */

    List<ServiceConfig> services;

    public List<ServiceConfig> getServices(){
        return this.services;
    }
    @XmlElementWrapper(name="services")
    @XmlElement(name="serviceConfig")
    public void setServices(List<ServiceConfig> services ){
        this.services = services;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb
            .append("AppConfig-----------------")
            .append("name : " + this.name + "\r\n")
            .append("services : -----------------------"  + "\r\n" );
            //.append("services : -----------------------"  + "\r\n" )

        for (ServiceConfig val : services ){
            sb.append(val.toString());
            //System.out.println(val.toString());
        }
        return sb.toString();
    }
}