
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

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet("/Cliente")
public class ClienteServlet extends HttpServlet {

	ClienteService clienteService = null;

	public ClienteServlet() {
		super();
		clienteService = new ClienteServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		writer.append("<html>");

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
					writer.append("Hola " + clienteEncontrado.getNombre() + "!");
				} else {
					writer.append("Email o contraseña incorrectos.");
				}

			} catch (Exception e) {

				e.printStackTrace();
				writer.append("Se ha producido un error durante el login.");

			}

		} else {

			writer.append("Deberia redirigirte a pagina de error.");

		}
		
		writer.append("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

	    String action = request.getParameter("action");
	    System.out.println("ACTION EN DOPOST: " + action);
		doGet(request, response);
	}
}

