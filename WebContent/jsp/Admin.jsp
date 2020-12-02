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
					<el-submenu index="2" >
						<span  slot="title">普通用户功能</span>
						<el-menu-item index="1-1" >租车</el-menu-item>
					</el-submenu>
					<el-submenu index="3">
						<span class="magical-drag-tmp-submenu-name" slot="title">管理员</span>
						<el-menu-item index="2-1" @click.native.prevent="showShelt">车辆信息管理</el-menu-item>
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

					  <el-card shadow="hover">
						总是显示
					  </el-card>

					<el-card v-show="showTable">
						<el-table :data="tableList.slice((currentPage-1)*pageSize,currentPage*pageSize)" :fit="true" :show-header="true" stripe="true">
							<el-table-column v-for="(value,name) in tableList[0]" :prop="name" :label="name"></el-table-column>
							<el-table-column label="操作" fixed="right" width="200px">
								<template slot-scope="scope">
								<el-button mc-type="column-el-button" size="mini" type="primary"><i class="el-icon-edit">编辑</i></el-button>
								<el-button mc-type="column-el-button" size="mini" type="danger" @click.native.prevent="handleDelete(scope.$index, scope.row)"><i class="el-icon-delete">删除</i></el-button>
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

<script src="${pageContext.request.contextPath}/static/js/main.js"></script>

</body>

</html>
