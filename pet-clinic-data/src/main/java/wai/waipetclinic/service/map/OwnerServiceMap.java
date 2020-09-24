package wai.waipetclinic.service.map;

import wai.waipetclinic.model.Owner;
import wai.waipetclinic.service.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

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
        return super.save(owner.getId(), owner);
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
}
