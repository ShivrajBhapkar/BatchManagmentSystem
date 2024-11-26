package com.gymmanagement.dao;

import com.gymmanagement.model.Participant;

import java.util.List;

public interface ParticipantDAO {
    void addParticipant(Participant participant);

    List<Participant> getAllParticipants();

    Participant getParticipantById(int id);

    void updateParticipant(Participant participant);

    void deleteParticipant(int id);
}
