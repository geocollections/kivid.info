$(function() {
  $(document).on('click', '.js-set-location-context', function(event) {
    event.preventDefault();
    $('.js-set-location-context').parent().removeClass('active');
    $(this).parent().addClass('active');
    if ($(this).data('inEstonia')) {
      $.post("/setLocationContext?inEstonia=true");
    } else {
      $.post("/setLocationContext?inEstonia=false");
    }
  });
});
