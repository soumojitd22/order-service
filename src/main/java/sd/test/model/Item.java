package sd.test.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private int id;
    private String name;
    private String description;
    private double price;
}
