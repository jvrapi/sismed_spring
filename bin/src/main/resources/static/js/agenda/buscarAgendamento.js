$(document).ready(function() {
	if($("#usuario").val() != 2){
		$("#data").prop("disabled", false);
	}
});
function busca(){
	
	var data = $("#data").val();
	var medico = $("#funcionario_id").val();
		
	var url = "http://localhost:8080/agenda/buscarAgendamento/" + data + "/" + medico;
	$("#resultsBlock").load(url);
}
