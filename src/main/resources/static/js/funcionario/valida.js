$(document).ready(function() {
		$("#tipo").change(function() {
			var id =  $(this).val();
			if(id == 2 ){
				alert("Opção escolhida: Funcionário");
				$("#especialidade").prop("disabled", true);
				$("#especialidade").val("");
				$("#crm").prop("disabled", true);
				$("#crm").val("");
				
			}else{
				alert("Opção escolhida: Médico");
				$("#especialidade").prop("disabled", false);
				$("#especialidade").val("");
				$("#crm").prop("disabled", false);
				$("#crm").val("");
			}
			
		});
	});