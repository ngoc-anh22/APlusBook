package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends BaseService<OrderItem> {
    @Override
    protected Class<OrderItem> clazz() {
        return OrderItem.class;
    }
}
