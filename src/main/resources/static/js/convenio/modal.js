
		$(document).ready(function() {
			var url = "http://localhost:8080/convenios/excluir/" + $(".classid").attr('id');
			$('#ok_confirm').click(function() {
				document.location.href = url;
			}); 
		});
		