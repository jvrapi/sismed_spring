$(document).ready(function() {
    $("#convenio_id").change(function() {
        var url = "http://localhost:8080/funcionario/convenio/" + $(this).val() + "/" + $("#id").val();
        $.ajax({
            url: url,
            method: "get",
            success: function(data) {
                var html = '';
               	if(data.length == 0){
               		html += '<span>Nenhum cadastrado</span>';
               		$("#btn_excluir").prop("disabled", true);
               	}
               	else {
                    for (var i = 0; i < data.length; i++) {
                    	$("#btn_excluir").prop("disabled", false);
                    	html += '<li>';
                    	html += '<label class"custom-control custom-checkbox">';
                        html += '<input class="custom-control-input" type="checkbox" id=' + data[i].nome + ' value=' + data[i].id + ' name="tconvenio">';
                        html += '<span class="custom-control-label" for=' + data[i].nome + '> '+ data[i].nome + '</span>';
                       	html += '</label>';
                        html += '</li>';
                  	}
               	}
                $("#teste2").html(html);
            },
        });
    });
});
