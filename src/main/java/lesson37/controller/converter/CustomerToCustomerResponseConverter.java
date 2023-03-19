package lesson37.controller.converter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lesson37.dto.CustomerResponse;
import lesson37.dto.OrderResponse;
import lesson37.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerResponseConverter implements Converter<Customer, CustomerResponse> {
    private final OrderToOrderResponseConverter orderToOrderResponseConverter;

    public CustomerToCustomerResponseConverter(OrderToOrderResponseConverter orderToOrderResponseConverter) {
        this.orderToOrderResponseConverter = orderToOrderResponseConverter;
    }

    @Override
    public CustomerResponse convert(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse(customer.getCustomerNumber(),
                customer.getCustomerName(), customer.getContactLastName(), customer.getContactFirstName(),
                customer.getPhone(), customer.getAddressLine1(), customer.getAddressLine2(), customer.getCity(),
                customer.getState(), customer.getPostalCode(), customer.getCountry(), customer.getCreditLimit());
        
        Set<OrderResponse> orders = Optional.ofNullable(customer.getOrders())
                .orElse(new HashSet<>())
                .stream()
                .map(orderToOrderResponseConverter::convert)
                .collect(Collectors.toSet());
        customerResponse.setOrders(orders);
        return customerResponse;
    }

}
