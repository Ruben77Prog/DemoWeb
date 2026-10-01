<%@ include file="/html/common/header.jsp"%>

<%@ page import="com.ruben.bluewave.model.IncidenciaDTO"%>

<%
IncidenciaDTO incidencia = (IncidenciaDTO) request.getAttribute("incidencia");
%>

<div class="detail-card">

	<h1>
		<%=incidencia.getTipoIncidenciaNombre()%>
	</h1>

	<p class="detail-description">
		<%=incidencia.getDescripcion()%>
	</p>

	<a class="btn"
		href="<%=request.getContextPath()%>/incidencia?action=search">

		Volver a incidencias </a>

</div>

<%@ include file="/html/common/footer.jsp"%>