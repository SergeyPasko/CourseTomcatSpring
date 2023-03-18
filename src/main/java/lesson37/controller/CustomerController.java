package lesson37.controller;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import lesson37.data.Customer;
import lesson37.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spasko
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getOrderById(@PathVariable("id") int id) {
        LOG.info("findCustomerById start, id={}", id);
        Customer customer = customerService.findCustomerById(id);
        LOG.info("findCustomerById end");
        if (customer == null) {
            return ResponseEntity.notFound()
                    .build();
        } else {
            return ResponseEntity.ok(customer);
        }
    }

    //	@Autowired
    //	private OrdersService ordersService;
    //
    //	@Autowired
    //	private OrderCreator orderCreator;
    //
    //	@GetMapping
    //	public @ResponseBody Set<Orders> getOrdersQtyBetween(@RequestParam(value = "min", required = false) Integer minQty,
    //			@RequestParam(value = "max", required = false) Integer maxQty) {
    //		LOG.info("getOrdersQtyBetween start, min={}, max={}", minQty, maxQty);
    //		if (Objects.isNull(maxQty) || Objects.isNull(minQty)) {
    //			LOG.debug("getOrdersQtyBetween use getAllOrders");
    //			return ordersService.getAllOrders();
    //		}
    //		Set<Orders> result = ordersService.findByQtyBetween(minQty, maxQty);
    //		LOG.info("getOrdersQtyBetween end");
    //		return result;
    //	}
    //
    //	@PostMapping
    //	@ApiOperation(authorizations = { @Authorization(value = "basicAuth") }, value = "addOrder")
    //	public void addOrder(@Valid @RequestBody OrderRequest orderRequest) {
    //		LOG.info("addOrder start, orderRequest={}", orderRequest);
    //		Orders order = orderCreator.createOrder(orderRequest);
    //		ordersService.insertOrder(order);
    //		LOG.info("addOrder end");
    //	}
    //
    //	@GetMapping("/{id}")
    //	public @ResponseBody Orders getOrderById(@PathVariable("id") int id) {
    //		LOG.info("getOrderById start, id={}", id);
    //		Orders result = ordersService.findOrderById(BigDecimal.valueOf(id));
    //		LOG.info("getOrderById end");
    //		return result;
    //	}
    //
    //	@DeleteMapping("/{id}")
    //	@ApiOperation(authorizations = { @Authorization(value = "basicAuth") }, value = "deleteOrderById")
    //	public void deleteOrderById(@PathVariable("id") int id) {
    //		LOG.info("deleteOrderById start, id={}", id);
    //		ordersService.deleteOrder(BigDecimal.valueOf(id));
    //		LOG.info("deleteOrderById end");
    //	}
    //
    //	@PutMapping("/{id}")
    //	@ApiOperation(authorizations = { @Authorization(value = "basicAuth") }, value = "updateOrderById")
    //	public void updateOrderById(@PathVariable("id") int id, @RequestParam("qty") Integer qty) {
    //		LOG.info("updateOrderById start, id={}, qty={}", id, qty);
    //		Orders order = ordersService.findOrderById(BigDecimal.valueOf(id));
    //		if (Objects.isNull(order)) {
    //			LOG.warn("updateOrderById cannot update not existing order");
    //			throw new UpdateException("Cannot update Order by Id=" + id + ", because it dont present");
    //		} else {
    //			order.setQty(BigDecimal.valueOf(qty));
    //			ordersService.updateOrder(order);
    //		}
    //		LOG.info("updateOrderById end");
    //	}

}
