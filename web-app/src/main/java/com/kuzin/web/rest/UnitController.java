package com.kuzin.web.rest;

import com.kuzin.entity.Unit;
import com.kuzin.service.service.UnitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**units controller class.*/

@RestController
@RequestMapping("/units/api")
public class UnitController {

    UnitService service;

    @Autowired
    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Unit getById(@PathVariable("id") long id) {
        return service.get(id);
    }

    @GetMapping()

    public List<Unit> get() {
        return service.getAll();
    }


    @PostMapping
    public ResponseEntity<Long> saveUnit(@RequestBody Unit unit) {
        long id = service.save(unit);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") long id) {
        int result = service.delete(id);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user input");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("unit with id: " + id + " was deleted");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Unit unit, @PathVariable ("id") long id) {
        service.update(unit, id);

        return ResponseEntity.ok("unit with id: " + id + " was updated");
    }


}
