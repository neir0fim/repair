package com.kuzin.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**mvc controller class.*/

@Controller
@RequestMapping("/units")
public class UnitMvcController {

    @GetMapping
    public String getUnits() {
        return "unit/units";
    }

    @GetMapping("/get/{id}")
    public String getUnit(@PathVariable ("id") long id) {
        return "unit/unit";
    }
}
