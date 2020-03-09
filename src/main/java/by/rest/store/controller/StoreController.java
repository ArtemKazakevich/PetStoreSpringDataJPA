package by.rest.store.controller;

import by.rest.store.exception.store.StoreBadRequestException;
import by.rest.store.exception.store.StoreNotFoundException;
import by.rest.store.model.Order;
import by.rest.store.model.request.store.StoreRequest;
import by.rest.store.service.impl.StoreServiceImpl;
import by.rest.store.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = "/store")
@Validated
@Slf4j
public class StoreController {
     private StoreServiceImpl storeService;
     private UserServiceImpl userService;
     
     public StoreController(StoreServiceImpl storeService, UserServiceImpl userService) {
          this.storeService = storeService;
          this.userService = userService;
     }
     
     @GetMapping(path = "/order/{orderID}")
     public ResponseEntity<Order> getOrder(@PathVariable("orderID") @NotBlank Long orderID,
                                           @RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          if (!storeService.getOrdersMap().containsKey(orderID)) throw new StoreNotFoundException();
          log.info("The list is made");
          return new ResponseEntity<>(storeService.getOrdersMap().get(orderID), HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/order/{orderID}")
     public ResponseEntity<String> deleteOrder(@PathVariable("orderID") @NotBlank Long orderID,
                                               @RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          if (!storeService.getOrdersMap().containsKey(orderID)) throw new StoreNotFoundException();
          storeService.deleteOrder(orderID);
          log.info("The order was successfully deleted");
          return new ResponseEntity<>("The order was successfully deleted", HttpStatus.OK);
     }
     
     @PostMapping(path = "/order")
     public ResponseEntity<String> addOrder(@Valid @RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          storeService.addOrder(storeRequest.getOrder());
          log.info("The order was added successfully");
          return new ResponseEntity<>("The order was added successfully", HttpStatus.OK);
     }
     
     @GetMapping("/inventory")
     public ResponseEntity<List<Order>> inventory(@RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          log.info("The output of the equipment is made");
          return new ResponseEntity<>(storeService.inventoryStore(storeRequest.getStatus()), HttpStatus.OK);
     }
}
