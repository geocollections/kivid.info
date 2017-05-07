$(function() {
  var rockId = $('body').attr('data-has-map');
  if (rockId !== undefined) {

    var olMap = new ol.Map({
      target: 'map',
      layers: [
        new ol.layer.Tile({
          source: new ol.source.XYZ({
            url: 'https://api.tiles.mapbox.com/v4/mapbox.light/{z}/{x}/{y}.png?access_token=pk.eyJ1Ijoia3V1dG9iaW5lIiwiYSI6ImNpZWlxdXAzcjAwM2Nzd204enJvN2NieXYifQ.tp6-mmPsr95hfIWu3ASz2w'
          })
        })
      ],
      view: new ol.View({
        center: ol.proj.fromLonLat([25.0136, 58.5953]),
        zoom: $('.js-location-context').hasClass('js-in-estonia') ? 7 : 2,
        maxZoom: 16,
        minZoom: 2
      })
    });

    function defaultStyle(feature, resolution) {
      return [
        new ol.style.Style({
          image: new ol.style.Circle({
            radius: 5,
            fill: new ol.style.Fill({color: 'rgba(236, 102, 37, 0.7)', opacity: 0.9})
          }),
          text: (resolution > 500 ? null : new ol.style.Text({
            font: '10pt Arial, sans-serif',
            text: feature.name,
            fill: new ol.style.Fill({color: 'rgba(236, 102, 37, 0.7)'}),
            textAlign: 'left',
            textBaseline: 'bottom',
            offsetX: 5,
            offsetY: -5
          }))
        })
      ]
    }

    var vectorSource = new ol.source.Vector({
      attributions: [new ol.Attribution({
        html: " "
      })]
    });

    $.getJSON('/rock/' + rockId + '/locations', function (locations) {
      if (locations.length) {
        for (var i = 0; i < locations.length; i++) {
          var centroidLL = ol.proj.transform([Number(locations[i].locality__longitude), Number(locations[i].locality__latitude)], 'EPSG:4326', 'EPSG:3857');
          var centroidPoint = new ol.geom.Point(centroidLL);
          var feature = new ol.Feature({geometry: centroidPoint});
          feature.name = locations[i].locality__locality;
          feature.fid = locations[i].id;
          vectorSource.addFeature(feature);
        }
      }

    }).complete(function() {
      var layerData = new ol.layer.Vector({
        title: "Localities",
        source: vectorSource,
        style: function (feature, resolution) {
          return defaultStyle(feature, resolution);
        }
      });

      olMap.addLayer(layerData);
    });
  }
});