package ex.db2.dao;

import ex.db.entity.MyData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

public class MyDataDaoImpl implements MyDataDao<MyData>{

    private static final long serialVersionUID = 1L;

    //entityManagerの@PersistenceContextはアプリで一つのみ。
    //@PersistenceContext
    private EntityManager entityManager;

    public MyDataDaoImpl(){
        super();
    }
    public MyDataDaoImpl(EntityManager manager){
        this.entityManager = manager;
    }
    @Override
    // public List<MyData> getAll(){
    //     TypedQuery<MyData> query = entityManager.createQuery("from MyData",MyData.class);
    //     //List<MyData> list = (List<MyData>)query.getResultList();
    //     List<MyData> list = query.getResultList();
    //     entityManager.close();
    //     return list;
    // }
    public List<MyData> getAll(){
        List<MyData> list = null;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root);
        list = (List<MyData>)entityManager 
            .createQuery(query)
            .getResultList();

        return list;
    }

    @Override
    public MyData findById(long id){
        return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
    }
    @Override
    public List<MyData> findByName(String name){
        //return (List<MyData>)entityManager.createQuery("from MyData where name = " + name,MyData.class).getResultList();
        List<MyData> list = null;
        TypedQuery<MyData> query = entityManager
            .createNamedQuery("findWithName" , MyData.class)
            .setParameter("fname" , "%"+ name+"%");
        list = query.getResultList();
        return list;
    }

    @Override
    // public List<MyData> find(String fstr){
    //     List<MyData> list = null;

    //     //String qstr = "from MyData where id = :fstr";
    //     // TypedQuery<MyData> query = 
    //     //     entityManager.createQuery(qstr,MyData.class)
    //     //         .setParameter("fstr",Long.parseLong(fstr));

    //     String qstr = "from MyData where id = :fid or name like :fname or mail like :fmail";
    //     //String qstr = "from MyData where id = ?1 or name like ?2 or mail like ?3";
    //     Long fid = 0L;
    //     try {
    //         fid = Long.parseLong(fstr);
    //     }catch (Exception ex){
    //         ex.printStackTrace();
    //     }
    //     TypedQuery<MyData> query = entityManager.createQuery(qstr,MyData.class)
    //         .setParameter("fid",fid)
    //         .setParameter("fname" , "%" + fstr + "%")
    //         .setParameter("fmail",  fstr + "@%");
            

    //     list = query.getResultList();

    //     return list;
    // }
    public List<MyData> find(String fstr){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root)
            .where(builder.equal(root.get("name"),fstr));
        List<MyData> list = null;
        list = (List<MyData>)entityManager
            .createQuery(query)
            .getResultList();

        return list;
    }

    @Override
    public List<MyData> findByAge(int min,int max){
        return (List<MyData>)entityManager
            .createNamedQuery("findByAge",MyData.class)
            .setParameter("min",min)
            .setParameter("max",max)
            .getResultList();
    }

}