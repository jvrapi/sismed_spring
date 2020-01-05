$(document).ready(function() {
		$('#procedimento_id').change(function() {
			
			var url = "http://localhost:8080/agenda/valor/" + $(this).val();
			
			$.ajax({
				
				url : url,
				method : "get",
				
				success : function(data) {
					$("#valor").val("R$ " + data.valor + ".00") ;
					
				},
			});
		});
	});