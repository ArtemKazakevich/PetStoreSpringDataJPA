package by.rest.store.service.impl;

import by.rest.store.model.Order;
import by.rest.store.service.StoreService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
     
     private final Map<Long, Order> orders;
     
     public StoreServiceImpl(Map<Long, Order> orders) {
          this.orders = orders;
     }
     
     @Override
     public Map<Long, Order> getOrdersMap() {
          return orders;
     }
     
     
     @Override
     public void addOrder(Order order) {
          orders.put(order.getId(), order);
     }
     
     
     @Override
     public void deleteOrder(Long id) {
          orders.remove(id);
     }
     
     
     @Override
     public List<Order> inventoryStore(Order.Status status) {
          List<Order> foundStore = new ArrayList<>();
          for (Order pet: orders.values()) {
               if (pet.getStatus().equals(status)) {
                    foundStore.add(pet);
               }
          }
          return foundStore;
     }
}
