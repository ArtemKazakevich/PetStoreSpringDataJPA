package by.rest.store.service.impl;

import by.rest.store.exception.user.AuthenticationUserException;
import by.rest.store.exception.user.UserNotFoundException;
import by.rest.store.model.User;
import by.rest.store.repository.UserRepository;
import by.rest.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {
     private UserRepository userRepository;
     
     @Autowired
     public UserServiceImpl(UserRepository userRepository) {
          this.userRepository = userRepository;
     }
     public UserServiceImpl() {
     }
     
     @Override
     public void addUser(User user) {
          userRepository.save(user);
     }
     
     @Override
     public User getUser(Long id) {
          Optional<User> byId = userRepository.findById(id);
          if (byId.isPresent()) {
               return byId.get();
          }
          throw new UserNotFoundException();
     }
     
     @Override
     public void updateUser(Long id, User user) {
          if (user.getId() != id) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
          Optional<User> byId = userRepository.findById(id);
          if (byId.isPresent()) {
               userRepository.save(user);
          } else throw new UserNotFoundException();
     }
     
     @Override
     public boolean isLoggedIn(String token) {
          Optional<User> byToken = userRepository.findUserByToken(token);
          return byToken.isPresent();
     }
     
     @Override
     public void deleteUser(Long id) {
          Optional<User> byId = userRepository.findById(id);
          if (byId.isPresent()) {
               userRepository.deleteById(id);
          } else throw new UserNotFoundException();
     }
     
     @Override
     public void logout(String token) {
          Optional<User> byToken = userRepository.findUserByToken(token);
          if (byToken.isPresent()) {
               User user = byToken.get();
               user.setToken(null);
               userRepository.save(user);
          }
     }
     
     @Override
     public String authentication(User newUser) {
          Optional<User> byLogin = userRepository.findUserByLogin(newUser.getLogin());
          if (byLogin.isPresent()) {
               if (byLogin.get().getToken() != null) throw new AuthenticationUserException("User has already logged in");
               if (byLogin.get().getPassword().equals(newUser.getPassword())) {
                    String token = Integer.toString(new Random().nextInt());
                    User user = byLogin.get();
                    user.setToken(token);
                    userRepository.save(user);
                    return token;
               }
          }
          return null;
     }
}
