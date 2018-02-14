package ex.db.entity;

//import javax.annotation.processing.Generated;
//import javax.persistence.
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="mydata")
public class MyData{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(length=50,nullable =false)
    private String name;

    @Column(length = 200 , nullable = true)
    private String mail;
    @Column(nullable = true)
    private Integer age;
    @Column(nullable=true)
    private String memo;
}