$(document).ready(function() {
			$("#funcionario_id").change(function() {
				var url = "http://localhost:8080/sismed/agenda/funcionario/" + $(this).val();
				
				$.ajax({
					url : url,
					method : "get",
					success : function(data) {
					$("#crm").val(data.crm);
					$("#especialidade").val(data.especialidade);	
						
					},
				});
			});
		});

