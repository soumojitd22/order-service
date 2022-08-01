package sd.test.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private int OrderId;
    private Customer customerInfo;
    private List<Item> orderedItems;
    private double totalOrderValue;
}
