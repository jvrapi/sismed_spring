$(document).ready(time);
function time(){
	today=new Date();
	hora = today.getHours();
	if(hora < 10) hora = "0"+hora;
	minuto = today.getMinutes();
	if(minuto < 10) minuto = "0"+minuto;
	document.getElementById('hora').value=hora+":"+minuto;
	setTimeout('time()',1000);
}