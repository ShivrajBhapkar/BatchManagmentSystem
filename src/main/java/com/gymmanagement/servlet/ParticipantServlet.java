package com.gymmanagement.servlet;

import com.gymmanagement.dao.ParticipantDAO;
import com.gymmanagement.dao.impl.ParticipantDAOImpl;
import com.gymmanagement.model.Participant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/participant")
public class ParticipantServlet extends HttpServlet {
	private ParticipantDAO participantDAO;

	@Override
	public void init() throws ServletException {
		// Instantiate the DAO implementation
		participantDAO = new ParticipantDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

		try {
			if (idParam == null) {
				// Fetch all participants
				List<Participant> participants = participantDAO.getAllParticipants();
				request.setAttribute("participants", participants);
				request.getRequestDispatcher("viewParticipants.jsp").forward(request, response);
			} else {
				// Fetch a specific participant by ID
				int id = Integer.parseInt(idParam);
				Participant participant = participantDAO.getParticipantById(id);
				request.setAttribute("participant", participant);
				request.getRequestDispatcher("updateParticipant.jsp").forward(request, response);
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("_method");
		if ("delete".equalsIgnoreCase(method)) {
			doDelete(request, response);
		} else if ("put".equalsIgnoreCase(method)) {
			doPut(request, response);
		} else {
			// Add a new participant
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int batchId = Integer.parseInt(request.getParameter("batchId"));

			Participant participant = new Participant(0, name, email, batchId);
			participantDAO.addParticipant(participant);

			response.sendRedirect("participant");
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Update an existing participant
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		int batchId = Integer.parseInt(request.getParameter("batchId"));

		Participant participant = new Participant(id, name, email, batchId);
		participantDAO.updateParticipant(participant);

		response.sendRedirect("participant");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delete a participant by ID
		int id = Integer.parseInt(request.getParameter("id"));
		participantDAO.deleteParticipant(id);

		response.sendRedirect("participant");
	}
}
