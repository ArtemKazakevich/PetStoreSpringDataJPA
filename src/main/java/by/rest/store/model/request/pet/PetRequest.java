package by.rest.store.model.request.pet;

import by.rest.store.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
     private Long token;
     private String name;
     private Pet.Status status;
     private String petPhotoUrl;
     private Pet pet;
}
