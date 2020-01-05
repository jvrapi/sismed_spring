$(document).ready(function() {
	$("#modalDetalhesRegistro").on('show.bs.modal', function(event){
		if($("#recipient-bool").val() == 0){		
			$("#recipient-bool").css("display", "none");
			$("#recipient-descricao").prop("disabled", true);
		}
		else{
			$("#recipient-bool").css("display", "");
			$("#recipient-descricao").prop("disabled", false);
		}
	});
});