$(document).ready(function() {
		$( function() {
			$("#txtBusca").autocomplete({
				source: "http://localhost:8080/convenios/buscar/1",
				minLength: 1,
				select: function (event, ui) {
					url = "http://localhost:8080/convenios/editar/" + ui.item.value;
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
						source: "http://localhost:8080/convenios/buscar/1",
						minLength: 1,
						select: function (event, ui) {
							url = "http://localhost:8080/convenios/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			function muda2() {
				$('#txtBusca').mask("99.999.999/9999-99");
				$('#txtBusca').val('');
				$("#dropdownMenu2").text("CNPJ");
				$("#dropdownMenu2").val("2");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/convenios/buscar/2",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/convenios/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda3() {
				$('#txtBusca').val('');
				$('#txtBusca').mask('000000');
				$("#dropdownMenu2").text("ANS");
				$("#dropdownMenu2").val("3");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/convenios/buscar/3",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/convenios/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
		