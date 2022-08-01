package sd.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sd.test.model.Customer;
import sd.test.model.Item;
import sd.test.model.Order;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    public Order getOrderDetails(int customerId, List<Integer> itemIds) {
        Customer customerInfo = getCustomerInfo(customerId);
        List<Item> orderedItems = itemIds.stream()
                .map(this::getItemInfo)
                .toList();

        double totalOrderPrice = orderedItems.stream()
                .mapToDouble(Item::getPrice)
                .sum();

        return new Order(Instant.now().getNano(), customerInfo, orderedItems, totalOrderPrice);
    }

    private Customer getCustomerInfo(int customerId) {
        return restTemplate.getForObject("http://customer-service/customer?id={id}", Customer.class, Map.of("id", customerId));
    }

    private Item getItemInfo(Integer id) {
        return Optional.ofNullable(restTemplate.getForObject("http://item-service/item?id={id}", Item.class, Map.of("id", id)))
                .orElse(new Item());
    }
}
