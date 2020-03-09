package by.rest.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @NotNull
     @PositiveOrZero
     private long id;
     
     @NotNull
     @PositiveOrZero
     private long petId;
     private int quantity;
     private String shipDate;
     private boolean complete;
     
     @Enumerated
     private Status status;
     
     public enum Status {
          placed,
          approved,
          delivered
     }
}
