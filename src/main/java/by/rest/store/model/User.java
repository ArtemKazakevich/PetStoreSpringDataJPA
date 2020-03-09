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
public class User {
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @NotNull
     @PositiveOrZero
     private long id;
     private String userName;
     private String firstName;
     private String lastName;
     private String email;
     private String password;
     private String phone;
     private int userStatus;
     
     @OneToMany
     private List<Pet> pets;
}
