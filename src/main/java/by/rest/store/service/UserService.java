package by.rest.store.service;

import by.rest.store.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
     Map<String, User> getUsersMap();
     
     Map<Long, Long> getTokens();
     
     void addUser(User user);
     
     Long authentication(User newUser);
     
     void addAllUsers(List<User> userList);
}
