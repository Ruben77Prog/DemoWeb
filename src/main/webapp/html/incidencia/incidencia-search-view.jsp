<%@ include file="/html/common/header.jsp"%>

<%@ page import="com.ruben.bluewave.model.IncidenciaDTO"%>
<%@ page import="com.ruben.bluewave.model.Results"%>

<%
Results<IncidenciaDTO> results = (Results<IncidenciaDTO>) request.getAttribute("results");
%>

<div class="page-title">

	<h1>Incidencias</h1>

	<p>Consulta las incidencias registradas en Bluewave.</p>

</div>

<%@ include file="/html/incidencia/incidenica-search.jsp"%>

<%
if (results != null) {
%>

<div class="results">

	<h2>
		Encontrados
		<%=results.getTotal()%>
		resultados
	</h2>

	<ol class="incidencias">

		<%
		for (IncidenciaDTO incidencia : results.getPage()) {
		%>

		<li class="incidencia-card">

			<div>

				<a class="incidencia-title"
					href="<%=request.getContextPath()%>/incidencia?action=detail&id=<%=incidencia.getId()%>">

					<%=incidencia.getTipoIncidenciaNombre()%>

				</a>

			</div> <a class="delete"
			href="<%=request.getContextPath()%>/incidencia?action=delete&id=<%=incidencia.getId()%>">

				Eliminar </a>

		</li>

		<%
		}
		%>

	</ol>

</div>

<%
}
%>

<%@ include file="/html/common/footer.jsp"%>