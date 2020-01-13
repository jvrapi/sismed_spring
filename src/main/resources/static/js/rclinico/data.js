$(document).ready(function() {
	var now = new Date();
	var dia = now.getDate(); if(dia < 10) dia = "0" + dia;
	var mes = now.getMonth() +1; if(mes < 10) mes = "0" + mes;			
	var ano = now.getFullYear();
	$("#datahoje").val(ano + "-" + mes + "-" + dia);
	
});