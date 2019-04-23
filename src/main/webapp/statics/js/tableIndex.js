//表格初始化函数，相当于构造函数
var TableInit = function (url,columns) {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_content').bootstrapTable({
            url: url,//请求后台的URL（*）
            method: 'post',//请求方式（*）
            toolbar: '#toolbar',//工具按钮用哪个容器
            striped: true,//是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,//是否显示分页（*）
            sortable: false,//是否启用排序
            sortOrder: "asc",//默认排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",//分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,//初始化加载第一页，默认第一页
            pageSize: 5,//每页的记录行数（*）
            pageList: [5, 10, 20, 30],//可供选择的每页的行数（*）
            search: false,//是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,//是否显示所有的列
            showRefresh: true,//是否显示刷新按钮
            minimumCountColumns: 2,//最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            /*height: 500,*///行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",//每一行的唯一标识，一般为主键列
            showToggle:false,//是否显示详细视图和列表视图的切换按钮
            cardView: false,//是否显示详细视图
            detailView: false,//是否显示父子表
            columns: columns //填充数据
        });
       /* Query();*/
    };
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = GetQueryParams();
        temp["limit"] = params.limit;   //页面大小
        temp["offset"] = params.offset;  //开始记录数
        /*temp["pageindex"] = this.pageNumber;//当前页码*/
        return temp;
    };
    return oTableInit;
};
//查询
function Query() {
    // 刷新表格  ,页面从新从1开始
    $('#tb_content').bootstrapTable('refreshOptions',{pageNumber:1});
}

