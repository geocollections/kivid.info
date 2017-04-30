$(function() {

  $("#rockSearch").autocomplete({
    minLength: 3,
    source: function(request, response) {
      $.getJSON('/rock/search/' + request.term, function (results) {
        var data = [];
        results.forEach(function(result) {
          var label = result.name;
          if ($('#languageButton').text() === 'en') {
            label = result.name_en;
          }
          data.push({label: label, id: result.id});
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
