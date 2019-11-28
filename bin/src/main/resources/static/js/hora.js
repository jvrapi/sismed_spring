var Elem = document.getElementById("Clock");
function Horario(){ 
    var Hoje = new Date(); 
    var Horas = Hoje.getHours(); 
    if(Horas < 10){ 
        Horas = "0"+Horas; 
    } 
    var Minutos = Hoje.getMinutes(); 
    if(Minutos < 10){ 
        Minutos = "0"+Minutos; 
    } 
    var Segundos = Hoje.getSeconds(); 
    if(Segundos < 10){ 
        Segundos = "0"+Segundos; 
    } 
    Elem.innerHTML = Horas+":"+Minutos+":"+Segundos; 
} 
window.setInterval("Horario()",1000);
var Elem = document.getElementById("Clock");
function Horario(){ 
    var Hoje = new Date(); 
    var Horas = Hoje.getHours(); 
    if(Horas < 10){ 
        Horas = "0"+Horas; 
    } 
    var Minutos = Hoje.getMinutes(); 
    if(Minutos < 10){ 
        Minutos = "0"+Minutos; 
    } 
    var Segundos = Hoje.getSeconds(); 
    if(Segundos < 10){ 
        Segundos = "0"+Segundos; 
    } 
    Elem.innerHTML = Horas+":"+Minutos+":"+Segundos; 
} 
window.setInterval("Horario()",60000);