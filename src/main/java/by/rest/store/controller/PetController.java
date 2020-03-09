package by.rest.store.controller;

import by.rest.store.exception.pet.PetBadRequestException;
import by.rest.store.exception.pet.PetNotFoundException;
import by.rest.store.model.Pet;
import by.rest.store.model.request.pet.PetRequest;
import by.rest.store.service.impl.PetServiceImpl;
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
@RequestMapping(path = ("/pet"))
@Validated
@Slf4j
public class PetController {
     private PetServiceImpl petService;
     private UserServiceImpl userService;
     
     public PetController(PetServiceImpl petService, UserServiceImpl userService) {
          this.petService = petService;
          this.userService = userService;
     }
     
     @GetMapping(path = "/{petID}")
     public ResponseEntity<Pet> getPet(@PathVariable("petID") @NotBlank Long petID,
                                                  @RequestBody PetRequest petRequest) {
          if (!userService.getTokens().containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petService.getPetsList().containsKey(petID)) throw new PetNotFoundException();
          log.info("The list is made");
          return new ResponseEntity<>(petService.getPetsList().get(petID), HttpStatus.OK);
     }
     
     @PostMapping(path = "/{petID}")
     public ResponseEntity<String> updatedPet(@PathVariable("petID") @NotBlank Long petID,
                                              @RequestBody PetRequest petRequest) {
          if (!userService.getTokens().containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petService.getPetsList().containsKey(petID)) throw new PetNotFoundException();
          petService.petUpdateById(petID, petRequest.getName(), petRequest.getStatus());
          log.info("The update was successful");
          return new ResponseEntity<>("The update was successful", HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/{petID}")
     public ResponseEntity<String> deletePet(@PathVariable("petID") @NotBlank Long petID,
                                             @RequestBody PetRequest petRequest) {
          if (!userService.getTokens().containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petService.getPetsList().containsKey(petID)) throw new PetNotFoundException();
          petService.getPetsList().remove(petID);
          log.info("The deletion was successful");
          return new ResponseEntity<>("The deletion was successful", HttpStatus.OK);
     }
     
     @PostMapping(path = "/{petId}/uploadImage")
     public ResponseEntity<String> uploadImagePet(@PathVariable("petID") @NotBlank Long petID,
                                                  @RequestBody PetRequest petRequest) {
          if (!userService.getTokens().containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petService.getPetsList().containsKey(petID)) throw new PetNotFoundException();
          petService.getPetsList().get(petID).getPhotoUrls().add(petRequest.getPetPhotoUrl());
          log.info("The image is successfully loaded");
          return new ResponseEntity<>("The image is successfully loaded", HttpStatus.OK);
     }
     
     @PostMapping
     public ResponseEntity<String> addPet(@Valid @RequestBody PetRequest petRequest) {
          if (!userService.getTokens().containsValue(petRequest.getToken())) throw new PetBadRequestException();
          petService.addPet(petRequest.getPet());
          log.info("Pet added successfully");
          return new ResponseEntity<>("Pet added successfully", HttpStatus.OK);
     }
     
     @PutMapping
     public ResponseEntity<String> updatePet(@Valid @RequestBody PetRequest petRequest) {
          if (!userService.getTokens().containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petService.updatePet(petRequest.getPet())) throw new PetNotFoundException();
          log.info("The update was successful");
          return new ResponseEntity<>("The update was successful", HttpStatus.OK);
     }
     
     @GetMapping(path = "/findByStatus")
     public ResponseEntity<List<Pet>> findByStatus(@RequestBody PetRequest petRequest) {
          if (!userService.getTokens().containsValue(petRequest.getToken())) throw new PetBadRequestException();
          log.info("The output of the equipment is made");
          return new ResponseEntity<>(petService.findByStatus(petRequest.getStatus()), HttpStatus.OK);
     }
     
}
