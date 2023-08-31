package com.gamo.ecommerce1.DAO;

import com.gamo.ecommerce1.model.Article;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CRUDArticle implements ArticleRepository {

    private final DataSource dataSource;

    public CRUDArticle(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createArticle(Article article) {
        String query = "INSERT INTO articles (name, description, size, price, gender, category, availability) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, article.getName());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getSize());
            preparedStatement.setLong(4, article.getPrice());
            preparedStatement.setString(5, article.getGender());
            preparedStatement.setString(6, article.getCategory());
            preparedStatement.setBoolean(7, article.getAvailability());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM articles")) {

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setName(resultSet.getString("name"));
                article.setDescription(resultSet.getString("description"));
                article.setSize(resultSet.getString("size"));
                article.setPrice(resultSet.getLong("price"));
                article.setGender(resultSet.getString("gender"));
                article.setCategory(resultSet.getString("category"));
                article.setAvailability(resultSet.getBoolean("availability"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public Article getArticleById(int id) {
        String query = "SELECT * FROM articles WHERE id = ?";
        try (Connection connection= dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToArticle(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateArticle(Article article) {
        String query = "UPDATE articles SET name = ?, description = ?, size = ?, price = ?, gender = ?, category = ?, availability = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, article.getName());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getSize());
            preparedStatement.setLong(4, article.getPrice());
            preparedStatement.setString(5, article.getGender());
            preparedStatement.setString(6, article.getCategory());
            preparedStatement.setBoolean(7, article.getAvailability());
            preparedStatement.setInt(8, article.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteArticle(int id) {
        String query = "DELETE FROM articles WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
