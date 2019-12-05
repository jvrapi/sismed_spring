/*function verificaData(){
     var data = document.getElementById('data_coletas').value;
 
     if (isDataValida(data, 30)){
	
     }else{
	alert('Preencha a Data de Coleta');
     }
}
 
 Valida se a data passada como parâmetro está dentro do período informado 
function isDataValida(data, periodo){
    var arrayData = data.split('/');
    var campoDia = parseInt(arrayData[0]); 
    var campoMes = parseInt(arrayData[1]); 
    var campAno = parseInt(arrayData[2]);
 
    var dataUsuario = new Date();
    dataUsuario.setDate(campoDia);
    dataUsuario.setMonth(campoMes -1);
    dataUsuario.setFullYear(campAno);
 
    var dataLimite = new Date();
    dataLimite.setDate(dataLimite.getDate() + periodo);
 
    if(dataUsuario.getTime() <= dataLimite.getTime()){
    	return true;
    }else{
    	return false;
    }
}*/
function validaData(form) {
	var arrayDataColeta = $("#data_coletas").val().split('-');
	var dataColeta = new Date();
	dataColeta.setDate(arrayDataColeta[2]); dataColeta.setMonth(arrayDataColeta[1] - 1); dataColeta.setFullYear(arrayDataColeta[0]);
	
	var arrayDataEnvio = $("#data_envio").val().split('-');
	var dataEnvio = new Date();
	dataEnvio.setDate(arrayDataEnvio[2]); dataEnvio.setMonth(arrayDataEnvio[1] - 1); dataEnvio.setFullYear(arrayDataEnvio[0]);
	
	if(dataEnvio.getTime() < dataColeta.getTime()) {
		alert("Data inválida\nA data de envio deve ser menor que a data de coleta");
		return false;
	}
	else if($("#data_retorno").val() != '') {
		var arrayDataRetorno = $("#data_retorno").val().split('-');
		var dataRetorno = new Date();
		dataRetorno.setDate(arrayDataRetorno[2]); dataRetorno.setMonth(arrayDataRetorno[1] - 1); dataRetorno.setFullYear(arrayDataRetorno[0]);
		
		if(dataRetorno.getTime() < dataEnvio.getTime()) {
			alert("Data inválida\nA data de retorno deve ser menor que a data de envio");
			return false;
		}
	}
	form.submit();
}