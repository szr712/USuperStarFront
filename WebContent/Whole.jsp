<%@page import="com.zjut.azure2.WholeData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zjut.azure2.Operator"%>
<%@page import="com.zjut.azure2.Room"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>USuperStar酒店管理系统-前台</title>
<meta charset="UTF-8">
<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

<link rel="shortcut icon" href="assets/images/3.ico" type="image/x-icon" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body class="no-skin">

	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a href="index.html" class="navbar-brand"> <small> <i
						class="fa fa-h-square"></i> 前台管理 &nbsp;<small> USuperStar
					</small>
				</small>
				</a>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue dropdown-modal"><a
						data-toggle="dropdown" href="#" class="dropdown-toggle"> <img
							class="nav-user-photo" src="assets/images/avatars/avatar5.png"
							alt="Jason's Photo" /> <span class="user-info"> <small>欢迎,</small>
								<%
									Operator operator = (Operator) request.getSession().getAttribute("Operator");

									if (operator != null) {
								%> <%=operator.getName()%> <%
 	}
 %>

						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a>

						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">


							<li><a href="profile.jsp"> <i
									class="ace-icon fa fa-user"></i> 个人信息
							</a></li>

							<li class="divider"></li>

							<li><a href="logout"> <i
									class="ace-icon fa fa-power-off"></i> 退出登录
							</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

		<%@ include file="HTMLfile/sidebar.html"%>

		<div class="main-content">
			<div class="main-content-inner">


				<div class="page-content">
					<div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
							id="ace-settings-btn">
							<i class="ace-icon fa fa-cog bigger-130"></i>
						</div>

						<div class="ace-settings-box clearfix" id="ace-settings-box">
							<div class="pull-left width-50">
								<div class="ace-settings-item">
									<div class="pull-left">
										<select id="skin-colorpicker" class="hide">
											<option data-skin="no-skin" value="#438EB9">#438EB9</option>
											<option data-skin="skin-1" value="#222A2D">#222A2D</option>
											<option data-skin="skin-2" value="#C6487E">#C6487E</option>
											<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
										</select>
									</div>
									<span>&nbsp; 选择皮肤</span>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox"
										class="ace ace-checkbox-2 ace-save-state"
										id="ace-settings-navbar" autocomplete="off" /> <label
										class="lbl" for="ace-settings-navbar"> 固定导航栏</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox"
										class="ace ace-checkbox-2 ace-save-state"
										id="ace-settings-sidebar" autocomplete="off" /> <label
										class="lbl" for="ace-settings-sidebar"> 固定侧边栏</label>
								</div>



								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2"
										id="ace-settings-rtl" autocomplete="off" /> <label
										class="lbl" for="ace-settings-rtl"> 镜像</label>
								</div>

								<!-- <div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
										id="ace-settings-add-container" autocomplete="off" />
									<label class="lbl" for="ace-settings-add-container">
										Inside
										<b>.container</b>
									</label>
								</div> -->
							</div>
							<!-- /.pull-left -->

							<!-- <div class="pull-left width-50">
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"
										autocomplete="off" />
									<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"
										autocomplete="off" />
									<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"
										autocomplete="off" />
									<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
								</div>
							</div>/.pull-left -->
						</div>
						<!-- /.ace-settings-box -->
					</div>
					<!-- /.ace-settings-container -->

					<div class="page-header">
						<h1>总体信息</h1>
					</div>
					<!-- /.page-header -->
					<div>
						<table id="dynamic-table"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">房号</th>
									<th class="center">房型</th>
									<th class="center">房价</th>
									<th class="center">住房者姓名</th>
									<th class="center">证件种类</th>
									<th class="center">证件号</th>
									<th class="center">房间状态</th>
									<th class="center">入住时间</th>
									<th class="center">结束时间</th>
									<th class="center">备注</th>
									
								</tr>
							</thead>
							<tbody>
								<%
									ArrayList<WholeData> WholeData = (ArrayList<WholeData>) request.getAttribute("WholeData");
									//System.out.print(roomlist.size());
									for (int i = 0; i < WholeData.size(); i++) {
								%>
								<tr>
									<td class="center"><%=WholeData.get(i).getNum()%></td>
									<td class="center"><%=WholeData.get(i).getType()%></td>
									<td class="center"><%=WholeData.get(i).getPrice()%></td>
									<td class="center"><%=WholeData.get(i).getName()%></td>
									<td class="center"><%=WholeData.get(i).getId()%></td>
									<td class="center"><%=WholeData.get(i).getIdtype()%></td>
									<%if(WholeData.get(i).getStatus().equals("空闲")){ %>
									<td class="center"><span class="label label-sm label-success"><%=WholeData.get(i).getStatus()%></span></td>
									<%} else if(WholeData.get(i).getStatus().equals("预约")){ %>
									<td class="center"><span class="label label-sm label-inverse arrowed-in"><%=WholeData.get(i).getStatus()%></span></td>
									<%}else { %>
									<td class="center"><span class="label label-sm label-warning"><%=WholeData.get(i).getStatus()%></span></td>
									<%} %>
									<td class="center"><%=WholeData.get(i).getCheckin()%></td>
									<td class="center"><%=WholeData.get(i).getCheckout()%></td>
									<td class="center"><%=WholeData.get(i).getMark()%></td>
														
								

								</tr>
								<%
									}
								%>


							</tbody>


						</table>
					</div>
				
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<%@ include file="HTMLfile/footer.html"%>

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="assets/js/jquery-2.1.4.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<" + "/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
	<script src="assets/js/jquery-ui.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/js/jquery.easypiechart.min.js"></script>
	<script src="assets/js/jquery.sparkline.index.min.js"></script>
	<script src="assets/js/jquery.flot.min.js"></script>
	<script src="assets/js/jquery.flot.pie.min.js"></script>
	<script src="assets/js/jquery.flot.resize.min.js"></script>

	<!-- ace scripts -->
	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>



</body>

</html>