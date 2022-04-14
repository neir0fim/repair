package com.kuzin.repair.controllers;

import com.kuzin.repair.entity.Repair;
import com.kuzin.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;





@RestController
@RequestMapping("/repair")
public class RepairController {

    RepairService service;

    @Autowired
    public RepairController(RepairService service) {
        this.service = service;
    }



    @GetMapping("/{id}")
    public Repair getLecture(@PathVariable("id") long id) {
        return service.get(id);
    }


    @PostMapping()
    public Repair doPost(@RequestBody Repair repair) {
        return service.save(repair);
    }


    @GetMapping()
    public List<Repair> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void doDelete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public Repair updateLecture(@PathVariable("id") long id) {
        //need change
        return null;
    }

}
