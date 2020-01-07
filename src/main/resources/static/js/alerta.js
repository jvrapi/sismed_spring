function selector() {
  $(".alert").stop().fadeTo(1, 1).removeClass('hidden');
  window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){
        $(".alert").addClass('hidden');
    });
  }, 1000); 
}