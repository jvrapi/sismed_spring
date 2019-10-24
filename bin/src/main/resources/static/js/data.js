var now = new Date();
var mName = now.getMonth() +1 ;
var dayNr = now.getDate();
var yearNr=now.getYear();
if(mName==1){Month = "Janeiro";}
if(mName==2){Month = "Fevereiro";}
if(mName==3){Month = "Março";}
if(mName==4){Month = "Abril";}
if(mName==5){Month = "Maio";}
if(mName==6){Month = "Junho";}
if(mName==7){Month = "Julho";}
if(mName==8){Month = "Agosto";}
if(mName==9){Month = "Setembro";}
if(mName==10){Month = "Outubro";}
if(mName==11){Month = "Novembro";}
if(mName==12){Month = "Dezembro";}
if(yearNr < 2000) {Year = 1900 + yearNr;}
else {Year = yearNr;}
var todaysDate =(dayNr + " " + Month + " " + Year);
document.write('  '+todaysDate);
