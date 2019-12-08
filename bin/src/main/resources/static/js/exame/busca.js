$(document).ready(function() {
				$( function() {
					$("#txtBusca").autocomplete({
						source: "buscar/2",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/exame/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			});
			
			function muda3() {
				$('#txtBusca').unmask();
				$("#dropdownMenu2").text("Nome");
				$("#dropdownMenu2").val("3");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "buscar/3",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/exame/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda2() {
				$('#txtBusca').unmask();
				$("#dropdownMenu2").text("Paciente");
				$("#dropdownMenu2").val("2");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "buscar/2",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/exame/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda4() {
				$('#txtBusca').unmask();
				$("#dropdownMenu2").text("Data de Coleta");
				$("#dropdownMenu2").val("4");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "buscar/4",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/exame/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}