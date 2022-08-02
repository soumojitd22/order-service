package sd.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.test.model.Customer;
import sd.test.model.Item;
import sd.test.model.Order;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private ItemInfoService itemInfoService;

    public Order getOrderDetails(int customerId, List<Integer> itemIds) {
        Customer customerInfo = customerInfoService.getCustomerInfo(customerId);
        List<Item> orderedItems = itemIds.stream()
                .map(itemInfoService::getItemInfo)
                .toList();

        double totalOrderPrice = orderedItems.stream()
                .mapToDouble(Item::getPrice)
                .sum();

        return new Order(Instant.now().getNano(), customerInfo, orderedItems, totalOrderPrice);
    }
}
