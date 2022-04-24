package com.kuzin.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.kuzin.entity.Unit;
import com.kuzin.service.service.UnitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

/**units controller class.*/

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


    @PostMapping
    public EntityModel<Unit> saveUnit(@RequestBody Unit unit) {
        return EntityModel.of(service.save(unit));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody Unit unit, @PathVariable ("id") long id) {
        service.update(unit, id);
    }


}
