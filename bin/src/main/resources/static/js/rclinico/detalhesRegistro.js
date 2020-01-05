$(document).ready(function(){
  $("#modalDetalhesRegistro").on('show.bs.modal', function(event){
	var recipientdata = new Date();
    var button = $(event.relatedTarget)
    var recipientid = button.data('id');
	var recipientnome = button.data('nome');
	var recipientdescricao = button.data('descricao');
	var recipienthora = button.data('hora');
	recipientdata = button.data('data');
	var recipientbool = button.data('bool');
	var recientpacienteid = button.data('pacienteid');
	var recipientfuncionarioid = button.data('funcionarioid');
	var recipientagendamento = button.data('agendamento');
	var modal = $(this);
	modal.find("#recipient-id").val(recipientid);
	modal.find('#recipient-name').val(recipientnome);
	modal.find('#recipient-descricao').val(recipientdescricao);
	modal.find('#recipient-hora').val(recipienthora);
	modal.find('#recipient-data').val(recipientdata);
	modal.find("#recipient-bool").val(recipientbool);
	modal.find("#recipient-pacienteid").val(recientpacienteid);
	modal.find("#recipient-funcionarioid").val(recipientfuncionarioid);
	modal.find("#recipient-agendamento").val(recipientagendamento);
  });
});
