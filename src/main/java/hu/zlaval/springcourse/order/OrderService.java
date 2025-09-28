package hu.zlaval.springcourse.order;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final PhoneRepository phoneRepository;
    private final ProductRepository productRepository;


    public List<OrderEntity> get() {
        var orders = orderRepository.findAll();
        //Explain PersistenceBag
        //Add Eager load, discuss EntityGraph later
        //Explain Eager/Lazy problem
        //Explain Json Serialization problem (bidirectional = circular) and JsonManagedReference
        return orders;
    }

    @PostConstruct
    public void init() {
        var phone = new PhoneEntity();
        phone.setPhoneNumber("066055666");
        //phone = phoneRepository.save(phone);

        var customer = new CustomerEntity();
        customer.setName("Gipsz Jakab");
        customer.setReseller(true);
        customer.setPhone(phone);// cascade save phone
        customer = customerRepository.save(customer);

        var p1 = new ProductEntity();
        p1.setName("Alienware monitor");
        p1.setProductType(ProductType.Electronics);
        p1.setPrice(1770.5);
        // p1 = productRepository.save(p1);

        var p2 = new ProductEntity();
        p2.setName("Toyota corolla");
        p2.setProductType(ProductType.Car);
        p2.setPrice(35000.0);
        // p2 = productRepository.save(p2);

        var order = new OrderEntity();
        order.setDate(Instant.now());
        order.setCustomer(customer);
        order.setProducts(List.of(p1, p2));
        orderRepository.save(order);
    }

}
