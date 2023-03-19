package lesson37.controller.converter;

import lesson37.dto.OrderResponse;
import lesson37.entity.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderResponseConverter implements Converter<Order, OrderResponse> {

    @Override
    public OrderResponse convert(Order order) {
        return new OrderResponse(order.getOrderNumber(), order.getOrderDate());
    }

}
