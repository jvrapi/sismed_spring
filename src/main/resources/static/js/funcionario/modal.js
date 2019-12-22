$(document).ready(function() {
	var url = "http://localhost:8080/sismed/funcionario/excluir/" + $(".classid").attr('id');
	$('#ok_confirm').click(function() {
		document.location.href = url;
	}); 
});