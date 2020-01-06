 $(document).ready(function(){
        	
            $("#valor").mask('#.##0.00', {reverse: true});
            $('#cnpj').mask('00.000.000/0000-00');
            $('#cpf').mask('000.000.000-00');
            $('#rg').mask('00.000.000-0');
            $('#telefone_fixo').mask('(00) 0000-0000');   
            $('#telefone_trabalho').mask('(00) 0000-0000');    
            $('#telefone').mask('(00) 0000-0000');   
        	$('#celular').mask('(00) 9 0000-0000');      
        	$('#crm').mask('00000000');   
        	$('#banco').mask('000');
        	$('#agencia').mask('0000'); 
        	$('#conta').mask('00000-0');
        	$('#ans').mask('000000');
        	$('#cep').mask('00000-000');
        	
        });