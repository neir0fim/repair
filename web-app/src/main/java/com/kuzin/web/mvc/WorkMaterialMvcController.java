package com.kuzin.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**mvc controller class.*/

@Controller
@RequestMapping("/work/material")
public class WorkMaterialMvcController {

    @GetMapping("/{id}")
    public String addMaterial(@PathVariable ("id") long id) {
        return "works/new";
    }




}
