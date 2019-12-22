$(document).ready(function() {
    var url = "http://localhost:8080/sismed/agenda/agendamentosAnteriores/" + $("#prontuario").val();
    $("#resultsBlock").load(url);
    });

function mudaPágina(element) {
    var url = "http://localhost:8080/sismed/agenda/agendamentosAnteriores/" +  $("#prontuario").val() + "?page=" + element.text;
    $("#resultsBlock").load(url);
}
function primeiraPagina() {
    var url = "http://localhost:8080/sismed/agenda/agendamentosAnteriores/" +  $("#prontuario").val() + "?page=1";
    $("#resultsBlock").load(url);
}
function ultimaPagina() {
    var url = "http://localhost:8080/sismed/agenda/agendamentosAnteriores/" +  $("#prontuario").val() + "?page=" + $("#ultimaPagina").val();
    $("#resultsBlock").load(url);
}