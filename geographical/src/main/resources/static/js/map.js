require([
    "esri/Map",
    "esri/views/MapView",
    "esri/widgets/Locate",
    "esri/widgets/Fullscreen",
    "esri/Graphic"
], function (Map, MapView, Locate, Fullscreen, Graphic) {

    let markerCount = 0;
    let mapView = null;

    const map = new Map({
        basemap: "dark-gray-vector"
    });

    const view = new MapView({
        container: "map",
        map: map,
        center: [0, 20],
        zoom: 3
    });

    mapView = view;

    // Locate widget
    const locate = new Locate({ view: view });
    view.ui.add(locate, "top-left");

    // Fullscreen widget
    const fullscreen = new Fullscreen({ view: view });
    view.ui.add(fullscreen, "top-left");

    // Update zoom display
    view.watch("zoom", function (newZoom) {
        const elem = document.getElementById("currentZoom");
        if (elem) {
            elem.textContent = Math.round(newZoom);
        }
    });

    // Update center coordinates
    view.watch("center", function (center) {
        const elem = document.getElementById("currentCoords");
        if (elem) {
            elem.textContent = center.latitude.toFixed(2) + ", " + center.longitude.toFixed(2);
        }
    });

    // Click to add marker
    view.on("click", function (event) {
        const pointGraphic = new Graphic({
            geometry: event.mapPoint,
            symbol: {
                type: "simple-marker",
                color: "#8b5cf6",
                size: "14px",
                outline: {
                    color: "#ffffff",
                    width: 2
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

        const markerElem = document.getElementById("markerCount");
        if (markerElem) {
            markerElem.textContent = markerCount;
        }

        view.popup.open({
            features: [pointGraphic],
            location: event.mapPoint
        });
    });

    // Expose functions globally
    window.focusOnMap = function () {
        const elem = document.getElementById("explore");
        if (elem) {
            elem.scrollIntoView({ behavior: "smooth" });
        }
    };

    window.resetMapView = function () {
        if (mapView) {
            mapView.goTo({
                center: [0, 20],
                zoom: 3
            });
        }
    };

    window.zoomIn = function () {
        if (mapView) {
            mapView.goTo({
                zoom: mapView.zoom + 1
            });
        }
    };

    window.zoomOut = function () {
        if (mapView) {
            mapView.goTo({
                zoom: mapView.zoom - 1
            });
        }
    };
});