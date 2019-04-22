var i = -1;
var toastCount = 0;
var $toastlast;
function showtoast(shortCutFunction,msg,title) {
    var toastIndex = toastCount++;
    toastr.options = {
        closeButton: true,
        debug: true,
        progressBar: false,
        positionClass: "toast-top-right",
        onclick: null
    };
    if ($('#addBehaviorOnToastClick').prop('checked')) {
        toastr.options.onclick = function () {
            alert('You can perform some custom action after a toast goes away');
        };
    }
    toastr.options.showDuration = 400;
    toastr.options.hideDuration = 1000;
    toastr.options.timeOut = 0;
    toastr.options.extendedTimeOut =1000;
    toastr.options.showEasing = "swing";
    toastr.options.hideEasing = "linear";
    toastr.options.showMethod = "fadeIn";
    toastr.options.hideMethod = "fadeOut";
    //$("#toastrOptions").text("Command: toastr[" + shortCutFunction + "](\"" + msg + (title ? "\", \"" + title : '') + "\")\n\ntoastr.options = " + JSON.stringify(toastr.options, null, 2));
    var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
    $toastlast = $toast;
    if ($toast.find('#okBtn').length) {
        $toast.delegate('#okBtn', 'click', function () {
            alert('you clicked me. i was toast #' + toastIndex + '. goodbye!');
            $toast.remove();
        });
    }
    if ($toast.find('#surpriseBtn').length) {
        $toast.delegate('#surpriseBtn', 'click', function () {
            alert('Surprise! you clicked me. i was toast #' + toastIndex + '. You could perform an action here.');
        });
    }
}
function startSocket(path) {
    /*websocket*/
    var websocket;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080"+path+"/websocketServer");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://localhost:8080"+path+"/websocketServer");
    } else {
        websocket = new SockJS("http://localhost:8080"+path+"/sockjs/websocketServer");
    }
    websocket.onopen = function (evnt) {
        console.log('ws clint:open websocket')
        //发送消息
        /*var msg = 'userid=1';
        console.log('ws clint:send msg:'+msg)
        websocket.send(msg);*/
    };
    websocket.onmessage = function (evnt) {
        console.log('ws client:get message ')
        var data = eval('(' + evnt.data + ')');
        var time = data.date;
        if(data.type == 0){
            showtoast("error","编号"+data.imei+"告警，时间："+time.substring(6,8)+":"+time.substring(8,10)+":"+time.substring(10,12),data.msg);
        }else if(data.type == 1){
            showtoast("warning","编号"+data.imei+"告警，时间："+time.substring(6,8)+":"+time.substring(8,10)+":"+time.substring(10,12),data.msg);
        }
    };
    websocket.onerror = function (evnt) {
        console.log('ws client:error ' + evnt)
    };
    websocket.onclose = function (evnt) {
        console.log('ws clent:close ')
    }
}




