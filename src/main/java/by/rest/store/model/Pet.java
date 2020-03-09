package by.rest.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @NotNull
     @PositiveOrZero
     private long id;
     
     @OneToOne
     private Category category;
     private String name;
     
     @ElementCollection
     private List<String> photoUrls;
     
     @OneToMany
     private List<Tag> tags;
     
     @Enumerated
     private Status status;
     
     public enum Status {
          available,
          pending,
          sold
     }
     
}
