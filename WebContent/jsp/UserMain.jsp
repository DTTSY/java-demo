<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head><meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>系统主界面</title>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/UserMain.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/elementUI.js"></script>
<!-- <script src="https://l2dwidget.js.org/lib/L2Dwidget.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>


</head>
<body>
<div id="app">
	<template>
	<el-container>
		<el-header style="width: 100%;" height="40px"></el-header>
		<el-container>
			<el-aside style="height: 100%;" width="200px">
				<el-menu mode="vertical">
					<el-menu-item index="1" @click.native.prevent="showForm=false">首页</el-menu-item>
					<el-submenu index="2" >
						<span  slot="title">普通用户功能</span>
						<el-menu-item index="1-1" @click.native.prevent="iniForm">租车</el-menu-item>
					</el-submenu>
					<el-menu-item index="4" @click.native.prevent="logout">登出</a></el-menu-item>
				</el-menu>
			</el-aside>

				<!-- <el-main >
					<el-carousel :interval="4000"  arrow="always" type="card">
						<el-carousel-item v-for="(img,index) in carImage" :key="index">
							<el-image 
								style="width: 100%"
								src="${pageContext.request.contextPath}/static/image/1.jpg"
								></el-image>
						  </el-carousel-item>
					  </el-carousel> -->

					  <el-card shadow="hover" body-style="left: 50%;">
						<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" v-show="showForm">

							<el-form-item label="取车城市" required>
								<el-col :span="11">
								<el-form-item  prop="city">
								<el-select v-model="ruleForm.city" placeholder="请选择">
									<el-option-group
									v-for="group in ruleForm.options"
									:key="group.label"
									:label="group.label">
									<el-option
										v-for="item in group.options"
										:key="item.value"
										:label="item.label"
										:value="item.value">
									</el-option>
									</el-option-group>
								</el-select>
								</el-form-item>
								</el-col>

								<el-col :span="11">
									<el-form-item label="送车地点" prop="region">
									<el-select v-model="ruleForm.region" placeholder="请选择">
										<el-option label="送车点一" value="送车点一"></el-option>
										<el-option label="送车点二" value="送车点二"></el-option>
									</el-select>
									</el-form-item>
								</el-col>
							</el-form-item>

							<el-form-item label="租   期" prop="value1">
								<el-date-picker
								v-model="ruleForm.value1"
								type="datetimerange"
								range-separator="至"
								start-placeholder="开始日期"
								end-placeholder="结束日期">
								</el-date-picker>
							</el-form-item>

							<el-form-item label="选择车型" clearable prop="car">
								<el-select v-model="ruleForm.car"  placeholder="请选择">
									<el-option
									  v-for="item in optionss"
									  :key="item.value"
									  :label="item.label"
									  :value="item.value">
									</el-option>
								  </el-select>
							</el-form-item>

							<el-form-item>
							  <el-button type="primary" @click="checkForm('ruleForm')">提交订单</el-button>
							  <el-button @click="resetForm('ruleForm')">重置</el-button>
							  <el-col :span="10"><h3 class="days">租期:{{ruleForm.rentDays(ruleForm.value1)}}天<br>租金:{{ruleForm.total(optionss)}}元</h3></el-col>
							</el-form-item>

							<el-dialog title="支付" :visible.sync="ruleForm.dialogFormVisible">
									<el-image src="${pageContext.request.contextPath}/static/image/QRcode_alipay.jpg"></el-image>
									<div slot="footer" class="dialog-footer">
									<el-button @click="ruleForm.dialogFormVisible = false">取 消</el-button>
									<el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
									</div>
							  </el-dialog>

						  </el-form>
					  </el-card>

				</el-main>
		</el-container>
	</el-container>
	</template>
</div>

<script src="${pageContext.request.contextPath}/static/js/UserMain.js"></script>

</body>

</html>