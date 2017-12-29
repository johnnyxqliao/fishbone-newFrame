// 定义初始参数
var counter = 0;
var checkDelete = '<div class="hidden-sm hidden-xs action-buttons">\n' +
   '              <a class="blue" href="#">\n' +
   '                <i class="ace-icon fa fa-search-plus bigger-130"></i>\n' +
   '              </a>\n' +
   '              <a class="red" href="#">\n' +
   '                <i class="ace-icon fa fa-trash-o bigger-130" data-toggle="modal" data-target="#deleteProject"  id="deleteButton"></i>\n' +
   '              </a>\n' +
   '            </div>';
// 表格初始化
$(document).ready(function () {
  $('#dynamic-table').DataTable({
    language: {//汉化
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
      "dataSrc": function (json) {
        json.data.forEach(function (element, value, array) {
          if (counter <= element.projectNumber) {
            counter = element.projectNumber;
          }
        });
        counter++;
        return json.data;
      }
    },
    "columns": [
      {"data": "projectNumber"},
      {"data": "projectTime"},
      {"data": "projectName"},
      {"data": "projectRemark"},
      {
        "sDefaultContent": checkDelete
      }
    ]
  });
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
  var data = {"counter": $(target).parents("tr")[0].children[0].innerHTML};
  $.ajax({
    type: "post",
    url: "removeProject",
    data: data,
    dataType: "json",
    success: function () {
      console.log("add data successful");
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
    "projectNumber": counter,
    "projectTime": projectTime,
    "projectName": projectName,
    "projectRemark": projectRemark
  };
  tableAdd.row.add(data).draw(false);
// 添加数据库
  $('#newProjectModal').modal('hide');

  $.ajax({
    type: "post",
    url: "addProject",
    data: data,
    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    success: function (data) {
      console.log("add data successful");
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("添加数据失败");
      console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
      console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
      console.log("textStatus是：" + textStatus);
    }
  });
  counter++;
}