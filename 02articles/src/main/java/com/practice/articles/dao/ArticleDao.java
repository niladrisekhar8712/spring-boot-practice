package com.practice.articles.dao;

import com.practice.articles.model.Article;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ArticleDao {
    int insertArticle(Article article) throws SQLException;
    List<Article> selectAllArticle() throws SQLException;
    Article getArticleById(UUID id) throws SQLException;
    int deleteArticleById(UUID id) throws SQLException;
    int deleteAllArticles() throws SQLException;
    int updateArticleById(UUID id, Article article) throws SQLException;

}
