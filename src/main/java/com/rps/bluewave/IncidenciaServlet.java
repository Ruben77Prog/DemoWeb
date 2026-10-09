package com.rps.bluewave;

import java.io.IOException;
import java.io.PrintWriter;

import com.bluewave.view.Views;
import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/incidencia")
public class IncidenciaServlet extends HttpServlet {

	private IncidenciaService incidenciaService = null;

	public IncidenciaServlet() {
		super();
		incidenciaService = new IncidenciaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");

		if (cliente == null) {

			// No hay usuario logueado
			response.sendRedirect(request.getContextPath() + Views.CLIENTE_LOGIN);

			return;
		}

		String action = request.getParameter("action");

		if ("detail".equalsIgnoreCase(action)) {

			// Action: Ver el detalle de una incidencia

			String idStr = request.getParameter("id");

			try {

				IncidenciaDTO incidencia = incidenciaService.findById(Long.valueOf(idStr));

				request.setAttribute("incidencia", incidencia);

				request.getRequestDispatcher(Views.INCIDENCIA_DETAIL).forward(request, response);

			} catch (Exception e) {

				e.printStackTrace();

			}

		} else if ("search".equalsIgnoreCase(action)) {

			// Action: Buscar incidencias

			String nombre = request.getParameter("nombre");

			IncidenciaCriteria criteria = new IncidenciaCriteria();

			criteria.setClienteNombre(nombre);

			try {

				Results<IncidenciaDTO> results = incidenciaService.findByCriteria(criteria, 1, Integer.MAX_VALUE);

				request.setAttribute("results", results);

				request.getRequestDispatcher(Views.INCIDENCIA_SEARCH).forward(request, response);

			} catch (Exception e) {

				e.printStackTrace();

				writer.append("<p>No se han podido obtener las incidencias.</p>");
			}

		} else if ("delete".equalsIgnoreCase(action)) {

			// Action: Eliminar una incidencia

			String idStr = request.getParameter("id");

			try {

				incidenciaService.delete(Long.valueOf(idStr));

				// Volvemos a la búsqueda
				response.sendRedirect(request.getContextPath() + "/incidencia?action=search");

			} catch (Exception e) {

				e.printStackTrace();

				writer.append(
						"<i>No ha podido borrarse la incidencia. " + "Por favor contacte con su administrador</i>");
			}

		} else {

			// Si no hay action, mostramos la búsqueda
			response.sendRedirect(request.getContextPath() + "/incidencia?action=search");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}