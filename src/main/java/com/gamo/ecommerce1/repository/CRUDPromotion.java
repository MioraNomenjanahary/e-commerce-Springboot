package com.gamo.ecommerce1.repository;

import com.gamo.ecommerce1.model.Promotion;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CRUDPromotion implements PromotionRepository {

    private final DataSource dataSource;

    public CRUDPromotion(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Promotion> findAll() {
        List<Promotion> promotionList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM promotions");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Promotion promotion = mapRowToPromotion(resultSet);
                promotionList.add(promotion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return promotionList;
    }

    @Override
    public Promotion findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM promotions WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToPromotion(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Promotion insert(Promotion promotion) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO promotions (type, start_date, end_date, values) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, promotion.getType());
            preparedStatement.setDate(2, java.sql.Date.valueOf(promotion.getStart_date()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(promotion.getEnd_date()));
            preparedStatement.setDouble(4, promotion.getValues());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return promotion;
    }

    @Override
    public void update(Promotion promotion) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE promotions SET type = ?, start_date = ?, end_date = ?, values = ? WHERE id = ?")) {

            preparedStatement.setString(1, promotion.getType());
            preparedStatement.setDate(2, java.sql.Date.valueOf(promotion.getStart_date()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(promotion.getEnd_date()));
            preparedStatement.setDouble(4, promotion.getValues());
            preparedStatement.setInt(5, promotion.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM promotions WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Promotion mapRowToPromotion(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String type = resultSet.getString("type");
        LocalDate start_date = resultSet.getDate("start_date").toLocalDate();
        LocalDate end_date = resultSet.getDate("end_date").toLocalDate();
        Double value = resultSet.getDouble("values");

        Promotion promotion = new Promotion(id, type, end_date, start_date, value);
        return promotion;
    }

}
