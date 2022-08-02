package sd.test.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sd.test.model.Item;

import java.util.Map;
import java.util.Optional;

@Service
public class ItemInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultItemInfo")
    public Item getItemInfo(Integer id) {
        return Optional.ofNullable(restTemplate.getForObject("http://item-service/item?id={id}", Item.class, Map.of("id", id)))
                .orElse(new Item());
    }

    private Item getDefaultItemInfo(Integer id) {
        return new Item();
    }
}
