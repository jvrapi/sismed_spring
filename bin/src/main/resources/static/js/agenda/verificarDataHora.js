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
	
	
	
	