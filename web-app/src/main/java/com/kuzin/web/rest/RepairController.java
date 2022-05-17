package com.kuzin.web.rest;

import com.kuzin.entity.Repair;
import com.kuzin.service.service.RepairService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**repair controller class.*/

@RestController
@RequestMapping("/repair/api")
public class RepairController {

    RepairService service;

    @Autowired
    public RepairController(RepairService service) {
        this.service = service;
    }


    @PostMapping()
    public void doPost(@ModelAttribute Repair repair, HttpServletResponse response)
            throws AccessException, IOException {
        long id = service.save(repair);

        response.sendRedirect("/repair/get/" + id);
    }

    @GetMapping("/article/{id}")
    public List<Repair> getAll(@PathVariable ("id") long id)
            throws AccessException {
        return service.getRepairArticle(id);
    }

    @GetMapping("/{id}")
    public Repair get(@PathVariable("id") long id) throws AccessException {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> doDelete(@PathVariable("id") long id) throws AccessException {
        int result = service.delete(id);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user input");
        } else {
            return ResponseEntity.ok("repair " + id + " was deleted");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> doUpdate(@RequestBody Repair repair, @PathVariable("id") long id)
            throws AccessException {
        int result = service.update(repair, id);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user input");
        } else {
            return ResponseEntity.ok("repair " + id + " was updated");
        }
    }

}
