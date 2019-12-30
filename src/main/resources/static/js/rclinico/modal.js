$(document).ready(function() {
	$('#ok_confirm').click(function() {
		document.location.href = "http://localhost:8080/RegistroClinico/excluir/" + $("#recipient-id").val() + "/" + $("#recipient-pacienteid").val();
	}); 
});
