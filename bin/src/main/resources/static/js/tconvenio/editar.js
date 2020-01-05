
	
			function executar(botao) {
				$(botao).remove();
				$(".classid").remove();
				$("#div_botao").append("<button class='btn btn-outline-primary float-right' id='btn_submit' type='submit'>Salvar</button>");
				$("#div_botao").append("<button class='btn btn-outline-secondary mr-2 float-right' id='btn_cancelar' style='margin-left: 10px;' type='button' onclick='voltar(this)'>Cancelar</button>");
				$("input").prop("disabled", false);
				$("select").prop("disabled", false);
				$('#form').find(':input').each(function(i, elem) {
				      $(this).data("previous-value", $(this).val());
				});
			}
		
			function voltar(botao) {
				$(botao).remove();
				$("#btn_submit").remove();
				$("#div_botao").append("<button type='button' class='btn btn-outline-danger classid float-right' data-toggle='modal' data-target='#myModal'>Excluir</button>");
				$("#div_botao").append("<button class='btn btn-outline-primary mr-2 float-right' id='btn_editar' style='margin-left: 10px;' type='button' onclick='executar(this)'>Editar</button>");
				$("input").prop("disabled", true);
				$("select").prop("disabled", true);
				$('#form').find(':input').each(function(i, elem) {
			        $(this).val($(this).data("previous-value"));
			    });
			}
		
		