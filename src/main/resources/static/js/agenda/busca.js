$(document).ready(function() {
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/sismed/agenda/listar/1",
				minLength: 2,
				select: function (event, ui) {
					if(ui.item.value2 == '0'){
						
						url = "http://localhost:8080/sismed/agenda/preCadastro" ;
						document.location.href = url;
						return false;
					}
					url = "http://localhost:8080/sismed/agenda/agendar/" + ui.item.value;
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
						source: "http://localhost:8080/sismed/agenda/listar/1",
						minLength: 2,
						select: function (event, ui) {
							
							if(ui.item.value == 0){
								
								url = "http://localhost:8080/sismed/agenda/preCadastro" ;
								document.location.href = url;
								return false;
							}
							url = "http://localhost:8080/sismed/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			function muda2() {
				$('#txtBusca').unmask();
				$('#txtBusca').val('');
				$("#dropdownMenu2").text("Prontuario");
				$("#dropdownMenu2").val("2");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/sismed/agenda/listar/2",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/sismed/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda3() {
				$('#txtBusca').val('');
				$('#txtBusca').mask('000.000.000-00');
				$("#dropdownMenu2").text("CPF");
				$("#dropdownMenu2").val("3");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/sismed/agenda/listar/3",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/sismed/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda4() {
				$('#txtBusca').val('');
				$('#txtBusca').mask('(00) 0000-0000');
				$("#dropdownMenu2").text("Telefone");
				$("#dropdownMenu2").val("4");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/sismed/agenda/listar/4",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/sismed/agenda/agendar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}