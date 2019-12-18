$(document).ready(function(){
  $("#modalDetalhesRegistro").on('show.bs.modal', function(event){
    var button = $(event.relatedTarget)
	var recipientnome = button.data('nome');
	var recipientdescricao = button.data('descricao');
	var recipienthora = button.data('hora');
	var recipientdata = button.data('data');
	var modal = $(this);
	modal.find('#recipient-name').val(recipientnome);
	modal.find('#recipient-descricao').val(recipientdescricao);
	modal.find('#recipient-hora').val(recipienthora);
	modal.find('#recipient-data').val(recipientdata);
  });
});
