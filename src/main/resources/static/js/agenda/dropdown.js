$(document).ready(function() {
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/agenda/listar/1",
				minLength: 1,
				select: function (event, ui) {
					url = "http://localhost:8080/agenda/agendar/" + ui.item.value;
					document.location.href = url;
					return false;
				}
			});
		});
	});
function muda() {
	$('#txtBusca').unmask();	
				$("#dropdownMenu2").text("Prontuário");
				$("#dropdownMenu2").val("1");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/agenda/listar/1",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			function muda2() {
				$('#txtBusca').unmask();
				$("#dropdownMenu2").text("Nome");
				$("#dropdownMenu2").val("2");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/agenda/listar/2",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda3() {
				$('#txtBusca').mask('000.000.000-00');
				$("#dropdownMenu2").text("CPF");
				$("#dropdownMenu2").val("3");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/agenda/listar/3",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda4() {
				$('#txtBusca').mask('(00) 0000-0000');
				$("#dropdownMenu2").text("Telefone");
				$("#dropdownMenu2").val("4");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/agenda/listar/4",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}