package com.gamo.ecommerce1.repository;

import com.gamo.ecommerce1.model.Customers;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
    public class CRUDCustomers implements CustomersRepository{
        private final DataSource dataSource;
        public CRUDCustomers(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        @Override
        public List<Customers> findAll() {
            List<Customers> customersList = new ArrayList<>();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers");
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Customers customers = mapRowToCustomers(resultSet);
                    customersList.add(customers);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return customersList;
        }
        @Override
        public Customers findById(int id) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?")) {

                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapRowToCustomers(resultSet);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        public Customers insert(Customers customers) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO customers (name, address, gender, phone_number, email, CIN) VALUES (?, ?, ?, ?, ?, ?)")) {

                preparedStatement.setString(1, customers.getName());
                preparedStatement.setString(2, customers.getAddress());
                preparedStatement.setString(3, customers.getGender());
                preparedStatement.setInt(4, customers.getPhone_number());
                preparedStatement.setString(5, customers.getEmail());
                preparedStatement.setString(6, customers.getCIN());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return customers;
        }

        @Override
        public void update(Customers customers) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE customers SET name = ?, address = ?, gender = ?, phone_number = ?, email = ?, CIN = ? WHERE id = ?")) {

                preparedStatement.setString(1, customers.getName());
                preparedStatement.setString(2, customers.getAddress());
                preparedStatement.setString(3, customers.getGender());
                preparedStatement.setInt(4, customers.getPhone_number());
                preparedStatement.setString(5, customers.getEmail());
                preparedStatement.setString(6, customers.getCIN());
                preparedStatement.setInt(7, customers.getId());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean delete(int id) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE id = ?")) {

                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() > 0;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private Customers mapRowToCustomers(ResultSet resultSet) throws SQLException {
            Customers customers = new Customers();
            customers.setId(resultSet.getInt("id"));
            customers.setName(resultSet.getString("name"));
            customers.setAddress(resultSet.getString("address"));
            customers.setGender(resultSet.getString("gender"));
            customers.setPhone_number(resultSet.getInt("phone_number"));
            customers.setEmail(resultSet.getString("email"));
            customers.setCIN(resultSet.getString("CIN"));
            return customers;
        }
    }
