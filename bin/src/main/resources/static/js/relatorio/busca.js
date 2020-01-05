$(document).ready(function () {
	$(function () {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/relatorio/buscar/1",
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
	//Limpa o campo de busca e faz o campo aparecer
	$('#txtBusca').val('');
	$('#txtBusca').css("display", "");

	//Esconde os demais campos
	$('#convenio').css("display", "none");
	$('#periodo').css("display", "none");
	

	$("#dropdownMenu2").text("Paciente");
	$("#dropdownMenu2").val("1");
	$(function () {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/relatorio/buscar/1",
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
	//Habilita o combo box de convenio
	$("#convenio").prop("hidden", false);
	$("#convenio").css("display","");
	//Esconde os demais campos
	$("#txtBusca").css("display", "none");
	$('#periodo').css("display", "none");
	

	$('#txtBusca').val('');
	$("#dropdownMenu2").text("Convenio");
	$("#dropdownMenu2").val("2");

}

function muda3() {
	//Desabilita os demais campos
	$("#txtBusca").css("display", "none");
	$("#convenio").prop("hidden", true);
	//Habilita os campos de data
	$("#dataInicio").prop("hidden", false);
	$("#dataFim").prop("hidden", false);
	$("#periodo").css("display","");
	
	$("#camposBusca").css("width", "450px")

	$("#dropdownMenu2").text("Periodo");
	$("#dropdownMenu2").val("3");
	$(function () {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/relatorio/buscar/3",
			minLength: 2,
			select: function (event, ui) {

				return false;
			}
		});
	});
}

function muda4() {
	//Limpa o campo de busca e faz o campo aparecer
	$('#txtBusca').val('');
	$('#txtBusca').css("display", "");

	//Esconde os demais campos
	$('#convenio').prop("hidden", true);
	$('#dataInicio').prop("hidden", true);
	$('#dataFim').prop("hidden", true);
	$("#dropdownMenu2").text("Funcionario");
	$("#dropdownMenu2").val("4");
	$(function () {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/relatorio/buscar/3",
			minLength: 2,
			select: function (event, ui) {
				$("#funcionario").val(ui.item.value);
				alert("funcionario selecionado");
				return false;
			}
		});
	});
}