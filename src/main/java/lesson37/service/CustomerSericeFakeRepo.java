package lesson37.service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lesson37.data.Customer;
import lesson37.data.Order;
import org.springframework.stereotype.Service;

@Service
public class CustomerSericeFakeRepo implements CustomerService {

    private Set<Customer> customers;

    @Override
    public Set<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Customer findCustomerById(int id) {
        return customers.stream()
                .filter(c -> c.getCustomerNumber() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean insertCustomer(Customer customer) {
        return customers.add(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        customers.removeIf(c -> c.getCustomerNumber() == customer.getCustomerNumber());
        customers.add(customer);
        return true;
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customers.removeIf(c -> c.getCustomerNumber() == id);
    }

    @PostConstruct
    void initRepo() {
        customers = new HashSet<>();
        Customer customer = new Customer(777, "Pasko Inc", "Pasko", "Serhii", "123456", "Address line1",
                "Address line2", "Kyiv", null, null, "Ukraine", BigDecimal.valueOf(333));
        Set<Order> orders = new HashSet<>();
        Order order = new Order(111, LocalDateTime.now());
        orders.add(order);
        customer.setOrders(orders);
        customers.add(customer);
    }

}
