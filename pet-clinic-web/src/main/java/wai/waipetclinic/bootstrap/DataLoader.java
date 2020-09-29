package wai.waipetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import wai.waipetclinic.model.*;
import wai.waipetclinic.service.OwnerService;
import wai.waipetclinic.service.PetTypeService;
import wai.waipetclinic.service.SpecialtyService;
import wai.waipetclinic.service.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }
    }

    //Separate from run method so that we can reuse this class when we bring in Spring Data JPA
    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCattType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");

        Specialty surgery = new Specialty();
        radiology.setDescription("surgery");

        Specialty dentistry = new Specialty();
        radiology.setDescription("dentistry");

        Specialty savedRadiology = specialtyService.save(radiology);
        Specialty savedSurgery = specialtyService.save(surgery);
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 street");
        owner1.setCity("Hong Kong");
        owner1.setTelephone("11223344");

        Pet mikesPet = new Pet();
        mikesPet.setPetType((savedDogPetType));
        mikesPet.setOwner(owner1);
        mikesPet.setBirthday(LocalDate.now());
        mikesPet.setName("Bingo");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("555 street");
        owner2.setCity("New York");
        owner2.setTelephone("22223333");

        Pet fionaCat = new Pet();
        fionaCat.setName("Just cat");
        fionaCat.setOwner(owner2);
        fionaCat.setBirthday(LocalDate.now());
        fionaCat.setPetType(savedCattType);
        owner2.getPets().add(fionaCat);


        ownerService.save(owner2);

        System.out.println("Loaded Owners ....");


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
