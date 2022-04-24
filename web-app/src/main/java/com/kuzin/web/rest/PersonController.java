package com.kuzin.web.rest;


import com.kuzin.entity.Person;
import com.kuzin.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**person controller class.*/

@RestController
@RequestMapping("/persons")
public class PersonController {

    PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public EntityModel<UserDetails> getUser(@PathVariable ("username") String username) {

        return EntityModel.of(service.getUser(username));
    }

    @GetMapping
    public CollectionModel<UserDetails> getUsers() {
        return CollectionModel.of(service.getUsers());
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void addUser(@RequestBody Person person) {
        service.addUser(person);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable ("username") String username) {
        service.delete(username);
    }

    @PatchMapping("/{username}/block")
    public void blockUser(@PathVariable ("username") String username) {
        service.block(username);
    }

    @PatchMapping("/{username}/unlock")
    public void unlockUser(@PathVariable ("username") String username) {
        service.unlock(username);
    }

}
