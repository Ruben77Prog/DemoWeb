<%@ include file="/html/common/header.jsp"%>

<%
	Results<IncidenciaDTO> results = (Results<IncidenciaDTO>) request.getAttribute("results");
	out.println("Encontrados " + results.getTotal() + " resultados");
	out.println("<ol>");
	for (IncidenciaDTO e : results.getPage()) {
		out.println("<li>"
					+"<a href="+request.getContextPath()+"/incidencia?action=detail&id=" + e.getId() +">"+e.getTipoIncidenciaNombre() +"</a>"
					+"<a href="+request.getContextPath()+"/incidencia?action=delete&id=" + e.getId() +">"
									+"<img src='"+request.getContextPath()+"/img/delete.jpg'/>"
				
					+"</li>");
	}
	out.println("</ol>");
%>
<%@ include file="/html/common/footer.jsp"%>
