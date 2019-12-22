$(document).ready(function() {
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/sismed/relatorio/buscar/1",
				minLength: 1,
				select: function (event, ui) {
					$("#paciente").val(ui.item.value);
					alert("paciente selecionado");
					return false;
				}
			});
		});
	});
function muda() {
				$('#txtBusca').val('');
				$("#dropdownMenu2").text("Paciente");
				$("#dropdownMenu2").val("1");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/sismed/relatorio/buscar/1",
						minLength: 1,
						select: function (event, ui) {
							$("#paciente").val(ui.item.value);
							alert("paciente selecionado");
							return false;
						}
					});
				});
			}
			function muda2() {
				
				$('#txtBusca').val('');
				$("#dropdownMenu2").text("Convenio");
				$("#dropdownMenu2").val("2");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/sismed/relatorio/buscar/2",
						minLength: 2,
						select: function (event, ui) {
							$("#convenio").val(ui.item.value);
							alert("convenio selecionado");
							return false;
						}
					});
				});
			}
			
			function muda3() {
				
				$("#dropdownMenu2").text("Periodo");
				$("#dropdownMenu2").val("3");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/sismed/relatorio/buscar/3",
						minLength: 2,
						select: function (event, ui) {
							
							return false;
						}
					});
				});
			}
			
function muda4() {
				
				$("#dropdownMenu2").text("Funcionario");
				$("#dropdownMenu2").val("4");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/sismed/relatorio/buscar/3",
						minLength: 2,
						select: function (event, ui) {
							$("#funcionario").val(ui.item.value);
							alert("funcionario selecionado");
							return false;
						}
					});
				});
			}
			
		