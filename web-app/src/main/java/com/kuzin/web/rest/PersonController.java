package com.kuzin.web.rest;

import com.kuzin.entity.Person;
import com.kuzin.service.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



/**person controller class.*/

@RestController
@RequestMapping("/persons/api/")
public class PersonController {

    PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDetails getUser(@PathVariable ("username") String username) {
        return service.getUser(username);
    }

    @GetMapping
    public List<UserDetails> getUsers() {
        return service.getUsers();
    }


    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody Person person) {
        Person result = service.addUser(person);

        return new ResponseEntity<>(result.getName(), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable ("username") String username) {
        int result = service.delete(username);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user input");
        } else {
            return ResponseEntity.ok("user " + username + " was deleted");
        }
    }

    @PatchMapping("/block/{username}")
    public void blockUser(@PathVariable ("username") String username) {
        service.block(username);
    }

    @PatchMapping("/unlock/{username}")
    public void unlockUser(@PathVariable ("username") String username) {
        service.unlock(username);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<String> updatePerson(@PathVariable ("username") String username,
                                     @RequestBody Person person) {
        service.updatePerson(person, username);

        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}
