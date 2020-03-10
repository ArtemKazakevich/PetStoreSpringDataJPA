package by.rest.store.model.request.user;

import by.rest.store.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
     private String token;
     private User user;
}
