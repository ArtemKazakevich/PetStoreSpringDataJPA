package by.rest.store.service;

import by.rest.store.model.User;

import java.util.List;

public interface UserService { ;
     void addUser(User user);
     
     User getUser(Long id);
     
     void updateUser(Long id, User user);
     
     boolean isLoggedIn(String token);
     
     void deleteUser(Long id);
     
     void logout(String token);
     
     String authentication(User newUser);
}
