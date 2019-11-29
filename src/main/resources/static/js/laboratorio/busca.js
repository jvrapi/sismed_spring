$(document).ready(function() {
				$( function() {
					$("#txtBusca").autocomplete({
						source: "buscar/1",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/laboratorio/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			});
			
			function muda2() {
				$("#dropdownMenu2").text("Nome");
				$("#dropdownMenu2").val("2");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "buscar/2",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/laboratorio/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			