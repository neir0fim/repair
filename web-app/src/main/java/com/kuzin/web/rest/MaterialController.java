package com.kuzin.web.rest;


import com.kuzin.entity.Material;
import com.kuzin.service.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

/**material controlle class.*/

@RestController
@RequestMapping("/material")
public class MaterialController {

    MaterialService service;

    @Autowired
    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @GetMapping("/{cod}")
    public EntityModel<Material> doGet(long cod) {
        return null;
    }



}
