<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head><meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>系统主界面</title>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/elementUI.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<!-- <script src="https://l2dwidget.js.org/lib/L2Dwidget.min.js"></script> -->
 
</head>
<body>
<div id="app">
	<template>
	<el-container>
		<el-header style="width: 100%;" height="40px"></el-header>
		<el-container>
			<el-aside style="height: 100%;" width="200px">
				<el-menu mode="vertical">
					<el-menu-item index="1" @click.native.prevent="showMain">首页</el-menu-item>
					<el-submenu index="2">
						<span  slot="title">管理员</span>
						<el-menu-item index="2-1" @click.native.prevent="showShelt('Cars')">车辆信息管理</el-menu-item>
						<el-menu-item index="2-1" @click.native.prevent="showShelt('userOrders')">租车管理</el-menu-item>
						<el-menu-item index="2-1" @click.native.prevent="showShelt('Cars')">还车管理</el-menu-item>
						<el-menu-item index="2-1" @click.native.prevent="showShelt('Cars')">车辆修理管理</el-menu-item>
					</el-submenu>
					<el-menu-item index="4" @click.native.prevent="logout">登出</a></el-menu-item>
				</el-menu>
			</el-aside>

				<el-main>
					<el-card v-show="showTable">

						<div slot="header" class="clearfix">
							<span>增加</span>
							<el-button  type="primary" icon="el-icon-circle-plus-outline" circle></i></el-button>
						  </div>

						<el-table :data="tableList.slice((currentPage-1)*pageSize,currentPage*pageSize)" :fit="true" :show-header="true" stripe="true">
							<el-table-column v-for="(value,name) in this.tableListCol" :prop="name" :label="value"></el-table-column>
							<el-table-column label="操作" fixed="right" width="200px">
								<template slot-scope="scope">
								<el-button mc-type="column-el-button" size="mini" type="primary" @click.native.prevent="handleModify(scope.$index, scope.row)" icon="el-icon-edit" round>编辑</el-button>
								<el-button mc-type="column-el-button" size="mini" type="danger" @click.native.prevent="handleDelete(scope.$index, scope.row)" icon="el-icon-delete" round>删除</el-button>
								</template>
							</el-table-column>
						</el-table>
						<el-pagination style="margin-left: -5px;" @current-change="handleCurrentChange" layout="prev,pager,next" :total="tableList.length" :current-page.sync="currentPage" :page-size="pageSize" :pager-count="5"></el-pagination>
					</el-card>

					<el-dialog title="修改信息" :visible.sync="this.dialogFormVisible">

						<el-input v-for="item in this.car" v-model="item.value" :placeholder="item.title"></el-input>
						<!-- <el-form :model="cars">
						  <el-form-item  v-for="(value, name) in this.cars" label="name">
							<el-input :model="cars.id" autocomplete="off"></el-input>
						  </el-form-item>
						</el-form> -->
						<div slot="footer" class="dialog-footer">
						  <el-button @click="dialogFormVisible = false">取 消</el-button>
						  <el-button type="primary" @click.native.prevent="sbmittModify">确 定</el-button>
						</div>
					  </el-dialog>

				</el-main>

		</el-container>
	</el-container>
	</template>
</div>

<script src="${pageContext.request.contextPath}/static/js/main.js"></script>

</body>

</html>
