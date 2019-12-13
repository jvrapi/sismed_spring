$(document).ready(function() {
		$("#tipo").change(function() {
			var id =  $(this).val();
			if(id == 2 ){
			
				$("#especialidade").prop("disabled", true);
				$("#especialidade").val("");
				$("#crm").prop("disabled", true);
				$("#crm").val("");
				
			}else{
				
				$("#especialidade").prop("disabled", false);
				$("#especialidade").val("");
				$("#crm").prop("disabled", false);
				$("#crm").val("");
			}
			
		});
	});