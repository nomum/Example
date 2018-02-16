package ex.db2.dao;

import java.util.List;
import java.io.Serializable;

public interface MyDataDao<T> extends Serializable{
    public List<T> getAll();
    public T findById(long id);
    public List<T> findByName(String name);
    public List<T> find(String fstr);
    public List<T> findByAge(int min,int max);
}