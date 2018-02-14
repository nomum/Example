package ex.th;

import lombok.Data;

@Data
public class DataObject{
    int id;
    String name;
    String value;
    public DataObject(int id , String name,String value){
        this.id = id;
        this.name = name;
        this.value = value;
    }
}