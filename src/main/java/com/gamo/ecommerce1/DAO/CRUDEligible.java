package com.gamo.ecommerce1.DAO;

import com.gamo.ecommerce1.model.Article;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CRUDEligible implements EligibleArticle {

    private final DataSource dataSource;
    public CRUDEligible(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articles");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Article article = mapRowToArticle(resultSet);
                articleList.add(article);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articleList;
    }

    @Override
    public EligibleArticle findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articles WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return (EligibleArticle) mapRowToArticle(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public EligibleArticle insert(EligibleArticle eligibleArticle) {
        return null;
    }

    @Override
    public void update(EligibleArticle eligibleArticle) {

    }

    @Override
    public Article insert(Article article) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO articles (name, description) VALUES (?, ?)")) {

            preparedStatement.setString(1, article.getName());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Override
    public void update(Article article) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE articles SET name = ?, description = ? WHERE id = ?")) {

            preparedStatement.setString(1, article.getName());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setInt(3, article.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM articles WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Article mapRowToArticle(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setName(resultSet.getString("name"));
        article.setDescription(resultSet.getString("description"));
        article.setSize(resultSet.getString("size"));
        article.setPrice(resultSet.getLong("price"));
        article.setGender(resultSet.getString("gender"));
        article.setCategory(resultSet.getString("category"));
        article.setAvailability(resultSet.getBoolean("availability"));
        return article;
    }

}
