$(document).ready(function() {
	$(".descricao").each(function() {
		var html = $(this).text();
		if(html.length > 20)
			$(this).text(html.substring(0, 20) + '....');
	});
});