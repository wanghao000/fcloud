$(document).ready(function(){
    $.ajax({
        type: "POST",
        url: "sys/menu/user",
        dataType: "json",
        async: false,
        success: function(result){
            if(result.code == 0){
                create(result.menuList);
            }else{
                swal("菜单数据加载失败" );
            }
        }
    });
});
function create(data) {

    for(var i=0;i<data.length;i++){
        var li = $("<li></li>")
        var obj = data[i];
        var catalog ="<a href='#'>" +
            "<i class='"+obj.icon+"'></i>" +
            "<span class='nav-label'>"+obj.name+"</span>" +
            "<span class='fa arrow'></span>" +
            "</a>" ;
        li.append(catalog);
        var list = obj.list;
        var ul = $("<ul class='nav nav-second-level'></ul>");
        for(var j=0;j<list.length;j++){
            var listData = list[j];
            ul.append("<li>" +
                "<a class='J_menuItem' href='"+listData.url+"'>"+listData.name+"</a>" +
                " </li>")
        }
        li.append(ul);
        $("#side-menu").append(li);

    }

}