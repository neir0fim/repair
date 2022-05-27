package com.kuzin.web.mvc;

import com.kuzin.service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**common page controller.*/

@Controller
@RequestMapping("/")
public class HomeMvcController {

    ArticleService articleService;


    @Autowired
    public HomeMvcController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("getLogin")
    public String login() {
        return "/home/loginPage";
    }

    @GetMapping
    public String homePage() {
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