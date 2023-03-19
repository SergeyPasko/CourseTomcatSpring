package lesson37.controller;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lesson37.dto.CustomerRequest;
import lesson37.dto.CustomerResponse;
import lesson37.entity.Customer;
import lesson37.exception.UpdateException;
import lesson37.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spasko
 */

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final ConversionService conversionService;

    public CustomerController(CustomerService customerService, ConversionService conversionService) {
        this.customerService = customerService;
        this.conversionService = conversionService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") int id) {
        LOG.info("findCustomerById start, id={}", id);
        Customer customer = customerService.findCustomerById(id);
        CustomerResponse customerResponse = conversionService.convert(customer, CustomerResponse.class);
        LOG.info("findCustomerById end");
        if (customer == null) {
            return ResponseEntity.notFound()
                    .build();
        } else {
            return ResponseEntity.ok(customerResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerRequest customerRequest) {
        LOG.info("addCustomer start, customerRequest={}", customerRequest);
        Customer customer = conversionService.convert(customerRequest, Customer.class);
        boolean inserted = customerService.insertCustomer(customer);
        LOG.info("addCustomer end");
        if (inserted) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") int id) {
        LOG.info("deleteCustomerById start, id={}", id);
        boolean deleted = customerService.deleteCustomer(id);
        LOG.info("deleteCustomerById end");
        if (deleted) {
            return ResponseEntity.ok()
                    .build();
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    @PutMapping("/{id}")
    public void updateCustomerById(@PathVariable("id") int id,
            @RequestParam("contactFirstName") String contactFirstName) {
        LOG.info("updateCustomerById start, id={}, contactFirstName={}", id, contactFirstName);
        Customer customer = customerService.findCustomerById(id);
        if (Objects.isNull(customer)) {
            LOG.warn("updateCustomerById cannot update not existing customer");
            throw new UpdateException("Cannot update Customer by Id=" + id + ", because it dont present");
        } else {
            customer.setContactFirstName(contactFirstName);
            customerService.updateCustomer(customer);
        }
        LOG.info("updateCustomerById end");
    }

    @GetMapping
    public Set<CustomerResponse> getAllCustomers() {
        LOG.info("getAllCustomers start");
        Set<CustomerResponse> customerResponses = customerService.getAllCustomers()
                .stream()
                .map(customer -> conversionService.convert(customer, CustomerResponse.class))
                .collect(Collectors.toSet());

        LOG.info("getAllCustomers end");
        return customerResponses;
    }

}
