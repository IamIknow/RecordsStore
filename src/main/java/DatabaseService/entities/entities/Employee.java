package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sergey on 28.04.17.
 */

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @Column(name = "EID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "BirthDate")
    private String birthdate;

    @Column(name = "Salary")
    private String salary;

    @ManyToOne
    @JoinColumn(name = "Shop_SID", foreignKey = @ForeignKey(name = "fk_Employees_Stores1"))
    private Shop shop;


    public Integer getId() {
        return id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Employee setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public Employee setBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getSalary() {
        return salary;
    }

    public Employee setSalary(String salary) {
        this.salary = salary;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public Employee setShop(Shop shop) {
        this.shop = shop;
        return this;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", salary='" + salary + '\'' +
                ", shop=" + shop +
                '}';
    }
}
