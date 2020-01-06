window.onload = function(){
	var dataEnvio = document.getElementById("data_envio");
	var dataRetorno = document.getElementById("data_retorno");
	dataEnvio.onchange = doValidate;
	dataRetorno.onchange = doValidate
}

function validaDataEnvio() {
	var arrayDataColeta = $("#data_coletas").val().split('-');
	var dataColeta = new Date();
	dataColeta.setDate(arrayDataColeta[2]); dataColeta.setMonth(arrayDataColeta[1] - 1); dataColeta.setFullYear(arrayDataColeta[0]);
	
	var arrayDataEnvio = $("#data_envio").val().split('-');
	var dataEnvio = new Date();
	dataEnvio.setDate(arrayDataEnvio[2]); dataEnvio.setMonth(arrayDataEnvio[1] - 1); dataEnvio.setFullYear(arrayDataEnvio[0]);
	
	if(dataEnvio.getTime() < dataColeta.getTime()) {
		return false;
	}
	return true;
}

function validaDataRetorno() {
	if($("#data_retorno").val() != '') {
		var arrayDataEnvio = $("#data_envio").val().split('-');
		var dataEnvio = new Date();
		dataEnvio.setDate(arrayDataEnvio[2]); dataEnvio.setMonth(arrayDataEnvio[1] - 1); dataEnvio.setFullYear(arrayDataEnvio[0]);
		var arrayDataRetorno = $("#data_retorno").val().split('-');
		var dataRetorno = new Date();
		dataRetorno.setDate(arrayDataRetorno[2]); dataRetorno.setMonth(arrayDataRetorno[1] - 1); dataRetorno.setFullYear(arrayDataRetorno[0]);
		
		if(dataRetorno.getTime() < dataEnvio.getTime()) {
			return false;
		}
	}
	return true;
}

function doValidate() {
	var dataEnvio = document.getElementById("data_envio");
	var dataRetorno = document.getElementById("data_retorno");
	
	if(!validaDataEnvio()) {
		dataEnvio.setCustomValidity("Data Inválida!");
	}
	else {
		dataEnvio.setCustomValidity("");
	}
	
	if(!validaDataRetorno()) {
		dataRetorno.setCustomValidity("Data Inválida!");
	}
	else {
		dataRetorno.setCustomValidity("");
	}
}