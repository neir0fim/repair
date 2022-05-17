package com.kuzin.web.mvc;

import com.kuzin.service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**mvc controller class.*/

@Controller
@RequestMapping("/repair")
public class RepairMvcController {

    ArticleService articleService;

    @Autowired
    public RepairMvcController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getRepairForArticle(Model model) {
        model.addAttribute("articles", articleService.getForUser());

        return "repair/article";
    }

    @GetMapping("/get/{id}")
    public String getRepair(@PathVariable ("id") long id) {
        return "repair/repair";
    }


    @GetMapping("/new")
    public String newRepair(Model model) {
        model.addAttribute("articles", articleService.getForUser());

        return "repair/new";
    }



}
