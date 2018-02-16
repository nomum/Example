package ex.db.entity;

//import javax.annotation.processing.Generated;
//import javax.persistence.
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import ex.commons.validator.Phone;
import lombok.Data;

@Data
@Entity
@Table(name="mydata")
@NamedQueries({
    @NamedQuery(
        name="findWithName",
        query="from MyData where name like :fname"
    ),
    @NamedQuery(
        name="findByAge",
        query="from MyData where age > :min and age < :max"
    )}
)
public class MyData{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    //@NotNull
    private Long id;

    @Column(length=50,nullable =false)
    @NotEmpty(message="空白は不可")
    private String name;

    @Column(length = 200 , nullable = true)
    @Email
    private String mail;

    @Column(nullable = true)
    @Min(0)
    @Max(200)
    private Integer age;

    @Column(nullable=true)
    @Phone(onlyNumber=true)
    private String memo;
}