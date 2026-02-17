require([
    "esri/Map",
    "esri/views/MapView",
    "esri/widgets/Locate",
    "esri/widgets/Fullscreen",
    "esri/Graphic"
], function (Map, MapView, Locate, Fullscreen, Graphic) {

    let markerCount = 0;

    const map = new Map({
        basemap: "streets-navigation-vector"
    });

    const view = new MapView({
        container: "map",
        map: map,
        center: [0, 20],
        zoom: 3
    });

    // Locate widget
    const locate = new Locate({ view: view });
    view.ui.add(locate, "top-left");

    // Fullscreen widget
    const fullscreen = new Fullscreen({ view: view });
    view.ui.add(fullscreen, "top-right");

    // Update zoom display
    view.watch("zoom", function (newZoom) {
        document.getElementById("currentZoom").textContent = Math.round(newZoom);
    });

    // Update center coordinates
    view.watch("center", function (center) {
        document.getElementById("currentCoords").textContent =
            center.latitude.toFixed(2) + ", " + center.longitude.toFixed(2);
    });

    // Click to add marker
    view.on("click", function (event) {

        const pointGraphic = new Graphic({
            geometry: event.mapPoint,
            symbol: {
                type: "simple-marker",
                color: "#3b82f6",
                size: "12px",
                outline: {
                    color: "#ffffff",
                    width: 1
                }
            },
            popupTemplate: {
                title: "Location Details",
                content:
                    "<b>Latitude:</b> " + event.mapPoint.latitude.toFixed(6) + "<br>" +
                    "<b>Longitude:</b> " + event.mapPoint.longitude.toFixed(6)
            }
        });

        view.graphics.add(pointGraphic);

        markerCount++;
        document.getElementById("markerCount").textContent = markerCount;

        view.popup.open({
            features: [pointGraphic],
            location: event.mapPoint
        });
    });

    // Expose focus function globally
    window.focusOnMap = function () {
        document.getElementById("explore").scrollIntoView({
            behavior: "smooth"
        });
    };

});
