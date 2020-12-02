//调试:打开浏览器控制台(F12),在代码中某行增加 debugger 即可调试

var vueData = function () {
    return {
      showForm: false,
        ruleForm: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          city: '',
          value1: [new Date(2020, 12, 11, 10, 10), new Date(2020, 12, 12, 10, 10)],
          rentDays: function (day) {
            return Math.floor((day[1]-day[0])/(24*3600*1000));
          },
          total:function (list) {
            let _t = this;
            var price = 0;
            if(list!=null){
            for (let index = 0; index < list.length; index++) {
              if (list[index].value == _t.car) {
                price = list[index].price;
              }
            }
            return price * _t.rentDays(_t.value1);
            }else{
            return price;
            }
          },
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
          value1: [
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

var _t = this;
var Ctor = Vue.extend({
    //提前绑定的变量
    data: vueData,
    mounted:function(){
      
    },
    methods: {
        submitForm(formName) {
          let _t=this;
            this.$refs[formName].validate((valid) => {
              if (valid) {
                _t.ruleForm.dialogFormVisible=false;
                $.ajax({
                  url: "LeaseCar.do",
                  data: {city: _t.ruleForm.city, region: _t.ruleForm.region, rentDays: _t.ruleForm.rentDays(_t.ruleForm.value1), car: _t.ruleForm.car, date: _t.ruleForm.value1[0], total: _t.ruleForm.total(_t.optionss)},
                  type: "GET",
                  dataType: "json",
                  success: function(data){
                    if(data.ok==1){
                      Swal.fire({
                        icon: 'success',
                        title: '(๑•̀ㅂ•́)و✧',
                        text: '支付成功',
                        });
                    }else{
                      Swal.fire({
                      icon: 'error',
                      title: 'X﹏X',
                      text: '获取数据失败',
                      });
                    }
                    }
                  });
                console.log(_t.ruleForm.city);
                console.log(_t.ruleForm.region);
                console.log(_t.ruleForm.rentDays(_t.ruleForm.value1));
                console.log(_t.ruleForm.car);
                console.log(_t.ruleForm.value1[0]);
                console.log(_t.ruleForm.total(_t.optionss));
              } else {
                return false;
              }
            });
          },
          checkForm(formName) {
            let _t=this;
              this.$refs[formName].validate((valid) => {
                if (valid) {
                  _t.ruleForm.dialogFormVisible = true;
                } else {
                  return false;
                }
              });
          },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        logout: function () {
          self.location.href="Logout.do";
        },

        iniForm: function () {
          this.showForm=true;
          let _t=this;
          $.ajax({
            url: "GetData.do",
            data: {dataName: "availableCars"},
            type: "GET",
            dataType: "json",
            success: function(data){
              if(data.ok!=0){
                _t.optionss = JSON.parse(JSON.stringify(data.data));
                console.log(_t.optionss);
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
        }
        
    }
});
new Ctor().$mount('#app');
