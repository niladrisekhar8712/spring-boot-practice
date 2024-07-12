package com.practice.articles.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.practice.articles.model.Article;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("mysqlDao")
public class ArticleAccessService implements ArticleDao{
    private final MysqlDataSource dataSource = new MysqlDataSource();
    private final String tableName = "Articles";
    private final Connection connection;
    private final Statement statement;

    public ArticleAccessService(){
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("articles");
        dataSource.setPort(3306);
        try{
            connection = dataSource.getConnection("Practice1", "splintercell@@");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int insertArticle(Article article) throws SQLException {
        String id = UUID.randomUUID().toString();
        String title = article.getTitle();
        String body = article.getBody();
        String query = "INSERT INTO %s VALUES ('%s', '%s', '%s');".formatted(tableName,id,title,body);
        boolean res = statement.execute(query);
        return res ? 1 : 0;
    }

    @Override
    public List<Article> selectAllArticle() throws SQLException {
        String query = "SELECT * FROM %s".formatted(tableName);
        ResultSet rs = statement.executeQuery(query);
        List<Article> list = new ArrayList<>();
        while(rs.next()){
            String id = rs.getString("id");
            String title = rs.getString("title");
            String body = rs.getString("body");
            list.add(new Article(UUID.fromString(id),title,body));
        }
        System.out.println(list);
        return list;
    }

    @Override
    public Article getArticleById(UUID id) throws SQLException {
        String query = "SELECT * FROM %s WHERE id = '%s'".formatted(tableName,id.toString());
        ResultSet rs = statement.executeQuery(query);
        if(rs.next()){
            String id1 = rs.getString("id");
            String title = rs.getString("title");
            String body = rs.getString("body");
            return new Article(UUID.fromString(id1),title,body);
        }
        return null;
    }

    @Override
    public int deleteArticleById(UUID id) throws SQLException {
        String query = "DELETE FROM %s WHERE id = '%s'".formatted(tableName,id.toString());
        return statement.execute(query) ? 1 : 0;
    }

    @Override
    public int deleteAllArticles() throws SQLException {
        String query = "DELETE FROM %s".formatted(tableName);
        return statement.execute(query) ? 1 : 0;
    }

    @Override
    public int updateArticleById(UUID id, Article article) throws SQLException {
        String query = "UPDATE %s SET title = '%s', body= '%s' WHERE id = '%s'"
            .formatted(tableName,article.getTitle(),article.getBody(), id.toString());
        statement.execute(query);
        return 1;
    }
}
