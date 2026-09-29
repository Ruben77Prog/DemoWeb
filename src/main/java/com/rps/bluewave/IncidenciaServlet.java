package com.rps.bluewave;

import java.io.IOException;
import java.io.PrintWriter;

import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.ServiceException;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/evento-musical")
public class IncidenciaServlet extends HttpServlet {

	private IncidenciaService incidenciaService = null;

	public IncidenciaServlet() {
		incidenciaService = new IncidenciaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

//		writer.append("<html>");
//		writer.append("Served at: ").append(request.getContextPath());
//		writer.append("</br>");
//		writer.append("<h1>Voy a aprender un montón</h1>");

		String action = request.getParameter("action");

		if ("detail".equalsIgnoreCase(action)) {

			// Action: Ver el detalle de una incidencia
			String idStr = request.getParameter("id");
			// EventoMusicalCriteria criteria = new EventoMusicalCriteria();
			// criteria.setId(Long.valueOf(idStr));
			try {
				IncidenciaDTO evento = incidenciaService.findById(Long.valueOf(idStr));
				// Redirige a la vista detalle
				request.setAttribute("evento", evento);
				request.getRequestDispatcher(Views.INCIDENCIA_DETAIL).forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("search".equalsIgnoreCase(action)) {

			// Action: Buscar eventos
			String nombre = request.getParameter("nombre");
			String fechaInicio = request.getParameter("fechaInicio");

			IncidenciaCriteria criteria = new IncidenciaCriteria();
			criteria.setClienteNombre(nombre);
			try {
				Results<IncidenciaDTO> results = incidenciaService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
				// Reenviar a la vista ....
				request.setAttribute("results", results);
				request.getRequestDispatcher(Views.INCIDENCIA_SEARCH).forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("delete".equalsIgnoreCase(action)) {
			// Action: Eliminar una incidencia
			String idStr = request.getParameter("id");
			try {
				incidenciaService.delete(Long.valueOf(idStr));
			} catch (Exception e) {
				e.printStackTrace();
				writer.append("<i>No ha podido borrarse el evento. Por favor contacte con su administrador</i>");
			}
		} else {
			// Niguna action
		}
		writer.append("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
