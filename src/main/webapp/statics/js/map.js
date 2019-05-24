
var position = function(){

    var map = new AMap.Map("container", {
        resizeEnable: true,
        zoom: 18
    })

    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = map.on('click', function(e) {
        document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat();
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
        if (e.poi && e.poi.location) {
            map.setZoom(15);
            map.setCenter(e.poi.location);
            addMarker(lng,lat);
        }
    }

    var marker;
    // 实例化点标记
    function addMarker(lng,lat) {
        if (marker) {
            marker.setMap(null);
            marker = null;
        }
        marker = new AMap.Marker({
            icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: [lng, lat]
        });
        marker.setMap(map);
        $("#lng").val(lng);
        $('#lat').val(lat);
    }

    //坐标-地址
    function regeocoder(lnglatXY) {  //逆地理编码
        $("#lnglat").attr("value",lnglatXY[0]+","+lnglatXY[1]);
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
        console.log(data.regeocode.addressComponent);
        // setAddress(data.regeocode.addressComponent);
        var address = data.regeocode.formattedAddress; //返回地址描述
        document.getElementById("tipinput").value=address;
    }
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
            var lng = position.lng;
            var lat = position.lat;
            $("#lng").val(lng);
            $('#lat').val(lat);
            addMarker(lng,lat);
            map.setZoomAndCenter(18, [lng, lat]);
            $("#lnglat").val(lng+","+lat);
            var lnglatXY=[lng,lat];
            regeocoder(lnglatXY);
        }

        //解析定位错误信息
        function onError(data) {
            swal("错误","定位失败，原因:"+data.message,"error");
        }
}

var navigation = function(){
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                var mk = new BMap.Marker(r.point);
                var lng = r.point.lng;
                var lat = r.point.lat;
                $("#lng").val(lng);
                $('#lat').val(lat);
                addMarker(lng,lat);
                var lnglatXY=[lng,lat];
                regeocoder(lnglatXY);
            } else {
                alert('failed' + this.getStatus());
            }
        }, {enableHighAccuracy: true})
}

var showPosition = function (monitor) {
    // console.log(monitor);
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
    var address = monitor.address;
    var contact = monitor.contact;
    var contact_phone = monitor.contact_phone;
    var localObj = window.location; //这个的意思是获取当前页面的地址
    var protocol = location.protocol //获取http或https
    var host = localObj.host //获取JSP地址栏IP和端口号 //localhost:8080
    var contextPath = localObj.pathname.split("/")[1]; //获取项目名
    var basePath = protocol +"//"+host+"/"+contextPath;
    address = (address == "" || address == null)?"暂无信息":address;
    contact = (contact == "" || contact == null)?"暂无信息":contact;
    contact_phone = (contact_phone == "" || contact_phone == null)?"暂无信息":contact_phone;
    content.push("<img src='"+basePath+"/statics/img/address.png' style='width:20px'>地址："+address);
    content.push("<img src='"+basePath+"/statics/img/contact.png' style='width:20px'>联系人："+contact);
    content.push("<img src='"+basePath+"/statics/img/phone.png'  style='width:20px'>联系人电话："+contact_phone);
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

//根据经纬度解析的地址选定省市县
// function setAddress(address){
//     var province = address.province;
//     var city = address.city;
//     var district = address.district;
//     console.log(province);
//     $("#province option:contains("+province+")").attr("selected","selected");
//     provinceChange();
//     $("#city option:contains("+city+")").attr("selected","selected");
//     cityChange();
//     $("#district option:contains("+district+")").attr("selected","selected");
// }
function provinceChange(){
    var localObj = window.location; //这个的意思是获取当前页面的地址
    var protocol = location.protocol //获取http或https
    var host = localObj.host //获取JSP地址栏IP和端口号 //localhost:8080
    var contextPath = localObj.pathname.split("/")[1]; //获取项目名
    var basePath = protocol +"//"+host+"/"+contextPath;
    var src = window.location.pathname.split("/")[1];
    url = basePath+"/sys/china/show/";
    if ($("#province").val() == "") return;
    $("#city option").remove();
    $("#district option").remove();
    var html = "<option value='-1'>请选择</option>";
    $("#district").append(html);
    var code = $("#province").find("option:selected").attr("exid");
    url+=code;
    $.ajaxSettings.async = false;
    jQuery.post(url,function (pdata) {
        jQuery.each(pdata,function(idx,item){
            html += "<option value='" + item.id + "' exid='" + item.id + "'>" + item.name + "</option>";
        });
        $("#city").append(html);
    })
}
function cityChange() {
    var localObj = window.location; //这个的意思是获取当前页面的地址
    var protocol = location.protocol //获取http或https
    var host = localObj.host //获取JSP地址栏IP和端口号 //localhost:8080
    var contextPath = localObj.pathname.split("/")[1]; //获取项目名
    var basePath = protocol +"//"+host+"/"+contextPath;
    var src = window.location.pathname.split("/")[1];
    url = basePath+"/sys/china/show/";
    if ($("#city").val() == "") return;
    $("#district option").remove();
    var code = $("#city").find("option:selected").attr("exid");
    var html = "<option value='-1'>请选择</option>";
    url += code;
    jQuery.post(url,function (pdata) {
        jQuery.each(pdata,function(idx,item){
            html += "<option value='" + item.id + "' exid='" + item.id + "'>" + item.name + "</option>";
        });
        $("#district").append(html);
    });
}
