package com.kuzin.web.rest;

import com.kuzin.entity.Material;
import com.kuzin.service.service.MaterialService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**material controller class.*/

@RestController
@RequestMapping("/materials/api")
public class MaterialController {

    MaterialService service;

    @Autowired
    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @GetMapping("/{cod}")
    public Material get(@PathVariable ("cod") long cod) {
        return service.get(cod);
    }

    @GetMapping
    public List<Material> get() {
        return service.getAll();
    }

    @PostMapping
    public void save(@ModelAttribute Material material, HttpServletResponse response)
            throws IOException {
        service.save(material);

        response.sendRedirect("/materials/get/" + material.getCod());
    }

    @PatchMapping("/{cod}")
    public ResponseEntity<String> update(@RequestBody Material material,
                                         @PathVariable ("cod") long cod) {
        service.update(material, cod);

        return ResponseEntity.ok("material " + cod + " was updated");
    }

    @DeleteMapping("/{cod}")
    public ResponseEntity<String> delete(@PathVariable ("cod") long cod) {
        int result = service.delete(cod);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user input");
        } else {
            return ResponseEntity.ok("user " + cod + " was deleted");
        }
    }

    @PostMapping("/input")
    public void download(@RequestParam ("file") MultipartFile file, HttpServletRequest request,
             HttpServletResponse response)
            throws IOException {


        String filePath = request.getServletContext().getRealPath("/");
        File result = new File(filePath);
        file.transferTo(result);


        int[] getResult = service.download(result);

        response.sendRedirect("/materials/result/" + getResult[0] + "/" + getResult[1]);

    }






}
