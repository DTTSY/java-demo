//调试:打开浏览器控制台(F12),在代码中某行增加 debugger 即可调试

let vueData = function () {
    return {
        tableNo: 0,
        car: {
            车辆编号: '',
            车辆名称: '',
            车辆颜色: '',
            车辆类型: '',
            车辆计价: '',
            车辆状态: ''
        },
        order:{
            订单编号: '',
            车辆编号: '',
            日租金: '',
            租车用户: '',
            起始日期: '',
            还车日期: '',
            取车地点: '',
            租车天数: '',
            租车费用: ''
        },
        editItem: "",
        itemId:'',
        itemIndex:'',
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
        fileList : [],
        loading: true
}
}

let Ctor = Vue.extend({
    //绑定的变量
    data: vueData,
    /*当前页面绑定的方法集合 与布局器节点一一映射即可 参照element ui文档*/
    methods: {
        handleDelete(index, row){
            let _t = this;

            Swal.fire({
                title: '确认删除?',
                text: "此操作不能恢复！",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: '删除!',
                cancelButtonText: '取消!'
              }).then((result) => {
                if (result.isConfirmed) {
                    let id = '';
                    let DeleteType='';

                    switch (_t.tableNo) {
                        case 0:{
                            id = row.车辆编号;
                            DeleteType='deleteCar';
                            status = row.车辆状态;
                            break;
                        }
                        case 1:{
                            id = row.订单编号;
                            DeleteType='deleteOrder';
                        }
                        default:
                            break;
                    }
                    console.log(id);
                    
                    $.ajax({
                        url: "DeleteData.do",
                        data: {DeleteType: DeleteType, id: id,status: status},
                        type: "GET",
                        dataType: "json",
                        success: function(data) {
                            if(data.ok==1){
                                Swal.fire({
                                    icon: 'success',
                                    title: '(๑•̀ㅂ•́)و✧',
                                    text: '修改数据成功'
                                    });
                                //console.log(data.url);
                                _t.tableList.splice(index,1);
                                _t.rendering;
                            }
                            Swal.fire({
                                icon: 'error',
                                title: 'X﹏X',
                                text: '修改数据失败！！'
                            });
                        },
                        error : function(e){
                            Swal.fire({
                                icon: 'error',
                                title: 'X﹏X',
                                text: '修改数据失败！！'
                            });
                            console.log(e.status);
                            console.log(e.responseText);
                        }
                    });
                }
              });
        },
        handleModify(index, row,){
            this.itemIndex = index + (this.currentPage-1)*this.pageSize;
        
            switch(this.tableNo){
                case 0:{
                    this.itemId=row.车辆编号;
                    this.editItem = {
                        车辆编号: row.车辆编号,
                        车辆名称: row.车辆名称,
                        车辆颜色: row.车辆颜色,
                        车辆类型: row.车辆类型,
                        车辆计价: row.车辆计价,
                        车辆状态: row.车辆状态,
                    };
                    break;
                    }
                case 1:{
                    this.itemId = row.订单编号;
                    this.editItem = {
                        订单编号: row.订单编号,
                        车辆编号: row.车辆编号,
                        日租金: row.日租金,
                        租车用户: row.租车用户,
                        起始日期: row.起始日期,
                        还车日期: row.还车日期,
                        取车地点: row.取车地点,
                        租车天数: row.租车天数,
                        租车费用: row.租车费用
                    }
                    break;
                }
                default:{
                    break;
                }
            }
            this.dialogFormVisible=true;
        },
        showMain(){
            this.showTable=false;
        },
        sbmittModify(){
            let _t = this;
            let modifyData = '';

            switch (this.tableNo) {
                case 0:{
                    modifyData = {
                        modifyType: "modifyCar",
                        oldId: _t.itemId,
                        newId: _t.editItem.车辆编号,
                        name: _t.editItem.车辆名称,
                        color: _t.editItem.车辆颜色,
                        price: _t.editItem.车辆计价,
                        status: _t.editItem.车辆状态,
                        type: _t.editItem.车辆类型
                    }
                    break;
                }
                case 1:{
                    modifyData = {
                        modifyType: "modifyOrder",
                        oldId: _t.itemId,
                        newId: _t.editItem.订单编号,
                        carId: _t.editItem.车辆编号,
                        price: _t.editItem.日租金,
                        user: _t.editItem.租车用户,
                        orderDate: _t.editItem.起始日期,
                        repayDate: _t.editItem.还车日期,
                        city: _t.editItem.取车地点,
                        days: _t.editItem.租车天数,
                        total: _t.editItem.租车费用
                    }
                    break;
                }
                default:
                    break;
            }
            
            $.ajax({
                url: "ModifyData.do",
                data: modifyData,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data.ok == 1){
                        _t.dialogFormVisible = false;
                        Swal.fire({
                            icon: 'success',
                            title: '(๑•̀ㅂ•́)و✧',
                            text: '修改数据成功'
                            });
                            Vue.set(_t.tableList, _t.itemIndex, _t.editItem);
                            _t.editItem='';
                        //console.log(data.url);
                    }
                    else{
                        _t.dialogFormVisible = false;
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '修改数据失败！！'
                                });
                    }
                },
                error : function(e){
                    _t.dialogFormVisible = false;
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '修改数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });    
        },
        addItemFlush(tableno){
            switch (tableno) {
                case 0:
                    this.tableList.push(this.car);
                    this.car = {
                        车辆编号: '',
                        车辆名称: '',
                        车辆颜色: '',
                        车辆类型: '',
                        车辆计价: '',
                        车辆状态: ''
                    }
                    break;
            
                default:
                    break;
            }
        },
        addData(){
            let _t = this;
            let addData = '';
            switch (this.tableNo) {
                case 0:{
                    console.log(this.car);
                    addData = {
                        AddType: 'addCar',
                        DataName: 'Cars',
                        tableno: this.tableNo,
                        name: this.car.车辆名称,
                        color: this.car.车辆颜色,
                        type: this.car.车辆类型,
                        id: this.car.车辆编号,
                        price: this.car.车辆计价,
                        status: this.car.车辆状态
                    }
                    break;
                }
                case 1:{
                    addData = {
                        AddType: 'addOrder',
                        DataName: 'Orders',
                        tableno: this.tableNo,
                        name: this.car.车辆名称,
                        color: this.car.车辆颜色,
                        type: this.car.车辆类型,
                        id: this.car.车辆编号,
                        price: this.car.车辆计价,
                        status: this.car.车辆状态
                    }
                    break;
                }
                default:
                    break;
            }

            $.ajax({
                url: "AddData.do",
                data: addData,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data.ok == 1){
                        Swal.fire({
                            icon: 'success',
                            title: '(๑•̀ㅂ•́)و✧',
                            text: '增加数据成功'
                            });
                            _t.addItemFlush(_t.tableNo);
                            
                        //console.log(data.url);
                    }
                    else{
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '增加数据失败！！'
                                });
                    }
                },
                error : function(e){
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '增加数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });    

        }, 
        showShelt(Dataname, tableno) {
            let _t = this;
            _t.loading=true;
            this.showTable=true;
            _t.tableNo = tableno;

            $.ajax({
                url: "GetData.do",
                data: {dataName: Dataname},
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data!=null){
                        _t.tableList = data.list;
                        _t.tableListCol = data.col;
                    }
                    else{
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '获取数据失败！！'
                                });
                    }
                },
                error : function(e){
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '获取数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });

            setTimeout(()=>_t.loading=false, 1000);
        },
        logout() {
            self.location.href="Logout.do";
        },
        handleCurrentChange(val) {
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