$(document).ready(function() {
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/exame/buscar/2",
			minLength: 1,
			select: function (event, ui) {
				$("#id_paciente").val(ui.item.value)
				return false;
			}
		});
	});
});
	
function muda3() {
	$("#divBusca").css("width", "405px");
	$("#txtBusca").css("display", "");
	$("#coleta_data").css("display", "none");
	$('#txtBusca').unmask();
	$("#dropdownMenu2").text("Exame");
	$("#dropdownMenu2").val("3");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/exame/buscar/3",
			minLength: 2,
			select: function (event, ui) {
				$("#nome_exame").val(ui.item.label)
				return false;
			}
		});
	});
}

function muda2() {
	$("#divBusca").css("width", "405px");
	$("#txtBusca").css("display", "");
	$("#coleta_data").css("display", "none");
	$('#txtBusca').unmask();
	$("#dropdownMenu2").text("Paciente");
	$("#dropdownMenu2").val("2");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/exame/buscar/2",
			minLength: 2,
			select: function (event, ui) {
				
				return false;
			}
		});
	});
}

function muda4() {
	$("#divBusca").css("width", "220px");
	$("#txtBusca").css("display", "none");
	$("#coleta_data").css("display", "");
	$('#txtBusca').unmask();
	$("#dropdownMenu2").text("Data de Coleta");
	$("#dropdownMenu2").val("4");
	$( function() {
		$("#txtBusca").autocomplete({
			source: "http://localhost:8080/exame/buscar/4",
			minLength: 2,
			select: function (event, ui) {
				
				return false;
			}
		});
	});
}

$(function() {
    $("#paciente").autocomplete({
        source: "http://localhost:8080/exame/buscarpaciente",
        minLength: 1,
        select: function(event, ui) {
            $("#paciente").val(ui.item.label);
            $("#paciente_id").val(ui.item.value);
            //nome do convenio
            $("#convenio").val(ui.item.nome_convenio);

            //nome e id do tipo de convenio
            $("#tipo").val(ui.item.nome_tipo);
            $("#tipo_id").val(ui.item.tipo);
            var url = "http://localhost:8080/exame/buscarlab/" + $("#tipo_id").val();
            $.ajax({
                url: url,
                method: "get",
                success: function(data) {
                    if (data.flag == 0) {
                        $("#convenio").val("Particular");
                        $("#tipo").val("Particular");
                        $("#tipo_id").val(1);
                        var html = '';
                        $("#laboratorio").prop("disabled", false);
                        var html = '<option value="" hidden>Selecione</option>';
                        for (var i = 0; i < data.listLab.length; i++) {
                            html += '<option value=' + data.listLab[i].id + '>' + data.listLab[i].nome + '</option>';
                        }
                    } else {
                        var html = '';
                        $("#laboratorio").prop("disabled", false);
                        var html = '<option value="" hidden>Selecione</option>';
                        for (var i = 0; i < data.listLab.length; i++) {
                            html += '<option value=' + data.listLab[i].id + '>' + data.listLab[i].nome + '</option>';
                        }
                    }
                    $("#laboratorio").html(html);
                }
            });
            return false;
        }
    });
});

$(function() {
    $("#funcionario").autocomplete({
        source: "http://localhost:8080/exame/buscarfuncionario",
        minLength: 2,
        select: function(event, ui) {
            $("#funcionario").val(ui.item.label);
            $("#funcionario_id").val(ui.item.value);
            return false;
        }
    });
});