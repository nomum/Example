package ex.service.service;


import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ex.db.entity.MyData;
import ex.db.repository.MyDataRepository;

@Service
public class MyDataService{

    @PersistenceContext
    private EntityManager entityManager;

    public List<MyData> getAll(){
        return  (List<MyData>)entityManager  
            .createQuery("from MyData",MyData.class).getResultList();
    }
    public MyData get(int num){
        return (MyData)entityManager
            .createQuery("from MyData where id = " + num)
            .getSingleResult();
    }
    public List<MyData> find(String fstr){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root).where(builder.equal(root.get("name"),fstr));
        List<MyData> list = null;
        list = (List<MyData>)entityManager.createQuery(query).getResultList();
        return list;
    }
    private static final int PAGE_SIZE = 2;
    @Autowired
    MyDataRepository repository;
    public Page<MyData> getMyDataInPage(Integer pageNumber){
        PageRequest pageRequest = new PageRequest(pageNumber - 1 , PAGE_SIZE);
        return repository.findAll(pageRequest);
    }
}