function relogio(){
			var data = new Date();
			var horas = data.getHours();
			var minutos = data.getMinutes();
			var segundos = data.getSeconds();
			
			/* coloca o 0 na frente do numero caso ele seja menor que 10*/
			if(horas < 10){
				horas = "0" + horas;
			}
			if(minutos < 10){
				minutos = "0" + minutos;
			}
			if(segundos < 10){
				segundos = "0" + segundos;
			}
			document.getElementById("relogio").innerHTML=horas+":"+minutos+":"+segundos;
		}
		window.setInterval("relogio()",1000)