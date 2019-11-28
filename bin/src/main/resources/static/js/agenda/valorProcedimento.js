$(document).ready(function() {
			$("#procedimento_id").change(function() {
			var procedimento = $("#procedimento_id").val();
			var url = "http://localhost:8080/agenda/procedimento/" + procedimento;
			$.ajax({	
				url : url,
				method : "get",
				success : function(data) {
					
					for (var i = 0; i < data.length; i++) {
						var valor = data[i].valor + ".00";
						$("#valor").val(valor);
					}
					
				},
			});
				
			});
		});