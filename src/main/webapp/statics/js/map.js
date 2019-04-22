

var load = function(){
    if($("#lng").val() == "") {
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function (r) {
            console.log(r);
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                var mk = new BMap.Marker(r.point);
                $("#lng").val(r.point.lng);
                $('#lat').val(r.point.lat);
            } else {
                alert('failed' + this.getStatus());
            }
        }, {enableHighAccuracy: true})
    }
}