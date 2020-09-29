package wai.waipetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wai.waipetclinic.service.OwnerService;

@RequestMapping("/owners") //pre-fixing mapping for the whole class
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //due to pre-fixing the mapping here is actually "/owners", "/owners/index", "/owners/index.html"
    @RequestMapping({"", "/index", "/index.html"})
    public String listOwner(Model model){

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(){
        return "notImplemented";
    }
}
