package com.gymmanagement.dao.impl;

import com.gymmanagement.dao.BatchDAO;
import com.gymmanagement.model.Batch;
import com.gymmanagement.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchDAOImpl implements BatchDAO {
    private static final String INSERT_BATCH_SQL = "INSERT INTO batches (name, timing) VALUES (?, ?)";
    private static final String SELECT_ALL_BATCHES = "SELECT * FROM batches";
    private static final String SELECT_BATCH_BY_ID = "SELECT * FROM batches WHERE id = ?";
    private static final String UPDATE_BATCH_SQL = "UPDATE batches SET name = ?, timing = ? WHERE id = ?";
    private static final String DELETE_BATCH_SQL = "DELETE FROM batches WHERE id = ?";

    @Override
    public void addBatch(Batch batch) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_BATCH_SQL)) {

            statement.setString(1, batch.getName());
            statement.setString(2, batch.getTiming());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Batch> getAllBatches() {
        List<Batch> batches = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BATCHES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Batch batch = new Batch(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("timing")
                );
                batches.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }

    @Override
    public Batch getBatchById(int id) {
        Batch batch = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BATCH_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    batch = new Batch(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("timing")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batch;
    }

    @Override
    public void updateBatch(Batch batch) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BATCH_SQL)) {

            statement.setString(1, batch.getName());
            statement.setString(2, batch.getTiming());
            statement.setInt(3, batch.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBatch(int id) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BATCH_SQL)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
