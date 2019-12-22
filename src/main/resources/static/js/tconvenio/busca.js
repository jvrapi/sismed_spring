$(document).ready(function() {

	
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/sismed/tconvenios/buscar/" + $("#convenio_id").val() ,
				
				minLength: 1,
				select: function (event, ui) {
					url = "http://localhost:8080/sismed/tconvenios/editar/" + ui.item.value;
					document.location.href = url;
					return false;
				}
			});
		});
	});