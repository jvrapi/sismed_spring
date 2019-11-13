function valida() {
			if($("#funcionario_radio").prop("checked") == true) {
				$("#especialidade").prop("disabled", true);
				$("#especialidade").val("");
				$("#crm").prop("disabled", true);
				$("#crm").val("");
			} else {
				$("#especialidade").prop("disabled", false);
				$("#crm").prop("disabled", false);
			}
		}