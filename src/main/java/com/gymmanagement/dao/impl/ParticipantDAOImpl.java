package com.gymmanagement.dao.impl;

import com.gymmanagement.dao.ParticipantDAO;
import com.gymmanagement.model.Participant;
import com.gymmanagement.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAOImpl implements ParticipantDAO {
	private static final String INSERT_PARTICIPANT_SQL = "INSERT INTO participants (name, email, batch_id) VALUES (?, ?, ?)";
	private static final String SELECT_ALL_PARTICIPANTS = "SELECT * FROM participants";
	private static final String SELECT_PARTICIPANT_BY_ID = "SELECT * FROM participants WHERE id = ?";
	private static final String UPDATE_PARTICIPANT_SQL = "UPDATE participants SET name = ?, email = ?, batch_id = ? WHERE id = ?";
	private static final String DELETE_PARTICIPANT_SQL = "DELETE FROM participants WHERE id = ?";

	@Override
	public void addParticipant(Participant participant) {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_PARTICIPANT_SQL)) {

			statement.setString(1, participant.getName());
			statement.setString(2, participant.getEmail());
			statement.setInt(3, participant.getBatchId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Participant> getAllParticipants() {
		List<Participant> participants = new ArrayList<>();
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PARTICIPANTS);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Participant participant = new Participant(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("email"), resultSet.getInt("batch_id"));
				participants.add(participant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participants;
	}

	@Override
	public Participant getParticipantById(int id) {
		Participant participant = null;
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_PARTICIPANT_BY_ID)) {

			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					participant = new Participant(resultSet.getInt("id"), resultSet.getString("name"),
							resultSet.getString("email"), resultSet.getInt("batch_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participant;
	}

	@Override
	public void updateParticipant(Participant participant) {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PARTICIPANT_SQL)) {

			statement.setString(1, participant.getName());
			statement.setString(2, participant.getEmail());
			statement.setInt(3, participant.getBatchId());
			statement.setInt(4, participant.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteParticipant(int id) {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PARTICIPANT_SQL)) {

			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
