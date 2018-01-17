// 定义初始参数
var counter = 0;
var curProjectNum;
var projectName;

// 表格初始化
$(document).ready(function () {
    if (typeof userName !== 'object')
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
            "order": [[2, "desc"]],//按照序号进行排列
            "ajax": {//请求数据库
                "url": "loadTable",
                "dataSrc": function (json) {//获取返回数据，数据中不只是有data，还包括分页等信息
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
                     {"data": "projectNumber"},
                     {"data": "projectName"},
                     {"data": "projectTime"},
                     {"data": "projectRemark"},
                     {"render": addButtonId}
                 ]
        });

    // 将数据按照顺序进行排列，序号不是项目的真实id
    $('#dynamic-table').DataTable().on('order.dt search.dt', function () {
        $('#dynamic-table').DataTable().column(0, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();
});

function addButtonId(data, type, row, meta) {
    return '<div class="hidden-sm hidden-xs action-buttons">\n' +
         '              <a class="btn btn-xs btn-info" href="#">\n' +
         '                <i class="ace-icon fa fa-eye bigger-120" id="checkButton" onclick="checkProject(' + row.projectNumber + ')">查看</i>\n' +
         '              </a>\n' +
         '              <a class="btn btn-xs btn-danger" href="#">\n' +
         '                <i class="ace-icon fa fa-trash-o bigger-120" id="deleteButton" onclick="removeProject(' + row.projectNumber + ')">删除</i>\n' +
         '              </a>\n' +
         '            </div>';
}

// 移除项目函数
var target;
$(document).click(function () {
    if ($(window.event.target)[0].id === 'deleteButton') {
        target = window.event.target;
    }
});

function removeProject(index) {
    if (confirm("项目删除后将无法恢复，确认要删除吗？")) {
        $('#dynamic-table').DataTable().row($(target).parents("tr")).remove().draw(false);
        $('#deleteProject').modal('hide');
        // 删除数据
        $.ajax({
            type: "post",
            url: "removeProject",
            data: {"projectNumber": index},
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
}

// 添加项目函数
function addProject() {
    // 获取输入框中的内容
    var projectName = $('#projectNameModal')[0].value;
    var projectTime = new Date().toLocaleDateString() + ',' + new Date().getHours() + ':' + new Date().getMinutes();
    var projectRemark = $('#projectRemarkModal')[0].value;
    var data = {
        "projectNumber": 0,
        "projectTime": projectTime,
        "projectName": projectName,
        "projectRemark": projectRemark,
        "userName": userName,
        "result": '',
        "wordResult": ''
    };
    //表格添加数据
    if (projectName === '') {
        alert("请输入项目名！！！");
    } else {
        // 添加数据库
        $.ajax({
            type: "post",
            url: "addProject",
            data: data,
            success: function () {
                console.log("add data successful");
                window.location.reload(true);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
                console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
                console.log("textStatus是：" + textStatus);
                console.log("errorThrown是：" + errorThrown);
            }
        });
        $('#newProjectModal').modal('hide');
    }
}

// 保存项目
function saveProject() {
    var data = {
        "projectNumber": curProjectNum,
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

// 查看项目
function checkProject(index) {
    curProjectNum = index;
    var target = window.event.target;
    projectName =$(target).parents("tr")[0].children[1].innerText;
    $("#main").trigger("click");
    $('#mainIdA').css('pointer-events', 'auto');
    $.ajax({
        type: "post",
        url: "checkProject",
        data: {"projectNumber": index},
        success: function (data) {
            console.log("accept data successful");
            if (data === "") {
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

//导出到word
function exportWord() {
    $("#wordExport").trigger("click");
    $('#wordEditBarA').css('pointer-events', 'auto');
    getWordContext();
}

// 保存word
function saveWord() {
    var data = {
        "projectNumber": curProjectNum,
        "wordResult": $("#WYeditor").html()
    };
    $.ajax({
        type: "post",
        url: "saveResultReport",
        data: data,
        success: function () {
            console.log("wordReport数据保存成功");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("XMLHttpRequest请求状态码：" + XMLHttpRequest.status);
            console.log("XMLHttpRequest状态码：" + XMLHttpRequest.readyState);
            console.log("textStatus是：" + textStatus);
            console.log("errorThrown是：" + errorThrown);
        }
    });
}