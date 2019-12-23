$(document).ready(function() {
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/laboratorio/buscar/2",
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
				$('#txtBusca').unmask();
				$("#dropdownMenu2").text("Nome");
				$("#dropdownMenu2").val("2");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/laboratorio/buscar/2",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/laboratorio/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda3() {
				$('#txtBusca').unmask();
				$('#txtBusca').mask('(00) 0000-0000');  
				$("#dropdownMenu2").text("Telefone");
				$("#dropdownMenu2").val("3");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/laboratorio/buscar/3",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/laboratorio/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			
			function muda4() {
				$('#txtBusca').unmask();  
				$("#dropdownMenu2").text("Bairro");
				$("#dropdownMenu2").val("4");
				$( function() {
					$("#txtBusca").autocomplete({
						source: "http://localhost:8080/laboratorio/buscar/4",
						minLength: 2,
						select: function (event, ui) {
							url = "http://localhost:8080/laboratorio/editar/" + ui.item.value;
							document.location.href = url;
							return false;
						}
					});
				});
			}
			