package by.rest.store.service;

import by.rest.store.model.Order;

import java.util.List;
import java.util.Map;

public interface StoreService {
     Map<Long, Order> getOrdersMap();
     
     void addOrder(Order order);
     
     void deleteOrder(Long id);
     
     List<Order> inventoryStore(Order.Status status);
}
