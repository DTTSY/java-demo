//调试:打开浏览器控制台(F12),在代码中某行增加 debugger 即可调试

let vueData = function () {
    return {
      tableListCol: {},
      tableList: [],
      currentPage : 1,
      radioGroup : "",
      pageSize: 9,
      checkboxGroup : [],
      input : "",
      select : "",
      srcList:[
        'https://s3.ax1x.com/2020/12/11/rEtYlT.png'
      ],
      badge : 10,
      slider : 0,
      fileList : [],
      showOrders: false,
      showForm: false,
      returnDate: new Date(),
      perDayPrice:'',
      beginDate: '',
      carId:'',
      orderId:'',
      tableNo: 0,
      ruleForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        city: '',
          dialogFormVisible: false,
          car: '',
          options: [{
            label: '热门城市',
            options: [{
              value: '上海',
              label: '上海'
            }, {
              value: '北京',
              label: '北京'
            }]
          }, {
            label: '城市名',
            options: [{
              value: '成都',
              label: '成都'
            }, {
              value: '深圳',
              label: '深圳'
            }, {
              value: '广州',
              label: '广州'
            }, {
              value: '大连',
              label: '大连'
            }]
          }]
        },
        optionss: [],
        perRent: 0,
        rules: {
          city: [
            { required: true, message: '请选择城市', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择车型', trigger: 'change' }
          ],
          car: [
            { required: true, message: '请选择车型', trigger: 'change' }
          ],
          date1: [
            {required: true, message: '请选择日期', trigger: 'change' }
          ],
          date2: [
            { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ]
        }
      };
}

//定义的数据覆盖布局器自动识别的变量

let = this;
let Ctor = Vue.extend({
    //提前绑定的变量
    data: vueData,
    mounted:function(){
      
    },
    methods: {
        submitForm(formName) {
          let _t=this;
            this.$refs[formName].validate((valid) => {
              if (valid) {
                //_t.ruleForm.dialogFormVisible=false;
                let d1 = moment(_t.ruleForm.date1).format('YYYY-MM-DD');
                let data={
                  action: 'lease',
                  city: _t.ruleForm.city + "/" +_t.ruleForm.region,
                  carId: _t.ruleForm.car,
                  price: _t.carPrice(_t.optionss),
                  beginDate: d1,
                }
                $.ajax({
                  url: "LeaseCar.do",
                  data: data,
                  type: "GET",
                  dataType: "json",
                  success: function(data){
                    if(data.ok==1){
                      Swal.fire({
                        icon: 'success',
                        title: '(๑•̀ㅂ•́)و✧',
                        text: '提交订单成功!',
                        });
                    }else{
                      Swal.fire({
                      icon: 'error',
                      title: 'X﹏X',
                      text: '提交订单失败!',
                      });
                    }
                    }
                  });
              } else {
                return false;
              }
            });
          },
          checkForm(formName) {
            let _t=this;
              this.$refs[formName].validate((valid) => {
                if (valid) {
                  _t.submitForm('ruleForm');
                } else {
                  return false;
                }
              });
          },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        logout() {
          self.location.href="Logout.do";
        },
        carPrice(list) {
          let _t = this;
          let price = 0;
          if(list!=null){
            for (let index = 0; index < list.length; index++) {
              if (list[index].value == _t.ruleForm.car) {
                price = list[index].price;
              }
            }
          }
          console.log(price);
          return price;
          },
        iniForm(){
          let _t=this;
          _t.showForm=true;
          _t.showOrders=false;
          $.ajax({
            url: "GetData.do",
            data: {dataName: "carOptions"},
            type: "GET",
            dataType: "json",
            success: function(data){
              if(data.ok!=0){
                _t.optionss = data.data;
                console.log('shoeform ; '+_t.optionss);
              }else{
                Swal.fire({
                icon: 'error',
                title: 'X﹏X',
                text: '获取数据失败',
                });
              }
              }
            });
            console.log(_t.ruleForm.optionss);
        },
        showOrder(tableno){
          let _t = this;
          _t.showForm=false;
          _t.showOrders=true;
          _t.tableNo=tableno;
          $.ajax({
            url: "GetData.do",
            data: {dataName: "myOrders",option: tableno},
            type: "GET",
            dataType: "json",
            success: function(data){
              if(data!=null){
                _t.tableList= data.list;
                _t.tableListCol = data.col;
              }else{
                Swal.fire({
                icon: 'error',
                title: 'X﹏X',
                text: '获取数据失败',
                });
              }
              }
            });
        },
        handleRetronCar(index, row){
          this.beginDate = moment(row.起始日期, "YYYY-MM-DD");
          this.perDayPrice = row.日租金;
          this.ruleForm.dialogFormVisible = true;
          this.carId=row.车辆编号;
          this.orderId=row.订单编号;
        },
        days(){
          let days = Math.floor((this.returnDate-this.beginDate)/(24*3600*1000));
          if(days>0){
            return days;
          }else
            return 0;
        },
        total(){
          return this.days()*this.perDayPrice;
        },
        submitChenge(){
          this.ruleForm.dialogFormVisible=false;

          if(this.days()==0){
            Swal.fire({
                icon: 'error',
                title: 'X﹏X',
                text: '还车日期无效，请重新选择！',
                });
          }else{
            let date={
              action: 'repay',
              repayDate: moment(this.returnDate).format('YYYY-MM-DD'),
              days: this.days(),
              total: this.total(),
              carId: this.carId,
              id: this.orderId,
            }

            $.ajax({
              url: "LeaseCar.do",
              data: date,
              type: "GET",
              dataType: "json",
              success: function(data){
                if(data.ok==1){
                  Swal.fire({
                    icon: 'success',
                    title: '(๑•̀ㅂ•́)و✧',
                    text: '还车成功!',
                   });
                  
                }else{
                  Swal.fire({
                    icon: 'success',
                    title: 'X﹏X',
                    text: '还车失败',
                    });
                }
              },
              error:function(e){
                Swal.fire({
                  icon: 'success',
                  title: 'X﹏X',
                  text: '还车失败',
                  });
              }
              });
          }
        },
        handleCurrentChange(val) {
          this.currentPage=val;
        }
        
    }
});
new Ctor().$mount('#app');
