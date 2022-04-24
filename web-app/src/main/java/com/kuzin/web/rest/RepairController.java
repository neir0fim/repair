package com.kuzin.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.kuzin.entity.Repair;
import com.kuzin.service.service.RepairService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

/**repair controller class.*/

@RestController
@RequestMapping("/repair")
public class RepairController {

    RepairService service;

    @Autowired
    public RepairController(RepairService service) {
        this.service = service;
    }



    @GetMapping("/{id}")
    public EntityModel<Repair> get(@PathVariable("id") long id) {
        Repair result = service.get(id);

        return EntityModel.of(result,
                linkTo(methodOn(RepairController.class).getAll()).withSelfRel());
    }


    @PostMapping()
    public EntityModel<Repair> doPost(@RequestBody Repair repair) {
        Repair result = service.save(repair);

        return EntityModel.of(result,
                linkTo(methodOn(RepairController.class).get(result.getId())).withSelfRel());
    }


    @GetMapping()
    public CollectionModel<EntityModel<Repair>> getAll() {
        List<EntityModel<Repair>> result = service.getAll().stream().map(
                repair -> EntityModel.of(repair, linkTo(methodOn(RepairController.class)
                        .get(repair.getId())).withSelfRel())).toList();


        return CollectionModel.of(result, linkTo(methodOn(RepairController.class)
                .getAll()).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public void doDelete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public void doUpdate(@RequestBody Repair repair, @PathVariable("id") long id) {
        service.update(repair, id);
    }

}
