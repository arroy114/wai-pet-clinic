package wai.waipetclinic.service.map;

import org.springframework.stereotype.Service;
import wai.waipetclinic.model.Owner;
import wai.waipetclinic.model.Pet;
import wai.waipetclinic.service.OwnerService;
import wai.waipetclinic.service.PetService;
import wai.waipetclinic.service.PetTypeService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        Owner savedOwner = null;

        //Save all petType that owner have and does exist in petType Map service before
        if(owner != null){
            if (owner.getPets() != null){
                owner.getPets().forEach(pet ->{
                    if(pet.getPetType() != null){
                        if(pet.getPetType().getId() == null) { //check if petType exists in Map service
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(owner);

        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner owner) {
        this.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        //this.deleteById(id) => recuses infinitely '.' the method will call itself
        //=> we should call the deleteById() that is inherited by superclass AbstractMapService
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
