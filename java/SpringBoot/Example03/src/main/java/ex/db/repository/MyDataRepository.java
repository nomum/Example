package ex.db.repository;

import ex.db.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MyDataRepository extends JpaRepository<MyData,Long>{

    public MyData findById(Long name);
    public List<MyData> findByNameLike(String name);
    public List<MyData> findByIdNotNullOrderByIdDesc();
    public List<MyData> findByAgeGreaterThan(Integer age);
    public List<MyData> findByAgeBetween(Integer age1,Integer age2);

    @Query("SELECT d FROM MyData d ORDER BY d.name")
    public List<MyData> findAllOrderByName();

    @Query("from MyData where age > :min and age < :max")
    public List<MyData> findByAge(@Param("min" ) int min , @Param("max") int max);
}
