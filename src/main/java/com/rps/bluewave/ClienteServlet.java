package com.rps.bluewave;

import java.io.IOException;
import java.io.PrintWriter;

import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Cliente")
public class ClienteServlet extends HttpServlet {

	private ClienteService clienteService = null;

	public ClienteServlet() {
		super();
		clienteService = new ClienteServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("login".equalsIgnoreCase(action)) {

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			try {

				Cliente cliente = new Cliente();
				cliente.setEmail(email);
				cliente.setContrasena(password);

				Cliente clienteEncontrado = clienteService.login(cliente);

				if (clienteEncontrado != null) {

					// Guardamos el cliente en la sesión
					HttpSession session = request.getSession();
					session.setAttribute("usuario", clienteEncontrado);

					// Redirigimos a la página de incidencias
					response.sendRedirect(request.getContextPath() + "/incidencia?action=search");

				} else {

					// Login incorrecto
					request.setAttribute("error", "Email o contraseña incorrectos.");

					request.getRequestDispatcher(Views.CLIENTE_LOGIN).forward(request, response);
				}

			} catch (Exception e) {

				e.printStackTrace();

				request.setAttribute("error", "Se ha producido un error durante el login.");

				request.getRequestDispatcher(Views.CLIENTE_LOGIN).forward(request, response);
			}

		} else if ("logout".equalsIgnoreCase(action)) {

			// Recuperamos la sesión existente
			HttpSession session = request.getSession(false);

			if (session != null) {

				session.invalidate();
			}

			response.sendRedirect(request.getContextPath() + Views.CLIENTE_LOGIN);

		} else {

			response.sendRedirect(request.getContextPath() + Views.CLIENTE_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}