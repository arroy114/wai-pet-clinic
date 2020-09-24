package wai.waipetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners") //pre-fixing mapping for the whole class
@Controller
public class OwnerController {

    //due to pre-fixing the mapping here is actually "/owners", "/owners/index", "/owners/index.html"
    @RequestMapping({"", "/index", "/index.html"})
    public String listOwner(){

        return "owners/index";
    }
}
