<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
  String sourcePath = "http://innovation.xjtu.edu.cn/webresources/ace-master/assets";
%>
<html>
<head>
  <%--<script type="test/javascript" src="test.js"></script>--%>
    <script type="text/javascript" src="<%=sourcePath%>/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<button onclick="test1()" id="Id">test</button>
<h1>this is a test</h1>
<button onclick="test2()" id="asdf">secondButton</button>
<script>
  function test1() {
    console.log("测试按钮函数执行了");
  }

  function test2() {
    console.log("第二个按钮执行了");
    $("#Id").trigger("click")
    // document.getElementById('Id').click;
  }

</script>
</body>
</html>