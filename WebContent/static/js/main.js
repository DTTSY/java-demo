//调试:打开浏览器控制台(F12),在代码中某行增加 debugger 即可调试
var carId;

var vueData = function () {

    return {
        car: [{
            title:'车辆编号',
            value:''}
            ,{
            title:'车辆名称',
            value:''},
            {
            title:'车辆颜色',
            value:''
            },{
            title:'车辆类型',
            value:''
            },{
            title:'车辆计价',
            value:''
            }
        ],
        dialogFormVisible: false,
        tableListCol: {},
        tableList : [],
        currentPage : 1,
        radioGroup : "",
        pageSize: 9,
        checkboxGroup : [],
        input : "",
        select : "",
        badge : 10,
        showTable: false,
        showUserGUI:false,
        colorPicker : "",
        slider : 0,
        fileList : []
}
}

var vueMethod = { }

var vueMounted = function(){
}

var rowData = [];
var myMethod = {
	
}
for (var key in myMethod) {
    vueMethod[key] = myMethod[key];
}
var myMounted = function () {

}
if(myMounted!==null){vueMounted=myMounted;}
var myData = {
	tableList : rowData,
	}
//定义的数据覆盖布局器自动识别的变量
Object.assign(vueData, myData);
var _t = this;
var Ctor = Vue.extend({
    //提前绑定的变量
    data: vueData,
    //页面加载完 会执行方法 可以做一些初始化操作
    mounted: vueMounted,
    /*当前页面绑定的方法集合 与布局器节点一一映射即可 参照element ui文档*/
    methods: {
				focus : function (e) {try {_t.fastKey.focusElem(e);}catch(e) {}},
				_tabClick : function(){setTimeout(function(){new McECharts().render();},200)
				},
				handleDelete: function(index, row){
                    console.log(this.tableList[index].id);
                    this.tableList.splice(index,1);
					this.rendering;
                },
                handleModify: function(index, row){
                    this.dialogFormVisible=true;
                    carId = this.tableList[index].id;
                },
				showMain: function(){
					this.showTable=false;
                },
                sbmittModify: function(){
                    console.log(carId);
                    this.car.forEach(element => {
                        console.log(element.value);
                        element.value='';
                    });
                    // dialogFormVisible = false;
                }, 
				showShelt: function(Dataname) {
                    let _t = this;
                    $.ajax({
                        url: "GetData.do",
                        data: {dataName: Dataname},
                        type: "GET",
                        dataType: "json",
                        success: function(data) {
                        	if(data!=null){
                                _t.tableList = data.list;
                                _t.tableListCol = data.col;
                                //console.log(data.url);
                            }
                            else{
                        		Swal.fire({
										  icon: 'error',
										  title: 'X﹏X',
										  text: '获取数据失败！！'
										});
                        	}
                        }
                    });
					this.showTable=true;
                },
                logout: function () {
                    self.location.href="Logout.do";
                },
				handleCurrentChange : function(val) {
        			this.currentPage=val;
      			}
				}
});
new Ctor().$mount('#app');

/*
L2Dwidget.init({
    "model": {
        //jsonPath: "https://unpkg.com/live2d-widget-model-haruto/assets/haruto.model.json",//<!--这里改模型，前面后面都要改-->
        // jsonPath: "https://unpkg.com/live2d-widget-model-epsilon2_1/assets/Epsilon2.1.model.json",//<!--这里改模型，前面后面都要改-->
        //jsonPath: "https://unpkg.com/live2d-widget-model-nico/assets/nico.model.json",//<!--这里改模型，前面后面都要改-->
        // jsonPath: "https://unpkg.com/live2d-widget-model-gf/assets/Gantzert_Felixander.model.json",//<!--这里改模型，前面后面都要改-->
        // jsonPath: "https://unpkg.com/live2d-widget-model-haru/02/assets/haru02.model.json",//<!--这里改模型，前面后面都要改-->
        // jsonPath: "https://unpkg.com/live2d-widget-model-hibiki/assets/hibiki.model.json",//<!--这里改模型，前面后面都要改-->
        // jsonPath: "https://unpkg.com/live2d-widget-model-haru/02/assets/haru02.model.json",//<!--这里改模型，前面后面都要改-->
        // jsonPath: "https://unpkg.com/live2d-widget-model-haru/02/assets/haru02.model.json",//<!--这里改模型，前面后面都要改-->
        // jsonPath: "https://unpkg.com/live2d-widget-model-haru/02/assets/haru02.model.json",//<!--这里改模型，前面后面都要改-->
        "scale": 1
    },
    "display": {
        "position": "left",//<!--设置看板娘的上下左右位置-->
        "width": 150,
        "height": 300,
        "hOffset": 20,
        "vOffset": -30
    },
    "mobile": {
        "show": true,
        "scale": 0.5
    },
    "react": {
        "opacityDefault": 0.7,//<!--设置透明度-->
        "opacityOnHover": 0.2
    },
	dialog: {
    // 开启对话框
    enable: true,
    script: {
      // 每空闲 10 秒钟，显示一条一言
      'every idle 15s': '$hitokoto$',
      // 当触摸到星星图案
      'hover .star': '星星在天上而你在我心里 (*/   /*ω＼*)',
      // 当触摸到角色身体
      'tap body': '哎呀！别碰我！',
      // 当触摸到角色头部
      'tap face': '人家已经不是小孩子了！'
    }
  }
});*/