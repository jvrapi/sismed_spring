$(document).ready(function() {
	var today = new Date();
	var dy = today.getDate();
	var mt = today.getMonth()+1;
	var yr = today.getFullYear();
	$("#data").val(yr+"-"+mt+"-"+dy);
	
	var usuario = $("#usuario").val();
	
	if(usuario == 2){
		$("#funcionario_id").change(function() {
			var url = "http://localhost:8080/agenda/agendaFuncionario/" + $(this).val();
			$("#resultsBlock").load(url);
			$("#data").prop("disabled", false);
			$("#data").val(yr+"-"+mt+"-"+dy);
		});
	
	}else{
	var url = "http://localhost:8080/agenda/agendaMedico" ;
	$("#resultsBlock").load(url);
	}
	
	
	
});

