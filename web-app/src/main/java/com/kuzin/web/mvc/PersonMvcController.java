package com.kuzin.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**controller class.*/
@Controller
@RequestMapping("/person")
public class PersonMvcController {

    @GetMapping()
    public String getPersons() {
        return "person/persons";
    }

    @GetMapping("/get/{username}")
    public String getPerson(@PathVariable ("username") String username) {
        return "person/person";
    }


}
