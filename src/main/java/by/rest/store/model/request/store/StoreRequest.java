package by.rest.store.model.request.store;

import by.rest.store.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
     private Long token;
     private Order order;
     private Order.Status status;
}
