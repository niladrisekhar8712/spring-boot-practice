package com.practice.articles.api;

import com.practice.articles.model.Article;
import com.practice.articles.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/article")
@RestController
public class ArticleController {
    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @PostMapping
    public void addArticle(@NonNull @RequestBody Article article) throws SQLException {
        articleService.addArticle(article);
    }
    @GetMapping
    public List<Article> getAllArticles() throws SQLException {
        return articleService.getAllArticles();
    }
    @GetMapping(path = "{id}")
    public  Article getArticleById(@PathVariable UUID id) throws SQLException {
        return articleService.getArticleById(id);
    }
    @DeleteMapping(path = "{id}")
    public void deleteArticleById(@PathVariable UUID id) throws SQLException {
        articleService.deleteArticleById(id);
    }
    @DeleteMapping
    public void deleteAllArticles() throws SQLException {
        articleService.deleteAllArticles();
    }
    @PutMapping(path = "{id}")
    public void updateArticleById(@PathVariable UUID id, @NonNull @RequestBody Article article) throws SQLException {
        articleService.updateArticleById(id,article);
    }
}
