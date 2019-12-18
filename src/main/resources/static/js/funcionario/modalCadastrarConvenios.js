$(document).ready(function() {
    $("#allconvenio_id").change(function() {
        var url = "http://localhost:8080/funcionario/allconvenios/" + $(this).val() + "/" + $("#id").val();
        $.ajax({
            url: url,
            method: "get",
            success: function(data) {
                var html = '';
                if(data.length == 0){
               		html += '<span>Todos já cadastrados</span>';
               	}
                else {
                    for (var i = 0; i < data.length; i++) {
                    	html += '<li>';
                    	html += '<label class"custom-control custom-checkbox">';
                        html += '<input class="custom-control-input" type="checkbox" id=' + data[i].nome + ' value=' + data[i].id + ' name="tconvenio">';
                        html += '<span class="custom-control-label" for=' + data[i].nome + '> '+ data[i].nome + '</span>';
                        html += '</label>';
                        html += '</li>';
                    }
                }
                $("#teste").html(html);
            },
        });
    });
});