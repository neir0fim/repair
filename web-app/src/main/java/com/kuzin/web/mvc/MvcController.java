package com.kuzin.web.mvc;

import com.kuzin.entity.enums.ApplicationUserRole;
import com.kuzin.service.service.ArticleService;
import com.kuzin.service.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/** mvc controller class.*/
@Controller
public class MvcController {
    UnitService service;
    ArticleService articleService;

    private static final String UNIT = "units";

    @Autowired
    public MvcController(UnitService service, ArticleService articleService) {
        this.service = service;
        this.articleService = articleService;
    }

    @GetMapping("users")
    public String addPersons(Model model) {
        model.addAttribute(UNIT, service.getAll());
        model.addAttribute("roles", ApplicationUserRole.values());

        return "person/new";
    }

    @GetMapping("units/new")
    public String addUnit() {
        return "unit/new";
    }

    @GetMapping("article/new")
    public String addArticle(Model model) {
        model.addAttribute(UNIT, service.getAll());

        return "article/new";
    }

    @GetMapping("/units/update/{id}")
    public String updateUnit(@PathVariable ("id") long id) {

        return "unit/update";
    }

    @GetMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable ("id") long id, Model model) {
        model.addAttribute(UNIT, service.getAll());
        model.addAttribute("id", id);

        return "article/update";
    }

    @GetMapping("/person/update/{name}")
    public String updatePerson(@PathVariable ("name") String name, Model model) {
        model.addAttribute(UNIT, service.getAll());
        model.addAttribute("roles", ApplicationUserRole.values());
        model.addAttribute("name", name);


        return "person/update";
    }


    @GetMapping("/repair/update/{id}")
    public String updateRepair(@PathVariable ("id") long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("articles", articleService.getForUser());

        return "repair/update";
    }

}
