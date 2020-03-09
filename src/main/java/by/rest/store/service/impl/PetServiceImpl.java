package by.rest.store.service.impl;

import by.rest.store.model.Pet;
import by.rest.store.repository.PetRepository;
import by.rest.store.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PetServiceImpl implements PetService {
     
     private final PetRepository petRepository;
     private final Map<Long, Pet> petsList;
     
     public PetServiceImpl(Map<Long, Pet> petsList, PetRepository petRepository) {
          this.petsList = petsList;
          this.petRepository = petRepository;
     }
     
     @Override
     public Map<Long, Pet> getPetsList() {
          return petsList;
     }
     
     @Override
     public void petUpdateById(Long id, String name, Pet.Status status) {
          petsList.get(id).setName(name);
          petsList.get(id).setStatus(status);
     }
     
     @Override
     public void addPet(Pet pet) {
          petsList.put(pet.getId(), pet);
     }
     
     @Override
     public boolean updatePet(Pet pet) {
          if (!petsList.containsKey(pet.getId())) return false;
          petsList.put(pet.getId(), pet);
          return true;
     }
     
     @Override
     public List<Pet> findByStatus(Pet.Status status) {
          List<Pet> pets = new ArrayList<>();
          for (Pet pet: petsList.values()) {
               if (pet.getStatus().equals(status)) {
                    pets.add(pet);
               }
          }
          return pets;
     }
}
