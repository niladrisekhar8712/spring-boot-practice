package com.practice.articles.service;

import com.practice.articles.dao.ArticleDao;
import com.practice.articles.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {
    private final ArticleDao articleDao;
    @Autowired
    public ArticleService(@Qualifier("mysqlDao") ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
    public int addArticle(Article article) throws SQLException {
        return articleDao.insertArticle(article);
    }
    public List<Article> getAllArticles() throws SQLException {
        return articleDao.selectAllArticle();
    }
    public Article getArticleById(UUID id) throws SQLException {
        return articleDao.getArticleById(id);
    }
    public int deleteArticleById(UUID id) throws SQLException {
        return articleDao.deleteArticleById(id);
    }
    public int deleteAllArticles() throws SQLException {
        return articleDao.deleteAllArticles();
    }
    public int updateArticleById(UUID id, Article article) throws SQLException{
        return articleDao.updateArticleById(id,article);
    }
}
