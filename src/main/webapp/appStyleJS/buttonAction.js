// 定义初始参数
var counter = 0;
var checkDelete = '<div class="hidden-sm hidden-xs action-buttons" >\n' +
   '              <a class="blue" href="#">\n' +
   '                <i class="ace-icon fa fa-search-plus bigger-130" onclick="checkProject()"></i>\n' +
   '              </a>\n' +
   '              <a class="red" href="#">\n' +
   '                <i class="ace-icon fa fa-trash-o bigger-130" data-toggle="modal" data-target="#deleteProject"  id="deleteButton"></i>\n' +
   '              </a>\n' +
   '            </div>';
// 表格初始化
$(document).ready(function () {
  if (userName !== 'anon')
    $('#dynamic-table').DataTable({
      language: {//表格汉化
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
          "sFirst": "首页",
          "sPrevious": "上页",
          "sNext": "下页",
          "sLast": "末页"
        },
        "oAria": {
          "sSortAscending": ": 以升序排列此列",
          "sSortDescending": ": 以降序排列此列"
        }
      },
      "order": [[0, "desc"]],//按照序号进行排列
      "ajax": {//请求数据库
        "url": "loadTable",
        "data": {"userName": userName},
        "dataSrc": function (json) {//获取返回数据，数据中不只是有data，还包括分页等信息
          json.data.forEach(function (element, value, array) {
            if (counter <= element.projectId) {
              counter = element.projectId;
            }
          });
          counter++;
          return json.data;
        },
        "error": function (XMLHttpRequest, textStatus, errorThrown) {//返回错误日志
          console.log("添加数据失败");
          console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
          console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
          console.log("textStatus是：" + textStatus);
          console.log("errorThrown是：" + errorThrown);
        }
      },
      "columns":
         [
           {"data": "projectId"},
           {"data": "projectTime"},
           {"data": "projectName"},
           {"data": "projectRemark"},
           {"sDefaultContent": checkDelete}
         ]
    })
    ;
});
// 移除项目函数
var target;
$(document).click(function () {
  if ($(window.event.target)[0].id === 'deleteButton') {
    target = window.event.target;
  }
});

function removeProject() {
  $('#dynamic-table').DataTable().row($(target).parents("tr")).remove().draw(false);
  $('#deleteProject').modal('hide');
  // 删除数据
  var data = {
    "counter": $(target).parents("tr")[0].children[0].innerText,
    "username": userName
  };
  $.ajax({
    type: "post",
    url: "removeProject",
    data: data,
    // dataType: "json",
    success: function () {
      console.log("remove data successful");
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
      console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
      console.log("textStatus是：" + textStatus);
      console.log("errorThrown是：" + errorThrown);
    }
  });
}

// 添加项目函数
function addProject() {
  // 获取输入框中的内容
  var projectName = $('#projectNameModal')[0].value;
  var projectTime = new Date().toLocaleDateString() + ',' + new Date().getHours() + ':' + new Date().getMinutes();
  var projectRemark = $('#projectRemarkModal')[0].value;
  var tableAdd = $('#dynamic-table').DataTable();
  var data = {
    "projectId": counter,
    "projectTime": projectTime,
    "projectName": projectName,
    "projectRemark": projectRemark
  };
  tableAdd.row.add(data).draw(false);
// 添加数据库
  $('#newProjectModal').modal('hide');
  data['userName'] = userName;
  data['result'] = "NullResult";
  $.ajax({
    type: "post",
    url: "addProject",
    data: data,
    success: function (data) {
      console.log("add data successful");
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
      console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
      console.log("textStatus是：" + textStatus);
      console.log("errorThrown是：" + errorThrown);
    }
  });
  counter++;
}

// 保存项目
function saveProject() {
  // console.log(window.event.target);
  var data = {
    "username": userName,
    "Id": checkId,
    "exportData": JSON.stringify(exportData)
  };
  console.log("鱼骨图数据为：" + exportData);
  $.ajax({
    type: "post",
    url: "saveProject",
    data: data,
    success: function (data) {
      console.log("请求接收到的数据是：" + data);
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
      console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
      console.log("textStatus是：" + textStatus);
      console.log("errorThrown是：" + errorThrown);
    }
  });
}

var checkId;

// 查看项目
function checkProject() {
  var target = window.event.target;
  checkId = $(target).parents("tr")[0].children[0].innerText;
  var data = {
    "username": userName,
    "Id": checkId
  };
  // 跳转到主功能界面
  $("#main").trigger("click");

  $.ajax({
    type: "post",
    url: "checkProject",
    data: data,
    success: function (data) {
      console.log("accept data successful");
      if (data === 'NullResult') {
        alert("这是一个新项目，尽情在canvas上发挥你的聪明才智吧！！！");
      } else {
        frameLoad(JSON.parse(data));
        exportData = JSON.parse(data);
        alert("项目马上开始加载，继续你的工作吧！！！");
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
      console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
      console.log("textStatus是：" + textStatus);
      console.log("errorThrown是：" + errorThrown);
    }
  });
}

// 绘制鱼骨图
function frameLoad(json) {
  excelData = json;
  excelData = init(excelData);
  excelData['open'] = true;
  zNodes = [excelData];
  zNodes[0].children.splice(6, 1);
  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
  redraw();
}