$(document).ready(function() {
	$('#data').focusout( function(){
		var arrayDataAgendamento= $('#data').val().split('-');
		var dataColeta = new Date();
		dataColeta.setDate(arrayDataAgendamento[2]); dataColeta.setMonth(arrayDataAgendamento[1] - 1); dataColeta.setFullYear(arrayDataAgendamento[0]);
		
		var dataEnvio = new Date();
		
		
		
		if(dataColeta.getTime() < dataEnvio.getTime()){
			
			alert("Data informada invalida!");
			$('#data').val('');
		}
	});
		

});
	
	
	
	/*dataEnvio.setDate(arrayDataEnvio[2]); dataEnvio.setMonth(arrayDataEnvio[1] - 1); dataEnvio.setFullYear(arrayDataEnvio[0]);
	
	if(dataEnvio.getTime() < dataColeta.getTime()) {
		alert("Data inválida!!\nA Data de Envio deve ser igual ou maior que a Data de Coleta");
		return false;
	}
	else if($("#data_retorno").val() != '') {
		var arrayDataRetorno = $("#data_retorno").val().split('-');
		var dataRetorno = new Date();
		dataRetorno.setDate(arrayDataRetorno[2]); dataRetorno.setMonth(arrayDataRetorno[1] - 1); dataRetorno.setFullYear(arrayDataRetorno[0]);
		
		if(dataRetorno.getTime() < dataEnvio.getTime()) {
			alert("Data inválida!!\n A Data de Retorno deve ser maior que a Data de Envio");
			return false;
		}
	}
	form.submit();*/
