
$(function () {
    map();
    function map() {
        var myChart = echarts.init(document.getElementById('map_1'));
        var chinaGeoCoordMap = {
            '黑龙江': [127.9688, 45.368],
            '内蒙古': [110.3467, 41.4899],
            "吉林": [125.8154, 44.2584],
            '北京市': [116.4551, 40.2539],
            "辽宁": [123.1238, 42.1216],
            "河北": [114.4995, 38.1006],
            "天津": [117.4219, 39.4189],
            "山西": [112.3352, 37.9413],
            "陕西": [109.1162, 34.2004],
            "甘肃": [103.5901, 36.3043],
            "宁夏": [106.3586, 38.1775],
            "青海": [101.4038, 36.8207],
            "新疆": [87.9236, 43.5883],
            "西藏": [91.11, 29.97],
            "四川": [103.9526, 30.7617],
            "重庆": [108.384366, 30.439702],
            "山东": [117.1582, 36.8701],
            "河南": [113.4668, 34.6234],
            "江苏": [118.8062, 31.9208],
            "安徽": [117.29, 32.0581],
            "湖北": [114.3896, 30.6628],
            "浙江": [119.5313, 29.8773],
            "福建": [119.4543, 25.9222],
            "江西": [116.0046, 28.6633],
            "湖南": [113.0823, 28.2568],
            "贵州": [106.6992, 26.7682],
            "云南": [102.9199, 25.4663],
            "广东": [113.12244, 23.009505],
            "广西": [108.479, 23.1152],
            "海南": [110.3893, 19.8516],
            '上海': [121.4648, 31.2891]
        };
        var chinaDatas = [
            [{
                name: '黑龙江',
                value: 0
            }],	[{
                name: '内蒙古',
                value: 0
            }],	[{
                name: '吉林',
                value: 0
            }],	[{
                name: '辽宁',
                value: 0
            }],	[{
                name: '河北',
                value: 0
            }],	[{
                name: '天津',
                value: 0
            }],	[{
                name: '山西',
                value: 0
            }],	[{
                name: '陕西',
                value: 0
            }],	[{
                name: '甘肃',
                value: 0
            }],	[{
                name: '宁夏',
                value: 0
            }],	[{
                name: '青海',
                value: 0
            }],	[{
                name: '新疆',
                value: 0
            }],[{
                name: '西藏',
                value: 0
            }],	[{
                name: '四川',
                value: 0
            }],	[{
                name: '重庆',
                value: 0
            }],	[{
                name: '山东',
                value: 0
            }],	[{
                name: '河南',
                value: 0
            }],	[{
                name: '江苏',
                value: 0
            }],	[{
                name: '安徽',
                value: 0
            }],	[{
                name: '湖北',
                value: 0
            }],	[{
                name: '浙江',
                value: 0
            }],	[{
                name: '福建',
                value: 0
            }],	[{
                name: '江西',
                value: 0
            }],	[{
                name: '湖南',
                value: 0
            }],	[{
                name: '贵州',
                value: 0
            }],[{
                name: '广西',
                value: 0
            }],	[{
                name: '海南',
                value: 0
            }],	[{
                name: '上海',
                value: 1
            }]
        ];

        var convertData = function(data) {
            var res = [];
            for(var i = 0; i < data.length; i++) {
                var dataItem = data[i];
                var fromCoord = chinaGeoCoordMap[dataItem[0].name];
                var toCoord = [119.5313, 29.8773];
                if(fromCoord && toCoord) {
                    res.push([{
                        coord: fromCoord,
                        value: dataItem[0].value
                    }, {
                        coord: toCoord,
                    }]);
                }
            }
            return res;
        };
        var series = [];
        [['浙江', chinaDatas]].forEach(function(item, i) {
            console.log(item)
            series.push({
                    type: 'lines',
                    zlevel: 2,
                    effect: {
                        show: true,
                        period: 4, //箭头指向速度，值越小速度越快
                        trailLength: 0.02, //特效尾迹长度[0,1]值越大，尾迹越长重
                        symbol: 'arrow', //箭头图标
                        symbolSize: 5, //图标大小
                    },
                    lineStyle: {
                        normal: {
                            width: 1, //尾迹线条宽度
                            opacity: 1, //尾迹线条透明度
                            curveness: .3 //尾迹线条曲直度
                        }
                    },
                    data: convertData(item[1])
                }, {
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    zlevel: 2,
                    rippleEffect: { //涟漪特效
                        period: 4, //动画时间，值越小速度越快
                        brushType: 'stroke', //波纹绘制方式 stroke, fill
                        scale: 4 //波纹圆环最大限制，值越大波纹越大
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'right', //显示位置
                            offset: [5, 0], //偏移设置
                            formatter: function(params){//圆环显示文字
                                return params.data.name;
                            },
                            fontSize: 13
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    symbol: 'circle',
                    symbolSize: function(val) {
                        return 5+ val[2] * 5; //圆环大小
                    },
                    itemStyle: {
                        normal: {
                            show: false,
                            color: '#f00'
                        }
                    },
                    data: item[1].map(function(dataItem) {
                        return {
                            name: dataItem[0].name,
                            value: chinaGeoCoordMap[dataItem[0].name].concat([dataItem[0].value])
                        };
                    }),
                },
                //被攻击点
                {
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    zlevel: 2,
                    rippleEffect: {
                        period: 4,
                        brushType: 'stroke',
                        scale: 4
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'right',
                            //offset:[5, 0],
                            color: '#0f0',
                            formatter: '{b}',
                            textStyle: {
                                color: "#0f0"
                            }
                        },
                        emphasis: {
                            show: true,
                            color: "#f60"
                        }
                    },
                    symbol: 'pin',
                    symbolSize: 50,
                    data: [{
                        name: item[0],
                        value: chinaGeoCoordMap[item[0]].concat([10]),
                    }],
                }
            );
        });

        option = {
            tooltip: {
                trigger: 'item',
                backgroundColor: 'rgba(166, 200, 76, 0.82)',
                borderColor: '#FFFFCC',
                showDelay: 0,
                hideDelay: 0,
                enterable: true,
                transitionDuration: 0,
                extraCssText: 'z-index:100',
                formatter: function(params, ticket, callback) {
                    //根据业务自己拓展要显示的内容
                    var res = "";
                    var name = params.name;
                    var value = params.value[params.seriesIndex + 1];
                    res = "<span style='color:#fff;'>" + name + "</span><br/>数据：" + value;
                    return res;
                }
            },
            // backgroundColor:"#013954",
            visualMap: { //图例值控制
                min: 0,
                max: 1,
                calculable: true,
                show: false,
                color: ['#f44336', '#fc9700', '#ffde00', '#ffde00', '#00eaff'],
                textStyle: {
                    color: '#fff'
                }
            },
            geo: {
                map: 'china',
                zoom: 1.2,
                label: {
                    emphasis: {
                        show: false
                    }
                },
                roam: false, //是否允许缩放
                itemStyle: {
                    normal: {
                        color: 'rgba(51, 69, 89, .5)', //地图背景色
                        borderColor: '#516a89', //省市边界线00fcff 516a89
                        borderWidth: 1
                    },
                    emphasis: {
                        color: 'rgba(37, 43, 61, .5)' //悬浮背景
                    }
                }
            },
            series: series
        };
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
      /*  // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('map_1'));
var data = [
     {name: '海门', value: 0},
     {name: '鄂尔多斯', value: 0},
     {name: '招远', value: 0},
     {name: '舟山', value: 0},
     {name: '齐齐哈尔', value: 0},
     {name: '盐城', value: 0},
     {name: '赤峰', value: 0},
     {name: '青岛', value: 0},
     {name: '乳山', value: 0},
     {name: '金昌', value: 0},
     {name: '泉州', value: 0},
     {name: '莱西', value: 0},
     {name: '日照', value: 0},
     {name: '胶南', value: 0},
     {name: '南通', value: 0},
     {name: '拉萨', value: 0},
     {name: '云浮', value: 0},
     {name: '梅州', value: 0},
     {name: '文登', value: 0},
     {name: '上海', value: 0},
     {name: '攀枝花', value: 0},
     {name: '威海', value: 0},
     {name: '承德', value: 0},
     {name: '厦门', value: 0},
     {name: '汕尾', value: 0},
     {name: '潮州', value: 0},
     {name: '丹东', value: 0},
     {name: '太仓', value: 0},
     {name: '曲靖', value: 0},
     {name: '烟台', value: 0},
     {name: '福州', value: 0},
     {name: '瓦房店', value: 0},
     {name: '即墨', value: 0},
     {name: '抚顺', value: 0},
     {name: '玉溪', value: 0},
     {name: '张家口', value: 0},
     {name: '阳泉', value: 0},
     {name: '莱州', value: 0},
     {name: '湖州', value: 0},
     {name: '汕头', value: 0},
     {name: '昆山', value: 0},
     {name: '宁波', value: 0},
     {name: '湛江', value: 0},
     {name: '揭阳', value: 0},
     {name: '荣成', value: 0},
     {name: '连云港', value: 0},
     {name: '葫芦岛', value: 0},
     {name: '常熟', value: 0},
     {name: '东莞', value: 0},
     {name: '河源', value: 0},
     {name: '淮安', value: 0},
     {name: '泰州', value: 0},
     {name: '南宁', value: 0},
     {name: '营口', value: 0},
     {name: '惠州', value: 0},
     {name: '江阴', value: 0},
     {name: '蓬莱', value: 0},
     {name: '韶关', value: 0},
     {name: '嘉峪关', value: 0},
     {name: '广州', value: 0},
     {name: '延安', value: 0},
     {name: '太原', value: 0},
     {name: '清远', value: 0},
     {name: '中山', value: 0},
     {name: '昆明', value: 0},
     {name: '寿光', value: 0},
     {name: '盘锦', value: 0},
     {name: '长治', value: 0},
     {name: '深圳', value: 0},
     {name: '珠海', value: 0},
     {name: '宿迁', value: 0},
     {name: '咸阳', value: 0},
     {name: '铜川', value: 0},
     {name: '平度', value: 0},
     {name: '佛山', value: 0},
     {name: '海口', value: 0},
     {name: '江门', value: 0},
     {name: '章丘', value: 0},
     {name: '肇庆', value: 0},
     {name: '大连', value: 0},
     {name: '临汾', value: 0},
     {name: '吴江', value: 0},
     {name: '石嘴山', value: 0},
     {name: '沈阳', value: 0},
     {name: '苏州', value: 0},
     {name: '茂名', value: 0},
     {name: '嘉兴', value: 0},
     {name: '长春', value: 0},
     {name: '胶州', value: 0},
     {name: '银川', value: 0},
     {name: '张家港', value: 0},
     {name: '三门峡', value: 0},
     {name: '锦州', value: 0},
     {name: '南昌', value: 0},
     {name: '柳州', value: 0},
     {name: '三亚', value: 0},
     {name: '自贡', value: 0},
     {name: '吉林', value: 0},
     {name: '阳江', value: 0},
     {name: '泸州', value: 0},
     {name: '西宁', value: 0},
     {name: '宜宾', value: 0},
     {name: '呼和浩特', value: 0},
     {name: '成都', value: 0},
     {name: '大同', value: 0},
     {name: '镇江', value: 0},
     {name: '桂林', value: 0},
     {name: '张家界', value: 0},
     {name: '宜兴', value: 0},
     {name: '北海', value: 0},
     {name: '西安', value: 0},
     {name: '金坛', value: 0},
     {name: '东营', value: 0},
     {name: '牡丹江', value: 0},
     {name: '遵义', value: 0},
     {name: '绍兴', value: 0},
     {name: '扬州', value: 0},
     {name: '常州', value: 0},
     {name: '潍坊', value: 0},
     {name: '重庆', value: 0},
     {name: '台州', value: 0},
     {name: '南京', value: 0},
     {name: '滨州', value: 0},
     {name: '贵阳', value: 0},
     {name: '无锡', value: 0},
     {name: '本溪', value: 0},
     {name: '克拉玛依', value: 0},
     {name: '渭南', value: 0},
     {name: '马鞍山', value: 0},
     {name: '宝鸡', value: 0},
     {name: '焦作', value: 0},
     {name: '句容', value: 0},
     {name: '北京', value: 0},
     {name: '徐州', value: 0},
     {name: '衡水', value: 0},
     {name: '包头', value: 0},
     {name: '绵阳', value: 0},
     {name: '乌鲁木齐', value: 0},
     {name: '枣庄', value: 0},
     {name: '杭州', value: 0},
     {name: '淄博', value: 0},
     {name: '鞍山', value: 0},
     {name: '溧阳', value: 0},
     {name: '库尔勒', value: 0},
     {name: '安阳', value: 0},
     {name: '开封', value: 0},
     {name: '济南', value: 0},
     {name: '德阳', value: 0},
     {name: '温州', value: 0},
     {name: '九江', value: 0},
     {name: '邯郸', value: 0},
     {name: '临安', value: 0},
     {name: '兰州', value: 0},
     {name: '沧州', value: 0},
     {name: '临沂', value: 0},
     {name: '南充', value: 0},
     {name: '天津', value: 0},
     {name: '富阳', value: 0},
     {name: '泰安', value: 0},
     {name: '诸暨', value: 0},
     {name: '郑州', value: 0},
     {name: '哈尔滨', value: 0},
     {name: '聊城', value: 0},
     {name: '芜湖', value: 0},
     {name: '唐山', value: 0},
     {name: '平顶山', value: 0},
     {name: '邢台', value: 0},
     {name: '德州', value: 0},
     {name: '济宁', value: 0},
     {name: '荆州', value: 0},
     {name: '宜昌', value: 0},
     {name: '义乌', value: 0},
     {name: '丽水', value: 0},
     {name: '洛阳', value: 0},
     {name: '秦皇岛', value: 0},
     {name: '株洲', value: 0},
     {name: '石家庄', value: 0},
     {name: '莱芜', value: 0},
     {name: '常德', value: 0},
     {name: '保定', value: 0},
     {name: '湘潭', value: 0},
     {name: '金华', value: 0},
     {name: '岳阳', value: 0},
     {name: '长沙', value: 0},
     {name: '衢州', value: 0},
     {name: '廊坊', value: 0},
     {name: '菏泽', value: 0},
     {name: '合肥', value: 0},
     {name: '武汉', value: 0},
     {name: '大庆', value: 0}
];
$.ajax({
    type:"post",
    // async:"false",
    url:"sys/eq/findAreaAlarmCount",
    dataType:"json",
    success:function(result){
        for (var i = 0; i < data.length; i++) {
            for (var j = 0; j < result.length; j++) {
                if (data[i].name == result[j].sn) {
                    data[i].value = result[j].csi;
                    f();
                }
            }
        }
    }
});
function f() {
var geoCoordMap = {
    '海门':[121.15,31.89],
    '鄂尔多斯':[109.781327,39.608266],
    '招远':[120.38,37.35],
    '舟山':[122.207216,29.985295],
    '齐齐哈尔':[123.97,47.33],
    '盐城':[120.13,33.38],
    '赤峰':[118.87,42.28],
    '青岛':[120.33,36.07],
    '乳山':[121.52,36.89],
    '金昌':[102.188043,38.520089],
    '泉州':[118.58,24.93],
    '莱西':[120.53,36.86],
    '日照':[119.46,35.42],
    '胶南':[119.97,35.88],
    '南通':[121.05,32.08],
    '拉萨':[91.11,29.97],
    '云浮':[112.02,22.93],
    '梅州':[116.1,24.55],
    '文登':[122.05,37.2],
    '上海':[121.48,31.22],
    '攀枝花':[101.718637,26.582347],
    '威海':[122.1,37.5],
    '承德':[117.93,40.97],
    '厦门':[118.1,24.46],
    '汕尾':[115.375279,22.786211],
    '潮州':[116.63,23.68],
    '丹东':[124.37,40.13],
    '太仓':[121.1,31.45],
    '曲靖':[103.79,25.51],
    '烟台':[121.39,37.52],
    '福州':[119.3,26.08],
    '瓦房店':[121.979603,39.627114],
    '即墨':[120.45,36.38],
    '抚顺':[123.97,41.97],
    '玉溪':[102.52,24.35],
    '张家口':[114.87,40.82],
    '阳泉':[113.57,37.85],
    '莱州':[119.942327,37.177017],
    '湖州':[120.1,30.86],
    '汕头':[116.69,23.39],
    '昆山':[120.95,31.39],
    '宁波':[121.56,29.86],
    '湛江':[110.359377,21.270708],
    '揭阳':[116.35,23.55],
    '荣成':[122.41,37.16],
    '连云港':[119.16,34.59],
    '葫芦岛':[120.836932,40.711052],
    '常熟':[120.74,31.64],
    '东莞':[113.75,23.04],
    '河源':[114.68,23.73],
    '淮安':[119.15,33.5],
    '泰州':[119.9,32.49],
    '南宁':[108.33,22.84],
    '营口':[122.18,40.65],
    '惠州':[114.4,23.09],
    '江阴':[120.26,31.91],
    '蓬莱':[120.75,37.8],
    '韶关':[113.62,24.84],
    '嘉峪关':[98.289152,39.77313],
    '广州':[113.23,23.16],
    '延安':[109.47,36.6],
    '太原':[112.53,37.87],
    '清远':[113.01,23.7],
    '中山':[113.38,22.52],
    '昆明':[102.73,25.04],
    '寿光':[118.73,36.86],
    '盘锦':[122.070714,41.119997],
    '长治':[113.08,36.18],
    '深圳':[114.07,22.62],
    '珠海':[113.52,22.3],
    '宿迁':[118.3,33.96],
    '咸阳':[108.72,34.36],
    '铜川':[109.11,35.09],
    '平度':[119.97,36.77],
    '佛山':[113.11,23.05],
    '海口':[110.35,20.02],
    '江门':[113.06,22.61],
    '章丘':[117.53,36.72],
    '肇庆':[112.44,23.05],
    '大连':[121.62,38.92],
    '临汾':[111.5,36.08],
    '吴江':[120.63,31.16],
    '石嘴山':[106.39,39.04],
    '沈阳':[123.38,41.8],
    '苏州':[120.62,31.32],
    '茂名':[110.88,21.68],
    '嘉兴':[120.76,30.77],
    '长春':[125.35,43.88],
    '胶州':[120.03336,36.264622],
    '银川':[106.27,38.47],
    '张家港':[120.555821,31.875428],
    '三门峡':[111.19,34.76],
    '锦州':[121.15,41.13],
    '南昌':[115.89,28.68],
    '柳州':[109.4,24.33],
    '三亚':[109.511909,18.252847],
    '自贡':[104.778442,29.33903],
    '吉林':[126.57,43.87],
    '阳江':[111.95,21.85],
    '泸州':[105.39,28.91],
    '西宁':[101.74,36.56],
    '宜宾':[104.56,29.77],
    '呼和浩特':[111.65,40.82],
    '成都':[104.06,30.67],
    '大同':[113.3,40.12],
    '镇江':[119.44,32.2],
    '桂林':[110.28,25.29],
    '张家界':[110.479191,29.117096],
    '宜兴':[119.82,31.36],
    '北海':[109.12,21.49],
    '西安':[108.95,34.27],
    '金坛':[119.56,31.74],
    '东营':[118.49,37.46],
    '牡丹江':[129.58,44.6],
    '遵义':[106.9,27.7],
    '绍兴':[120.58,30.01],
    '扬州':[119.42,32.39],
    '常州':[119.95,31.79],
    '潍坊':[119.1,36.62],
    '重庆':[106.54,29.59],
    '台州':[121.420757,28.656386],
    '南京':[118.78,32.04],
    '滨州':[118.03,37.36],
    '贵阳':[106.71,26.57],
    '无锡':[120.29,31.59],
    '本溪':[123.73,41.3],
    '克拉玛依':[84.77,45.59],
    '渭南':[109.5,34.52],
    '马鞍山':[118.48,31.56],
    '宝鸡':[107.15,34.38],
    '焦作':[113.21,35.24],
    '句容':[119.16,31.95],
    '北京':[116.46,39.92],
    '徐州':[117.2,34.26],
    '衡水':[115.72,37.72],
    '包头':[110,40.58],
    '绵阳':[104.73,31.48],
    '乌鲁木齐':[87.68,43.77],
    '枣庄':[117.57,34.86],
    '杭州':[120.19,30.26],
    '淄博':[118.05,36.78],
    '鞍山':[122.85,41.12],
    '溧阳':[119.48,31.43],
    '库尔勒':[86.06,41.68],
    '安阳':[114.35,36.1],
    '开封':[114.35,34.79],
    '济南':[117,36.65],
    '德阳':[104.37,31.13],
    '温州':[120.65,28.01],
    '九江':[115.97,29.71],
    '邯郸':[114.47,36.6],
    '临安':[119.72,30.23],
    '兰州':[103.73,36.03],
    '沧州':[116.83,38.33],
    '临沂':[118.35,35.05],
    '南充':[106.110698,30.837793],
    '天津':[117.2,39.13],
    '富阳':[119.95,30.07],
    '泰安':[117.13,36.18],
    '诸暨':[120.23,29.71],
    '郑州':[113.65,34.76],
    '哈尔滨':[126.63,45.75],
    '聊城':[115.97,36.45],
    '芜湖':[118.38,31.33],
    '唐山':[118.02,39.63],
    '平顶山':[113.29,33.75],
    '邢台':[114.48,37.05],
    '德州':[116.29,37.45],
    '济宁':[116.59,35.38],
    '荆州':[112.239741,30.335165],
    '宜昌':[111.3,30.7],
    '义乌':[120.06,29.32],
    '丽水':[119.92,28.45],
    '洛阳':[112.44,34.7],
    '秦皇岛':[119.57,39.95],
    '株洲':[113.16,27.83],
    '石家庄':[114.48,38.03],
    '莱芜':[117.67,36.19],
    '常德':[111.69,29.05],
    '保定':[115.48,38.85],
    '湘潭':[112.91,27.87],
    '金华':[119.64,29.12],
    '岳阳':[113.09,29.37],
    '长沙':[113,28.21],
    '衢州':[118.88,28.97],
    '廊坊':[116.7,39.53],
    '菏泽':[115.480656,35.23375],
    '合肥':[117.27,31.86],
    '武汉':[114.31,30.52],
    '大庆':[125.03,46.58]
};

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};

option = {
   // backgroundColor: '#404a59',
  /!***  title: {
        text: '实时行驶车辆',
        subtext: 'data from PM25.in',
        sublink: 'http://www.pm25.in',
        left: 'center',
        textStyle: {
            color: '#fff'
        }
    },**!/
    tooltip : {
        trigger: 'item',
		formatter: function (params) {
              if(typeof(params.value)[2] == "undefined"){
              	return params.name + ' : ' + params.value;
              }else{
              	return params.name + ' : ' + params.value[2];
              }
            }
    },
  
    geo: {
        map: 'china',
        label: {
            emphasis: {
                show: false
            }
        },
        roam: false,//禁止其放大缩小
        itemStyle: {
            normal: {
                areaColor: '#4c60ff',
                borderColor: '#002097'
            },
            emphasis: {
                areaColor: '#293fff'
            }
        }
    },
    series : [
        {
            name: '消费金额',
            type: 'scatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            symbolSize: function (val) {
                return val[2] / 15;
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#ffeb7b'
                }
            }
        }
		
		/!**
		,
        {
            name: 'Top 5',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: convertData(data.sort(function (a, b) {
                return b.value - a.value;
            }).slice(0, 6)),
            symbolSize: function (val) {
                return val[2] / 20;
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#ffd800',
                    shadowBlur: 10,
                    shadowColor: 'rgba(0,0,0,.3)'
                }
            },
            zlevel: 1
        }
		**!/
    ]
};

        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
}*/
    }

})
