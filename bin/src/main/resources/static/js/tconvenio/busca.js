$(document).ready(function() {

	
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/tconvenios/buscar/" + $("#convenio_id").val() ,
				
				minLength: 1,
				select: function (event, ui) {
					url = "http://localhost:8080/tconvenios/editar/" + ui.item.value;
					document.location.href = url;
					return false;
				}
			});
		});
	});