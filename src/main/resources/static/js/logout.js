$(document).ready(function(){
	$('a[logout-confirm]').click(function(ev){
		var href = $(this).attr('href');
		if(!$('#confirm-logout').length){
			$('body').append('<div class="modal fade" id="confirm-logout" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"><div class="modal-dialog" role="document"><div class="modal-content"><div class="modal-header bg-danger text-white">ENCERRAR SESSÃO<button type="button" class="close" logout-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button></div><div class="modal-body">Deseja realmente sair?</div><div class="modal-footer"><button type="button" class="btn btn-success" data-dismiss="modal">Cancelar</button><a  class="btn btn-danger text-white" id= "logoutConfirmOk">Sair</a></div></div></div></div>');
		}
		$('#logoutConfirmOk').attr('href',href)/
		$('#confirm-logout').modal({shown:true});
		return false;
	});
});