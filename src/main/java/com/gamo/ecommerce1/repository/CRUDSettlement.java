package com.gamo.ecommerce1.repository;

import com.gamo.ecommerce1.model.Settlement;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CRUDSettlement implements SettlementRepository {

    private final DataSource dataSource;

    public CRUDSettlement(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Settlement> findAll() {
        List<Settlement> settlementList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM settlements");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Settlement settlement = mapRowToSettlement(resultSet);
                settlementList.add(settlement);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return settlementList;
    }

    @Override
    public Settlement findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM settlements WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToSettlement(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Settlement insert(Settlement settlement) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO settlements (amount, date) VALUES (?, ?)")) {

            preparedStatement.setDouble(1, settlement.getPayment());
            preparedStatement.setDate(2, java.sql.Date.valueOf(settlement.getDate()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return settlement;
    }

    @Override
    public void update(Settlement settlement) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE settlements SET amount = ?, date = ? WHERE id = ?")) {

            preparedStatement.setDouble(1, settlement.getPayment());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(settlement.getDate().atStartOfDay()));
            preparedStatement.setInt(3, settlement.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM settlements WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Settlement mapRowToSettlement(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Long payment = resultSet.getLong("payment_id");
        String status = resultSet.getString("status");
        LocalDate date = resultSet.getDate("date").toLocalDate();

        return new Settlement(id, payment, status, date);
    }
}
