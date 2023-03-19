package lesson37.controller.converter;

import lesson37.dto.CustomerRequest;
import lesson37.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestToCustomerConverter implements Converter<CustomerRequest, Customer> {

    @Override
    public Customer convert(CustomerRequest customerRequest) {
        return new Customer(customerRequest.getCustomerNumber(), customerRequest.getCustomerName(),
                customerRequest.getContactLastName(), customerRequest.getContactFirstName(), customerRequest.getPhone(),
                customerRequest.getAddressLine1(), customerRequest.getAddressLine2(), customerRequest.getCity(),
                customerRequest.getState(), customerRequest.getPostalCode(), customerRequest.getCountry(),
                customerRequest.getCreditLimit());
    }

}
