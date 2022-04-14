package com.kuzin.repair.controllers;


import com.kuzin.repair.entity.Material;
import com.kuzin.repair.entity.Repair;
import com.kuzin.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    RepairService service;

    @Autowired
    public MaterialController(RepairService service) {
        this.service = service;
    }

    @PostMapping()
    public Repair doPost(@RequestBody List<Material> material, @RequestBody Long amount,
                         @RequestBody Long id) {
        return service.update(material, amount, id);
    }

}
