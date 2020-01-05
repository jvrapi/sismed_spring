$(document).ready(function() {
    $("#convenio_id").change(function() {
        var url = "http://localhost:8080/laboratorio/convenio/" + $(this).val() + "/" + $("#id").val();
        $.ajax({
            url: url,
            method: "get",
            success: function(data) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value=' + data[i].id + '>' + data[i].nome + '</option>';
                }
                $("#tipo_convenio_id").html(html);
            },
        });
    });

    $("#allconvenio_id").change(function() {
        var url = "http://localhost:8080/laboratorio/allconvenios/" + $(this).val() + "/" + $("#id").val();
        $.ajax({
            url: url,
            method: "get",
            success: function(data) {
                $("#alltipo_convenio_id").prop("disabled", false);
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value=' + data[i].id + '>' + data[i].nome + '</option>';
                }
                $("#alltipo_convenio_id").html(html);
            },
        });
    });

    $("#excluir").click(function() {
        url = "http://localhost:8080/laboratorio/excluirTConv/" + $('#tipo_convenio_id option:selected').val() + "/" + $("#id").val();
        $.ajax({
            url: url,
            method: "get",
            success: function(data) {
                $('#tipo_convenio_id option:selected').remove();
                alert("Excluido com sucesso");
                if ($('#tipo_convenio_id option:selected').val() == null) {
                    $('#convenio_id option:selected').remove();
                }
            }
        });

    });
});