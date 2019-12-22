$(document).ready(function() {
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/sismed/RegistroClinico/listar/2",
			minLength: 3,
			select: function (event, ui) {
				url = "http://localhost:8080/sismed/RegistroClinico/cadastrarpac/" + ui.item.value;
				document.location.href = url;
				return false;
			}
		});
	});
});
function muda() {
	$("#dropdownMenu2").text("Prontuário");
	$("#dropdownMenu2").val("1");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/sismed/RegistroClinico/listar/1",
			minLength: 1,
			select: function (event, ui) {
				url = "http://localhost:8080/sismed/RegistroClinico/cadastrarpac/" + ui.item.value;
				document.location.href = url;
				return false;
			}
		});
	});
}
function muda2() {
	$("#dropdownMenu2").text("Nome");
	$("#dropdownMenu2").val("2");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/sismed/RegistroClinico/listar/2",
			minLength: 3,
			select: function (event, ui) {
				url = "http://localhost:8080/sismed/RegistroClinico/cadastrarpac/" + ui.item.value;
				document.location.href = url;
				return false;
			}
		});
	});
}