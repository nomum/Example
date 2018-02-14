package ex.db.repository;

import ex.db.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDataRepository extends JpaRepository<MyData,Long>{

    public MyData findById(Long name);
}
