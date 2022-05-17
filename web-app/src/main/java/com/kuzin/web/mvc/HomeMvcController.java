package com.kuzin.web.mvc;

import com.kuzin.service.service.ArticleService;
import com.kuzin.service.service.FactoryService;
import com.kuzin.service.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**common page controller.*/

@Controller
@RequestMapping("/")
public class HomeMvcController {

    FactoryService service;
    ArticleService articleService;
    MaterialService materialService;

    @Autowired
    public HomeMvcController(FactoryService service, ArticleService articleService,
                             MaterialService materialService) {
        this.service = service;
        this.articleService = articleService;
        this.materialService = materialService;
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("factory", service.getFactory());

        return "login";
    }

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("factory", service.getFactory().getName());

        return "index";
    }

    @GetMapping("logoutPage")
    public String logout() {
        return "logoutPage";
    }

    @GetMapping("/download")
    public String downloadData() {
        return "/download";
    }

    @GetMapping("/admin")
    public String getAdminMenu() {
        return "/admin/admin";
    }

    @GetMapping("/user")
    public String getUserMenu(Model model) {
        model.addAttribute("articles", articleService.getAll());

        return "/user/user";
    }


    @GetMapping("/supp")
    public String suppMenu() {
        return "supp/menu";
    }

}