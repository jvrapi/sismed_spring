$(document).ready(function() {
			$("#convenio_id").change(function() {
				var url = "http://localhost:8080/agenda/convenio/" + $(this).val();
				
				$.ajax({
					url : url,
					method : "get",
					success : function(data) {
						$("#tipo_convenio_id").prop("disabled", false);
						var html = '<option value="" hidden>Selecione</option>';
						for (var i = 0; i < data.length; i++) {
							html += '<option value=' + data[i].id + '>' + data[i].nome + '</option>';
							
						}
						$("#tipo_convenio_id").html(html);
						
					},
				});
			});
		});

