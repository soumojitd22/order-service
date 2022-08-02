package sd.test.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sd.test.model.Customer;

import java.util.Map;

@Service
public class CustomerInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultCustomerInfo")
    public Customer getCustomerInfo(int customerId) {
        return restTemplate.getForObject("http://customer-service/customer?id={id}", Customer.class, Map.of("id", customerId));
    }

    private Customer getDefaultCustomerInfo(int customerId) {
        return new Customer();
    }
}
