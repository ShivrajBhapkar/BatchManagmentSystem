package com.gymmanagement.servlet;

import com.gymmanagement.dao.BatchDAO;
import com.gymmanagement.dao.impl.BatchDAOImpl;
import com.gymmanagement.model.Batch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/batch")
public class BatchServlet extends HttpServlet {
	private BatchDAO batchDAO;

	@Override
	public void init() throws ServletException {
		// Instantiate the DAO implementation
		batchDAO = new BatchDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

		try {
			if (idParam == null) {
				// Fetch all batches
				List<Batch> batches = batchDAO.getAllBatches();
				request.setAttribute("batches", batches);
				request.getRequestDispatcher("viewBatches.jsp").forward(request, response);
			} else {
				// Fetch a specific batch by ID
				int id = Integer.parseInt(idParam);
				Batch batch = batchDAO.getBatchById(id);
				request.setAttribute("batch", batch);
				request.getRequestDispatcher("updateBatch.jsp").forward(request, response);
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
			// Add a new batch
			String name = request.getParameter("name");
			String timing = request.getParameter("timing");

			Batch batch = new Batch(0, name, timing);
			batchDAO.addBatch(batch);

			response.sendRedirect("batch");
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Update an existing batch
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String timing = request.getParameter("timing");

		Batch batch = new Batch(id, name, timing);
		batchDAO.updateBatch(batch);

		response.sendRedirect("batch");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delete a batch by ID
		int id = Integer.parseInt(request.getParameter("id"));
		batchDAO.deleteBatch(id);

		response.sendRedirect("batch");
	}
}
