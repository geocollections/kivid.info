$(function() {

  $("#rockSearch").autocomplete({
    minLength: 3,
    source: function(request, response) {
      $.getJSON('/rock/search/' + request.term, function (results) {
        var data = [];
        results.forEach(function(result) {
          data.push({label: result.name, id: result.id});
        });
        response(data);
      });
    },
    select: function(event, ui) {
      window.location = "/rock/" + ui.item.id;
    }
  });

  $(".clickableTable .clickableRow").click(function() {
    window.location = $(this).attr("href");
  });
});
