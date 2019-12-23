/*função para listar os convenios que os medicos aceitam*/

$(document).ready(function() {
			$("#funcionario_id").change(function() {
				//Pega o valor do id do funcinario selecionado e passa para o mapeamento no controller de agenda
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
			
			
			
		});

