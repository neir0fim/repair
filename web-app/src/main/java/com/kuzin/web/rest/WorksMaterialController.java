package com.kuzin.web.rest;


import com.kuzin.entity.WorksMaterial;
import com.kuzin.entity.enums.MaterialPost;
import com.kuzin.service.service.WorksMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**controller class.*/

@RestController
@RequestMapping("/works/material/api")
public class WorksMaterialController {


    WorksMaterialService materialService;

    private static final String INPUT = "wrong user input";

    @Autowired
    public WorksMaterialController(WorksMaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMaterial(@RequestBody MaterialPost worksMaterial) {
        WorksMaterial result = materialService.save(worksMaterial);

        return ResponseEntity.ok("material " + result.getName() + " was added");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterialFromRepair(@PathVariable ("id") long id) {
        int result = materialService.deleteMaterial(id);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INPUT);
        } else {
            return ResponseEntity.ok("material " + id + " was deleted");
        }
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<String> deleteAllMaterial(@PathVariable ("id") long id) {
        int result = materialService.deleteMaterials(id);

        if (result == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INPUT);
        } else {
            return ResponseEntity.ok("materials was deleted");
        }
    }

    @PatchMapping
    public ResponseEntity<String> updateMaterial(@RequestBody WorksMaterial worksMaterial) {
        int result = materialService.updateMaterial(worksMaterial);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("such data now found");
        } else {
            return ResponseEntity.ok("materials was updated");
        }
    }

}
