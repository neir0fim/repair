package com.kuzin.web.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/** controller class.*/
@RestController
public class HomeController {

    @GetMapping("/auth/api")
    public Authentication checkAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
