package com.gymmanagement.dao;

import com.gymmanagement.model.Batch;

import java.util.List;

public interface BatchDAO {
    void addBatch(Batch batch);

    List<Batch> getAllBatches();

    Batch getBatchById(int id);

    void updateBatch(Batch batch);

    void deleteBatch(int id);
}
