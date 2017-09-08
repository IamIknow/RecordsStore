package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sergey on 01.05.17.
 */

@Entity()
@Table(name = "MANAGER")
public class Manager implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @OneToOne
    @JoinColumn(name = "Employee_EID", foreignKey = @ForeignKey(name = "fk_Managers_Employees1"))
    Employee employee;

    @OneToOne
    @JoinColumn(name = "SHOP_SID", foreignKey = @ForeignKey(name = "fk_MANAGER_SHOP1"))
    Shop shop;


    public Employee getEmployee() {
        return employee;
    }

    public Manager setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public Manager setShop(Shop shop) {
        this.shop = shop;
        return this;
    }
}
