package by.rest.store.controller;

import by.rest.store.exception.user.UserBadRequestException;
import by.rest.store.exception.user.UserNotFoundException;
import by.rest.store.model.User;
import by.rest.store.model.request.user.UserRequest;
import by.rest.store.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
@Validated
@Slf4j
public class UserController {
     private UserServiceImpl userService;
     
     public UserController(UserServiceImpl userService) {
          this.userService = userService;
     }
     
     @GetMapping(path = "/{userName}")
     public ResponseEntity<User> getUser(@PathVariable("userName") @NotBlank String userName,
                                         @RequestBody UserRequest userRequest) {
          if (!userService.getTokens().containsValue(userRequest.getToken())) throw new UserBadRequestException();
          Map<String, User> usersMap = userService.getUsersMap();
          if (!usersMap.containsKey(userName)) throw new UserNotFoundException();
          log.info("The list is made");
          return new ResponseEntity<>(usersMap.get(userName), HttpStatus.OK);
     }
     
     @PutMapping(path = "/{userName}")
     public ResponseEntity<String> updateUser(@PathVariable("userName") @NotBlank String userName,
                                              @RequestBody UserRequest userRequest) {
          if (!userService.getTokens().containsValue(userRequest.getToken())) throw new UserBadRequestException();
          Map<String, User> usersMap = userService.getUsersMap();
          if (!usersMap.containsKey(userName)) throw new UserNotFoundException();
          usersMap.put(userName, userRequest.getUser());
          log.info("The user is successfully updated");
          return new ResponseEntity<>("The user is successfully updated", HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/{userName}")
     public ResponseEntity<String> deleteUser(@PathVariable("userName") @NotBlank String userName,
                                              @RequestBody UserRequest userRequest) {
          if (!userService.getTokens().containsValue(userRequest.getToken())) throw new UserBadRequestException();
          Map<String, User> usersMap = userService.getUsersMap();
          if (!usersMap.containsKey(userName)) throw new UserNotFoundException();
          usersMap.remove(userName);
          log.info("The user was successfully deleted");
          return new ResponseEntity<>("The user was successfully deleted", HttpStatus.OK);
     }
     
     @PostMapping(path = "/login")
     public ResponseEntity<Long> loginUser(@RequestBody UserRequest userRequest) {
          Long token = userService.authentication(userRequest.getUser());
          if (token == null) throw new UserBadRequestException();
          log.info("Login completed");
          return new ResponseEntity<>(token, HttpStatus.OK);
     }
     
     @PostMapping(path = "/logout")
     public ResponseEntity<String> logoutUser(@RequestBody UserRequest userRequest) {
          if (!userService.getTokens().containsValue(userRequest.getToken())) throw new UserBadRequestException();
          userService.getTokens().values().remove(userRequest.getToken());
          log.info("Exit successfully completed");
          return new ResponseEntity<>("Exit successfully completed", HttpStatus.OK);
     }
     
     @PostMapping
     public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
          userService.addUser(user);
          log.info("The user is created");
          return new ResponseEntity<>("The user is created", HttpStatus.OK);
     }
     
     @PostMapping(path = "/createWithList")
     public ResponseEntity<String> createWithListUser(@Valid @RequestBody List<User> users) {
          userService.addAllUsers(users);
          log.info("User output completed");
          return new ResponseEntity<>("User output completed", HttpStatus.OK);
     }
}
