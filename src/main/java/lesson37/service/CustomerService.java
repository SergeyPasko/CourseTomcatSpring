package lesson37.service;

import java.util.Set;
import lesson37.entity.Customer;

/**
 * @author spasko
 */
public interface CustomerService {

    Set<Customer> getAllCustomers();

    Customer findCustomerById(int id);

    boolean insertCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(int id);
}
