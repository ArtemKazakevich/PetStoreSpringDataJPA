package by.rest.store.service.impl;

import by.rest.store.model.User;
import by.rest.store.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {
     public final Map<String, User> usersList;
     public final Map<Long, Long> tokens;
     
     public UserServiceImpl(Map<String, User> usersList, Map<Long, Long> tokens) {
          this.usersList = usersList;
          this.tokens = tokens;
     }
     
     @Override
     public Map<String, User> getUsersMap() {
          return usersList;
     }
     
     
     @Override
     public Map<Long, Long> getTokens() {
          return tokens;
     }
     
     
     @Override
     public void addUser(User user) {
          usersList.put(user.getUserName(), user);
     }
     
     
     @Override
     public Long authentication(User newUser) {
          String login = newUser.getUserName();
          String password = newUser.getPassword();
          if (usersList.isEmpty()) {
               return null;
          }
          if (usersList.containsKey(login)) {
               if (usersList.get(login).getPassword().equals(password)) {
                    Long token = new Random().nextLong();
                    tokens.put(usersList.get(login).getId(), token);
                    return token;
               }
          }
          return null;
     }
     
     @Override
     public void addAllUsers(List<User> userList) {
          for (User user: userList) {
               usersList.put(user.getUserName(), user);
          }
     }
}
