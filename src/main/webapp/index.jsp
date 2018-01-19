<%--<%@ page import="java.util.Map" %>--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
  //  服务器路径
  String sourcePath = "http://innovation.xjtu.edu.cn/webresources/ace-master/assets";
  //  本地路径
//  String sourcePath = "assets";
%>
<html lang="en">
<head>
  <title>创新方法工作平台</title>
  <meta name="description" content="overview &amp; stats"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <%--css--%>
  <link rel="stylesheet" href="<%=sourcePath%>/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="<%=sourcePath%>/css/ace.min.css"/>
  <link rel="stylesheet" href="<%=sourcePath%>/font-awesome/4.5.0/css/font-awesome.min.css"/>
  <link rel="stylesheet" href="<%=sourcePath%>/css/ace-rtl.min.css"/>
  <link rel="stylesheet" href="<%=sourcePath%>/css/ace-skins.min.css"/>
  <link rel="stylesheet" href="<%=sourcePath%>/css/fonts.googleapis.com.css"/>
  <%--js--%>
  <script type="text/javascript" src="<%=sourcePath%>/js/ace-extra.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/ace.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.dataTables.bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery-ui.custom.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.ui.touch-punch.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.easypiechart.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.sparkline.index.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.flot.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.flot.pie.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/jquery.flot.resize.min.js"></script>
  <script type="text/javascript" src="<%=sourcePath%>/js/ace-elements.min.js"></script>
  <%--FISHBONE--%>
  <link rel="stylesheet" href="./css/canvasAdapt.css" type="text/css">
  <link rel="stylesheet" href="./css/main.css" type="text/css"/>
  <link rel="stylesheet" href="./css/freezeRootNode.css" type="text/css"/>
  <link rel="stylesheet" href="./css/demo.css" type="text/css"/>
  <link rel="stylesheet" href="./css/zTreeStyle.css" type="text/css"/>
  <!-- 导入js -->
  <script type="text/javascript" src="js/cpexcel.js"></script>
  <script type="text/javascript" src="js/shim.js"></script>
  <script type="text/javascript" src="js/jszip.js"></script>
  <script type="text/javascript" src="jtopo/csvConvertJson.js"></script>
  <script type="text/javascript" src="js/xlsx.js"></script>
  <script type="text/javascript" src="jtopo/jTopo.js"></script>
  <script type="text/javascript" src="./jtopo/saveImages.js"></script>
  <%--需要单独添加的js、css--%>
  <script type="text/javascript" src="./appStyleJS/buttonAction.js"></script>
  <script type="text/javascript" src="./appStyleJS/logout.js"></script>
  <%--<script type="text/javascript" src="/webresources/js/common/logout.js"></script>--%>
  <script type="text/javascript" src="./appStyleJS/resultReport.js"></script>
  <link rel="stylesheet" href="./appStyleCSS/Extra.css"/>
</head>
<body class="no-skin">
<%@include file="appStyleJSP/jspf/banner.jspf" %>
<div class="main-container ace-save-state" id="main-container">
  <%--侧边栏--%>
  <%@include file="appStyleJSP/jspf/sideBar.jspf" %>
  <%--功能显示区--%>
  <div class="main-content">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
      <ul class="breadcrumb">
        <li>
          <i class="ace-icon fa fa-home home-icon"></i>
          <a href="#" style="font-weight: bold">创新方法工具平台</a>
        </li>
        <li class="active">鱼骨图</li>
      </ul>
    </div>
    <div class="tab-content">
      <%--项目管理--%>
      <div id="projectManagement" class="tab-pane active">
        <div id="table-position">
          <div class="btn-group btn-group-sm" style="padding-left: 15px;">
            <button id="buttonNew" class="btn btn-success" data-toggle="modal" data-target="#newProjectModal">
              <span class="menu-icon fa fa-folder"></span>新建项目
            </button>
          </div>
          <%@include file="/appStyleJSP/jspf/tableData.jspf" %>
        </div>
      </div>
      <%--主功能区--%>
      <div id="mainFunction" class="tab-pane">
        <%@include file="/appStyleJSP/navigationBar/mainFunction.jspf" %>
      </div>
      <%
        if (userInfo == null) {
          out.write("<style> " +
                "#projectManagement,#projectManagementBar,#saveProject" +
                "{display:none}" +
                "</style>");
          out.write("<script>" +
                "$('#mainFunction').addClass('active');" +
                "$('#mainId').addClass('active');" +
                "$('#mainIdA').css('pointer-events', 'auto');" +
                "</script>");
        }
      %>
      <%--word编辑区--%>
      <div id="wordEdit" class="tab-pane">
        <%@include file="/appStyleJSP/navigationBar/wordEdit.jspf" %>
      </div>
      <%--帮助文档--%>
      <div id="help" class="tab-pane">
        <%@include file="/appStyleJSP/navigationBar/help.jspf" %>
      </div>
    </div>
  </div>
  <%@include file="appStyleJSP/jspf/copyright.jspf" %>
</div>
<%@include file="appStyleJSP/jspf/modalFrame.jspf" %>
<script type="text/javascript" src="js/tree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/tree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="./jtopo/canvasAdapt.js"></script>
<script type="text/javascript" src="js/featureButton.js"></script>
<script type="text/javascript" src="jtopo/bone.js"></script>
<script type="text/javascript" src="jtopo/drawing.js"></script>
<script type="text/javascript" src="jtopo/tree.js"></script>
<script type="text/javascript">
    var userName = '<%=userInfo.get("username")%>';
</script>
</body>
</html>