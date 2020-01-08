$(document).ready(function() {
	if($("#success-alert").alert("show")) {
		$("#success-alert").fadeTo(2000, 500).slideUp(400, function() {
			$("#success-alert").slideUp(500);
		});
	}
	if($("#fail-alert").alert("show")) {
		$("#fail-alert").fadeTo(2000, 500).slideUp(400, function() {
			$("#fail-alert").slideUp(500);
		});
	}
});