<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>系统主界面</title>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/UserMain.css">
<script src="${pageContext.request.contextPath}/static/js/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/elementUI.js"></script>
<!-- <script src="https://l2dwidget.js.org/lib/L2Dwidget.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="http://cdn.staticfile.org/moment.js/2.24.0/moment.js"></script>

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
						<el-menu-item index="1-2" @click.native.prevent="showOrder(0)">还车</el-menu-item>
						<el-menu-item index="1-3" @click.native.prevent="showOrder(1)">租车历史查看</el-menu-item>
					</el-submenu>
					<el-menu-item index="4" @click.native.prevent="logout">登出</a></el-menu-item>
				</el-menu>
			</el-aside>
		
				<el-main >
					  <el-card  v-show="showForm" shadow="hover" class="form_area" body-style="left: 50%;">
						<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" >

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

							<el-form-item label="取车日期" prop="date1">
								<el-date-picker
									v-model="ruleForm.date1"
									type="date"
									placeholder="选择日期">
								</el-date-picker>
							</el-form-item>

							<el-form-item>
							  <el-button type="primary" @click="checkForm('ruleForm')">提交订单</el-button>
							  <el-button @click="resetForm('ruleForm')">重置</el-button>
							  <el-col :span="10"><h3 class="days">每日租金: {{carPrice(optionss)}}元</h3></el-col>
							</el-form-item>
						  </el-form>
					  </el-card>

							<el-dialog title="扫码支付" :visible.sync="ruleForm.dialogFormVisible">
								<div>
										<el-date-picker
											v-model="returnDate"
											type="date"
											placeholder="选择还车日期">
										</el-date-picker>
									</div>
									<div>
										<h1>开始日期：{{moment(beginDate).format("YYY-MM-DD")}}</h1>
										<h1>日租金：{{perDayPrice}}</h1>
										<h1>租车天数：{{days()}}</h1>
										<h1>租车费用：{{total()}}</h1>
									</div>
									<div>
									<el-image 
										style="width: 200px; height: 200px"
										src="https://s3.ax1x.com/2020/12/11/rEtYlT.png"
										:preview-src-list="srcList">
									</el-image>
									</div>

									<div slot="footer" class="dialog-footer">
									<el-button @click="ruleForm.dialogFormVisible = false">取 消</el-button>
									<el-button type="primary" @click="submitChenge">确 定</el-button>
									</div>
							</el-dialog>

					  <el-card v-show="showOrders">
						<el-table :data="tableList" :fit="true" :show-header="true" stripe="true">
							<el-table-column v-for="(value, key) in this.tableListCol" :prop="key" :label="value"></el-table-column>
							<el-table-column label="操作" fixed="right" width="200px">
								<template slot-scope="scope">
								<el-button v-show="tableNo==0" mc-type="column-el-button" size="mini" type="primary" @click.native.prevent="handleRetronCar(scope.$index, scope.row)" icon="el-icon-edit" round>还车</el-button>
								</template>
							</el-table-column>
						</el-table>
						
						<el-pagination style="margin-left: -5px;" @current-change="handleCurrentChange" layout="prev,pager,next" :total="tableList.length" :current-page.sync="currentPage" :page-size="pageSize" :pager-count="5"></el-pagination>
					</el-card>

				</el-main>
		</el-container>
	</el-container>
	</template>
</div>

<script src="${pageContext.request.contextPath}/static/js/UserMain.js"></script>

</body>

</html>
