package com.kuzin.web.rest;

import com.kuzin.entity.Factory;
import com.kuzin.service.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController()
@RequestMapping("/factory")
public class FactoryController {

    FactoryService service;

    @Autowired
    public FactoryController(FactoryService service) {
        this.service = service;
    }

    @GetMapping()
    public EntityModel<Factory> get() {
        Factory factory = service.getFactory();

        return EntityModel.of(factory,
                linkTo(methodOn(UnitController.class).get()).withSelfRel());
    }

}
