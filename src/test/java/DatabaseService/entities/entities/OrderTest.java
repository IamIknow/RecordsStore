package DatabaseService.entities.entities;

import DatabaseService.entities.DatabaseService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sergey on 28.04.17.
 */
public class OrderTest {

    DatabaseService service = new DatabaseService();

    @Test
    public void OrderDaoTest() {

        List<Order> list = service.findAll(Order.class);

        for(Order order : list) {
            System.out.println(order);
        }

        Customer customer = new Customer();
        customer.setName("Sylvia Platt").setId(9);

        Order order = new Order();
        order.setCustomer(customer).setOrderDate(new java.sql.Date(234));

        service.persist(order);

        service.delete(order);

    }
}