package com.kuzin.web.mvc;

import com.kuzin.service.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**common page controller.*/

@Controller
@RequestMapping("/")
public class HomeController {

    FactoryService service;

    @Autowired
    public HomeController(FactoryService service) {
        this.service = service;
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

    @GetMapping("getLogout")
    public String logout() {
        return "logout";
    }


}