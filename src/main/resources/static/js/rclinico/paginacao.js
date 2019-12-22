$(document).ready(function() {
	var url = "http://localhost:8080/sismed/RegistroClinico/find/" + $("#id_paciente").val();
	$("#resultsBlock").load(url);
	});

function mudaPágina(element) {
	var url = "http://localhost:8080/sismed/RegistroClinico/find/" + $("#id_paciente").val() + "?page=" + element.text;
	$("#resultsBlock").load(url);
}
function primeiraPagina() {
	var url = "http://localhost:8080/sismed/RegistroClinico/find/" + $("#id_paciente").val() + "?page=1";
	$("#resultsBlock").load(url);
}
function ultimaPagina() {
	var url = "http://localhost:8080/sismed/RegistroClinico/find/" + $("#id_paciente").val() + "?page=" + $("#ultimaPagina").val();
	$("#resultsBlock").load(url);
}
