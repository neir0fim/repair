package com.kuzin.web.rest;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.kuzin.entity.Article;
import com.kuzin.service.service.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

/**article controller class.*/

@RestController
@RequestMapping("/articles")
public class ArticleController {
    ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping()
    public CollectionModel<EntityModel<Article>> get() {
        List<EntityModel<Article>> result = service.getAll().stream().map(
                article -> EntityModel.of(article, linkTo(methodOn(ArticleController.class)
                        .getById(article.getId())).withSelfRel())).toList();


        return CollectionModel.of(result, linkTo(methodOn(ArticleController.class)
                .get()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Article> getById(@PathVariable ("id") long id) {
        return EntityModel.of(service.get(id),
                linkTo(methodOn(ArticleController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(RepairController.class).getAll()).withSelfRel());
    }

    @PostMapping
    public EntityModel<Article> save(@RequestBody Article article) {
        Article result = service.save(article);

        return EntityModel.of(result, linkTo(methodOn(ArticleController.class)
                .getById(result.getId())).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody Article article, @PathVariable ("id") long id) {
        service.update(article, id);
    }


}
