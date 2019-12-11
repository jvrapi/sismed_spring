$(document).ready(function() {
			$("#funcionario_id").change(function() {
				var url = "http://localhost:8080/agenda/funcionarioConvenio/" + $(this).val();
				
				$.ajax({
					url : url,
					method : "get",
					success : function(data) {
						$("#convenio_id").prop("disabled", false);
						var html = '<option value="" hidden>Selecione</option>';
						for (var i = 0; i < data.length; i++) {
							html += '<option value=' + data[i].id + '>' + data[i].nome + '</option>';
						}
						$("#convenio_id").html(html);
					},
				});
			});
			
			/*
			//pegar as informações de funcionario
			$("#funcionario_id").change(function() {
				var url = "http://localhost:8080/agenda/funcionario/" + $(this).val();"
				$.ajax({
					url : url,
					method : "get",
					success : function(data) {
						$("#convenio_id").prop("disabled", false);
						var html = '<option value="" hidden>Selecione</option>';
						for (var i = 0; i < data.length; i++) {
							html += '<option value=' + data[i].id + '>' + data[i].nome + '</option>';
						}
						$("#convenio_id").html(html);
					},
				});
			});*/
			
		});

