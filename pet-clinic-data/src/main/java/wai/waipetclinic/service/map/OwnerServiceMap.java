package wai.waipetclinic.service.map;

import org.springframework.stereotype.Service;
import wai.waipetclinic.model.Owner;
import wai.waipetclinic.service.OwnerService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

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
        return super.save(owner);
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
