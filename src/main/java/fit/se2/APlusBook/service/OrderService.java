package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseService<Order>{
    @Override
    protected Class<Order> clazz() {
        return Order.class;
    }
}
