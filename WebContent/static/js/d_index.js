let DATA = function(){
    let validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码！'));
        } else if ( value !== this.singnUpForm.psw ) {
          callback(new Error('两次输入密码不一致！'));
        } else {
          callback();
        }
      };

      let validateName = (rule, value, callback) => {
        if (!/^[a-zA-Z][a-zA-Z0-9_]{3,10}$/.test(value)) {
            callback(new Error('允许字母开头，允许4-9字节，允许字母数字下划线组合'));
        }else {
          callback();
        }
      };
      
return {
    loginForm: {
    name: '',
    psw: '',
    authority: false,
    },
    singnUpForm: {
        name: '',
        psw: '',
        checkPass: ''
    },
    login :true,
    rules: {
    psw: [
        { required: true, message: ' 不能为空！', trigger: 'blur' }
    ],
    name: [
        { required: true, message: ' 不能为空！', trigger: 'blur' },
        { validator: validateName, trigger: 'blur'  }
    ],
    checkPass: [
        { validator: validatePass, trigger: 'blur' }
    ]
    }
    }
};

let _t=this;
let app = Vue.extend({
    data: DATA,
    mounted: function(){
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let that=this;
                    if(that.login){
                    $.ajax({
                        url: "Login.do",
                        data: {name: that.loginForm.name, psw: that.loginForm.psw, authority: that.loginForm.authority},
                        type: "GET",
                        dataType: "json",
                        success: function(data) {
                        	if(data.url!="null")
                        		self.location.href = data.url;
                        		//console.log(data.url);
                            else{
                        		Swal.fire({
										  icon: 'error',
										  title: 'X﹏X',
										  text: '用户名或密码有误！',
										  footer: '<a href>找回密码</a>'
										});
                        	}
                        }
                    });
                    }else{
                        $.ajax({
                            url: "SignUp.do",
                            data: {name: that.singnUpForm.name, psw: that.singnUpForm.checkPass},
                            type: "GET",
                            dataType: "json",
                            success: function(data) {
                                console.log(data);
                                if(data.state==1){
                                    Swal.fire({
                                        icon: 'success',
                                        title: '注册成功！',
                                        showConfirmButton: false,
                                        timer: 1500
                                      });
                                      that.login=true;
                                }else{
                                    Swal.fire({
                                              icon: 'error',
                                              title: 'X﹏X',
                                              text: '注册失败,用户已存在！',
                                            });
                                }
                            }
                        });

                    }
                } else {
                    console.log('error submit!!');
                    return false;
            }
        });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
});
new app().$mount('#app');
