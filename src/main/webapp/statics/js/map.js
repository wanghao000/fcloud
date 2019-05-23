
var position = function(){
    if ($("#lng").val() == "") {
        AMap.plugin('AMap.Geolocation', function() {
            var geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 10000,          //超过10秒后停止定位，默认：5s
                buttonPosition:'RB',    //定位按钮的停靠位置
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true,   //定位成功后是否自动调整地图视野到定位点

            });
            geolocation.getCurrentPosition(function(status,result){
                if(status=='complete'){
                    onComplete(result)
                }else{
                    onError(result)
                }
            });
        });

        //解析定位结果
        function onComplete(data) {
            var position = data.position;
            $("#lng").val(position.lng);
            $('#lat').val(position.lat);
        }

        //解析定位错误信息
        function onError(data) {
            swal("错误","定位失败，原因:"+data.message,"error");
        }
    }
}

var navigation = function(){
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

var showPosition = function (monitor) {
    var map = new AMap.Map("container", {
        resizeEnable: true,
        center: [monitor.lng, monitor.lat],
        zoom: 16
    });
    addMarker();

//添加marker标记
    function addMarker() {
        map.clearMap();
        var marker = new AMap.Marker({
            map: map,
            position: [monitor.lng, monitor.lat]
        });
        //鼠标点击marker弹出自定义的信息窗体
        AMap.event.addListener(marker, 'click', function () {
            infoWindow.open(map, marker.getPosition());
        });
    }

    //实例化信息窗体
    var title = monitor.name+'<span style="font-size:11px;color:#F00;"></span>',
        content = [];
    content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址："+monitor.address);
    var infoWindow = new AMap.InfoWindow({
        isCustom: true,  //使用自定义窗体
        content: createInfoWindow(title, content.join("<br/>")),
        offset: new AMap.Pixel(16, -45)
    });

//构建自定义信息窗体
    function createInfoWindow(title, content) {
        var info = document.createElement("div");
        info.className = "custom-info input-card content-window-card";

        //可以通过下面的方式修改自定义窗体的宽高
        //info.style.width = "400px";
        // 定义顶部标题
        var top = document.createElement("div");
        var titleD = document.createElement("div");
        var closeX = document.createElement("img");
        top.className = "info-top";
        titleD.innerHTML = title;
        closeX.src = "https://webapi.amap.com/images/close2.gif";
        closeX.onclick = closeInfoWindow;

        top.appendChild(titleD);
        top.appendChild(closeX);
        info.appendChild(top);

        // 定义中部内容
        var middle = document.createElement("div");
        middle.className = "info-middle";
        middle.style.backgroundColor = 'white';
        middle.innerHTML = content;
        info.appendChild(middle);

        // 定义底部内容
        var bottom = document.createElement("div");
        bottom.className = "info-bottom";
        bottom.style.position = 'relative';
        bottom.style.top = '0px';
        bottom.style.margin = '0 auto';
        var sharp = document.createElement("img");
        sharp.src = "https://webapi.amap.com/images/sharp.png";
        bottom.appendChild(sharp);
        info.appendChild(bottom);
        return info;
    }

//关闭信息窗体
    function closeInfoWindow() {
        map.clearInfoWindow();
    }
}

var selectLocation = function () {
    var map = new AMap.Map("container", {
        resizeEnable: true,
        zoom: 18
    })

    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = map.on('click', function(e) {
        document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat();

        if (marker) {
            marker.setMap(null);
            marker = null;
        }
        addMarker(e.lnglat.getLng(),e.lnglat.getLat());
        //这边是数组
        var lnglatXY=[e.lnglat.getLng(),e.lnglat.getLat()];
        regeocoder(lnglatXY);
    });

    var auto = new AMap.Autocomplete({
        input: "tipinput"
    });

    //注册监听，当选中某条记录时会触发
    AMap.event.addListener(auto, "select", select);
    function select(e) {
        var lng = e.poi.location.lng;
        var lat = e.poi.location.lat;
        console.log(e.poi.location.lng);
        if (e.poi && e.poi.location) {
            map.setZoom(15);
            map.setCenter(e.poi.location);
            addMarker(lng,lat);
        }
    }

    var marker;
    // 实例化点标记
    function addMarker(lng,lat) {
        marker = new AMap.Marker({
            icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: [lng, lat]
        });
        marker.setMap(map);
    }

    //坐标-地址
    function regeocoder(lnglatXY) {  //逆地理编码
        var geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
        });
        geocoder.getAddress(lnglatXY, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
            }
        });
    }

    function geocoder_CallBack(data) {
        var address = data.regeocode.formattedAddress; //返回地址描述
        document.getElementById("tipinput").value=address;
    }
}