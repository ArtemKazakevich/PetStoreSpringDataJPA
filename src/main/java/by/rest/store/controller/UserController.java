package by.rest.store.controller;

import by.rest.store.exception.user.UserBadRequestException;
import by.rest.store.model.User;
import by.rest.store.model.request.user.UserRequest;
import by.rest.store.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(path = "/user")
@Validated
@Slf4j
public class UserController {
     private UserServiceImpl userService;
     
     public UserController(UserServiceImpl userService) {
          this.userService = userService;
     }
     
     @GetMapping(path = "/{id}")
     public ResponseEntity<User> getUser(@PathVariable("id") @Min(value = 0) Long id,
                                         @RequestBody UserRequest userRequest) {
          if (!userService.isLoggedIn(userRequest.getToken())) throw new UserBadRequestException();
          log.info("The list is made");
          return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
     }
     
     @PutMapping(path = "/{id}")
     public ResponseEntity<String> updateUser(@PathVariable("id") @Min(value = 0) Long id,
                                              @RequestBody UserRequest userRequest) {
          if (!userService.isLoggedIn(userRequest.getToken())) throw new UserBadRequestException();
          userService.updateUser(id, userRequest.getUser());
          log.info("The user is successfully updated");
          return new ResponseEntity<>("The user is successfully updated", HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/{id}")
     public ResponseEntity<String> deleteUser(@PathVariable("id") @Min(value = 0) Long id,
                                              @RequestBody UserRequest userRequest) {
          if (!userService.isLoggedIn(userRequest.getToken())) throw new UserBadRequestException();
          userService.deleteUser(id);
          log.info("The user was successfully deleted");
          return new ResponseEntity<>("The user was successfully deleted", HttpStatus.OK);
     }
     
     @PostMapping(path = "/login")
     public ResponseEntity<String> loginUser(@RequestBody UserRequest userRequest) {
          String token = userService.authentication(userRequest.getUser());
          if (token == null) throw new UserBadRequestException();
          log.info("Login completed");
          return new ResponseEntity<>(token, HttpStatus.OK);
     }
     
     @PostMapping(path = "/logout")
     public ResponseEntity<String> logoutUser(@RequestBody UserRequest userRequest) {
          if (!userService.isLoggedIn(userRequest.getToken())) throw new UserBadRequestException();
          userService.logout(userRequest.getToken());
          log.info("Exit successfully completed");
          return new ResponseEntity<>("Exit successfully completed", HttpStatus.OK);
     }
     
     @PostMapping
     public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
          userService.addUser(user);
          log.info("The user is created");
          return new ResponseEntity<>("The user is created", HttpStatus.OK);
     }
}
