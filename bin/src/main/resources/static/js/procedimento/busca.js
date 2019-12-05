$(document).ready(function() {
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/procedimentos/buscar/1",
				minLength: 1,
				select: function (event, ui) {
					url = "http://localhost:8080/procedimentos/editar/" + ui.item.value;
					document.location.href = url;
					return false;
				}
			});
		});
	});
function muda() {
	
				$('#txtBusca').unmask();
				$('#txtBusca').val('');
				$("#dropdownMenu2").text("Descricao");
				$("#dropdownMenu2").val("1");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/procedimentos/buscar/1",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/procedimentos/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			
		