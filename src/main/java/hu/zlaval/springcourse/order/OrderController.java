package hu.zlaval.springcourse.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService service;

    @GetMapping
    public List<OrderEntity> getOrders() {
        return service.get();
    }
}
