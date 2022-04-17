package com.kuzin.web.rest;

import com.kuzin.entity.Unit;
import com.kuzin.service.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/units")
public class UnitController {

    UnitService service;

    @Autowired
    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public EntityModel<Unit> getById(@PathVariable("id") long id) {
        return EntityModel.of(service.get(id),
                linkTo(methodOn(UnitController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(ArticleController.class).get()).withRel("/articles"));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Unit>> get() {
        List<EntityModel<Unit>> units = service.getAll().stream().map(
                unit -> EntityModel.of(unit, linkTo(methodOn(UnitController.class)
                        .getById(unit.getId())).withSelfRel())).toList();


        return CollectionModel.of(units,
                linkTo(methodOn(UnitController.class).get()).withSelfRel());
    }



}
