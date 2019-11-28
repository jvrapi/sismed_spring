function verificaData(){
     var data = document.getElementById('data').value;
 
     if (isDataValida(data, 30)){
	
     }else{
	alert(Preencha a Data de Coleta');
     }
}
 
/* Valida se a data passada como parâmetro está dentro do período informado */
function isDataValida(data, periodo){
    var arrayData = data.split('/');
    var campoDia = parseInt(arrayData[]); 
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
}