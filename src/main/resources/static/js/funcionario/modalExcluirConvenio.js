$(document).ready(function() {
	$("#btn_excluir").prop("disabled", true);
    $("#convenio_id").change(function() {
    	$("#btn_excluir").prop("disabled", true);
        var url = "http://localhost:8080/sismed/funcionario/convenio/" + $(this).val() + "/" + $("#id").val();
        $.ajax({
            url: url,
            method: "get",
            success: function(data) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                	html += '<li>';
                	html += '<label class"custom-control custom-checkbox">';
                    html += '<input class="custom-control-input" type="checkbox" id=' + data[i].nome + ' value=' + data[i].id + ' name="tconvenio" onClick="validaExcluir()">';
                    html += '<span class="custom-control-label" for=' + data[i].nome + '> '+ data[i].nome + '</span>';
                   	html += '</label>';
                    html += '</li>';
              	}
                $("#teste2").html(html);
            },
        });
    });
});
