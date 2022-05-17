package com.kuzin.web.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**controller class.*/
@Controller
@RequestMapping("/materials")
public class MaterialMvcController {

    @GetMapping
    public String getUnits() {
        return "supp/materials";
    }

    @GetMapping("/get/{code}")
    public String getUnit(@PathVariable ("code") long code) {
        return "supp/material";
    }


    @GetMapping("/new")
    public String addMaterial() {
        return "supp/new";
    }

    @GetMapping("/update/{code}")
    public String updateMaterial(@PathVariable ("code") long code) {
        return "supp/update";
    }

    @GetMapping("/download")
    public String newRepair() {
        return "download";
    }

    @GetMapping("/result/{success}/{fail}")
    public String resultDownload(@PathVariable ("success") int success,
                                 @PathVariable ("fail") int fail, Model model) {

        model.addAttribute("success", success);
        model.addAttribute("fail", fail);

        return "supp/result";
    }
}
