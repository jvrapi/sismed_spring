$(document).on("input", "#nome", function () {
    var limite = 50;
    var caracteresDigitados = $(this).val().length;
    var caracteresRestantes = limite - caracteresDigitados;
    $(".caracteres").text(caracteresRestantes);
});