$(document).ready(function() {
			$("#convenio_id").change(function() {
				var url = "http://localhost:8080/agenda/procedimento/" + $(this).val();
				
				$.ajax({	
					url : url,
					method : "get",
					success : function(data) {
						$("#procedimento_id").prop("disabled", false);
						var html = '<option value="" hidden>Selecione</option>';
						for (var i = 0; i < data.length; i++) {
							html += '<option value=' + data[i].id + '>' + data[i].descricao + '</option>';
							
						}
						$("#procedimento_id").html(html);
					},
				});
			});
		});