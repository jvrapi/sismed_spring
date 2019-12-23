
		$(document).ready(function() {
			var url = "http://localhost:8080/procedimentos/excluir/" + $(".classid").attr('id') + "/" + $(".convenioid").attr('id');
			
			$('#ok_confirm').click(function() {
				document.location.href = url;
			}); 
		});
		