<%@ include file="/html/common/header.jsp" %>
<%
	IncidenciaDTO incidencia = (IncidenciaDTO ) request.getAttribute("evento"); 
	out.println("<h1>" + incidencia.getTipoIncidenciaNombre() + "</h1>");
	out.println("<p>" + incidencia.getDescripcion() + "</p>");
	
%>
<%@ include file="/html/common/footer.jsp" %>
