$(document).ready(function() {
			$("#funcionario_id").change(function() {
				var url = "http://localhost:8080/agenda/funcionario/" + $(this).val();
				alert(url);
				$.ajax({
					url : url,
					method : "get",
					success : function(data) {
						
						
						
					},
				});
			});
		});

