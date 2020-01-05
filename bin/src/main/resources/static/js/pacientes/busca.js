$(document).ready(function() {
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/pacientes/buscar/2",
			minLength: 1,
			select: function (event, ui) {
				url = "http://localhost:8080/pacientes/editar/" + ui.item.value2;
				document.location.href = url;
				return false;
			}
		});
	});
});
function muda() {
	$('#txtBusca').unmask();
	$("#dropdownMenu2").text("Prontuário");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/pacientes/buscar/1",
			minLength: 1,
			select: function (event, ui) {
				url = "http://localhost:8080/pacientes/editar/" + ui.item.value2;
				document.location.href = url;
				return false;
			}
		});
	});
}
function muda2() {
	$('#txtBusca').unmask();
	$("#dropdownMenu2").text("Nome");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/pacientes/buscar/2",
			minLength: 2,
			select: function (event, ui) {
				url = "http://localhost:8080/pacientes/editar/" + ui.item.value2;
				document.location.href = url;
				return false;
			}
		});
	});
}
function muda3() {
	$('#txtBusca').unmask();
	$('#txtBusca').mask('000.000.000-00');
	$("#dropdownMenu2").text("CPF");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/pacientes/buscar/3",
			minLength: 2,
			select: function (event, ui) {
				url = "http://localhost:8080/pacientes/editar/" + ui.item.value2;
				document.location.href = url;
				return false;
			}
		});
	});
}
function muda4() {
	$('#txtBusca').unmask();
	$('#txtBusca').mask('(00) 9 0000-0000');
	$("#dropdownMenu2").text("Celular");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/pacientes/buscar/4",
			minLength: 2,
			select: function (event, ui) {
				url = "http://localhost:8080/pacientes/editar/" + ui.item.value2;
				document.location.href = url;
				return false;
			}
		});
	});
}
