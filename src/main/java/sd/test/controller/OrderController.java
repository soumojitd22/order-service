package sd.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.test.model.Order;
import sd.test.service.OrderService;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public Order placeOrder(@RequestParam("customerId") int customerId, @RequestParam("itemIds")List<Integer> itemIds) {
        return orderService.getOrderDetails(customerId, itemIds);
    }
}
