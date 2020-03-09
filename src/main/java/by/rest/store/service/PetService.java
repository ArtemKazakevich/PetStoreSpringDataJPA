package by.rest.store.service;

import by.rest.store.model.Pet;

import java.util.List;
import java.util.Map;

public interface PetService {
     Map<Long, Pet> getPetsList();
     
     void petUpdateById(Long id, String name, Pet.Status status);
     
     void addPet(Pet pet);
     
     boolean updatePet(Pet pet);
     
     List<Pet> findByStatus(Pet.Status status);
}
