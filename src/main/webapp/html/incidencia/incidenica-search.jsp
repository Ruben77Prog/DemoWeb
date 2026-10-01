<div class="search-box">

	<h2>Buscar incidencias</h2>

	<form action="<%=request.getContextPath()%>/incidencia" method="post">

		<input type="hidden" name="action" value="search"> <label
			for="nombre"> Nombre del cliente </label> <input type="text"
			id="nombre" name="nombre" placeholder="Ej: Juan"> <input
			type="submit" value="Buscar" class="btn">

	</form>

</div>