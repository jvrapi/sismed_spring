$(document).ready(function() {	
	
$('.pass').keyup(function(){
		$('#senha1').val() === $('#senha2').val() ? $('#btnSenha').removeAttr('disabled') : $('#btnSenha').attr('disabled', 'disabled');
	});


	
});