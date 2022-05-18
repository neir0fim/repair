package com.kuzin.web.mvc;

import com.kuzin.service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**article mvc controller.*/
@Controller
public class ArticleMvcController {

    ArticleService articleService;

    private static final String ARTICLE = "articles";

    @Autowired
    public ArticleMvcController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String getArticles() {

        return "article/articles";
    }


    @GetMapping("/report/Article")
    public String getReport(Model model) {
        model.addAttribute(ARTICLE, articleService.getForUser());

        return "article/report";
    }

    @GetMapping("/filter/Repair")
    public String filterByRepair(Model model) {
        model.addAttribute(ARTICLE, articleService.getForUser());

        return "article/filterRepair";
    }



}
