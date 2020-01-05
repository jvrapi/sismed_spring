function validaCadastro() {
	var check;
	$("#formCadastroConvFunc").find("input:checkbox").each(function() {
    	if($(this).prop("checked") == true) {
    		check = true;
    	}
    });
	if(check) $("#btn_cadastrar").prop("disabled", false);
	else $("#btn_cadastrar").prop("disabled", true);
}

function validaExcluir() {
	var check;
	$("#formExcluirConvFunc").find("input:checkbox").each(function() {
    	if($(this).prop("checked") == true) {
    		check = true;
    	}
    });
	if(check) $("#btn_excluir").prop("disabled", false);
	else $("#btn_excluir").prop("disabled", true);
}