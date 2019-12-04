$(document).ready(function() {
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/tconvenios/buscar/1",
				minLength: 1,
				select: function (event, ui) {
					url = "http://localhost:8080/tconvenios/editar/" + ui.item.value;
					document.location.href = url;
					return false;
				}
			});
		});
	});
function muda() {
	
				$('#txtBusca').unmask();
				$('#txtBusca').val('');
				$("#dropdownMenu2").text("Nome");
				$("#dropdownMenu2").val("1");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/tconvenios/buscar/1",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/tconvenios/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			
		