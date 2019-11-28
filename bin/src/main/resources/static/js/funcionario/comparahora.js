
function liberar()
{
var data_coleta = document.getElementById("data_coleta");	
var data_envio= document.getElementById("data_envio");

alert(data_coleta);

if(data_coleta.value == data_envio)
{
	data_envio.disabled=false;
}
	}
