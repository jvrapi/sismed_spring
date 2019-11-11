function ApenasLetras(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        } else if (e) {
            var charCode = e.which;
        } else {
            return true;
        }
        if ( charCode == 32 ||
            (charCode > 64 && charCode < 91) || 
            (charCode > 96 && charCode < 123) ||
            (charCode > 191 && charCode <= 255) // letras com acentos
            ){
            return true;
    } else {
        alert("Esse campo não permite esse caractere");
        return false;
    }
} catch (err) {
    alert(err.Description);
}
}

/* 
 * CHAMADA DO METODO
 * onkeypress="return ApenasLetras(event,this);" 
 * 
 * 
 */*/ 