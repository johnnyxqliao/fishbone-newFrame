//获得内容
function getWordContext() {
    setCustomContext();
}

//定制初始化内容
function setCustomContext() {
    var editor = $("#WYeditor");
    var title = "本次鱼骨图项目（" + projectName + "）分析结果为：";
    var chap1 = "  通过小组的不断讨论，我们将本问题的人员，机器，材料，方法，环境，测量等因素的划分为如下鱼骨图所示：";
    var chap2 = "  从图上可知，我们需要在***和***优化和改进现有技术。";
    var img1 = $("#canvas")[0];  //选择页面中的img元素
    var image1 = new Image();
    if (img1 != null) {
        image1.src = img1.toDataURL("image/png");
    }
    title = "<h2> " + title + "</h2>";
    chap1 = "<h3>" + chap1 + "</h3>";
    chap2 = "<h3>" + chap2 + "</h3>";
    editor.append(title, chap1, image1, chap2);
}

$(document).ready(function () {
    $('#wordEditBarA').click(function () {
        $("#WYeditor").empty();
        $("#WYeditor").focus();  //定位光标到文本编辑区
        bindToolbar(); //绑定toolbar
        bindImageInsert(); //绑定插入图片按钮
        initSelectionMarker(); //编辑区初始化鼠标事件
        setCustomContext();
    });
});

var range; //全局变量，选择的文本范围或光标当前位置
function bindToolbar() {
    var toolbar = $("a[data-edit]");
    //绑定点击事件
    for (var i = 0; i < toolbar.length; i++) {
        var onebar = toolbar.eq(i);
        onebar["0"].onclick = function () {
            var tempbar = onebar;  // 重新定义变量，解决闭包只能访问最后一个变量问题
            return function () {
                if (tempbar["0"].classList.contains("active")) { // 实现按钮效果
                    tempbar["0"].classList.remove("active");
                } else {
                    tempbar["0"].classList.add("active");
                }
                var lab = tempbar.data("edit").split(" ")[0];
                var value = tempbar.data("edit").split(" ")[1];

                var selection = window.getSelection();
                selection.removeAllRanges();
                selection.addRange(range); //将目前的Selection替换为全局设定的
                $("#WYeditor").focus();
                document.execCommand(lab, false, value);  //编辑命令
            };
        }();
        onebar = null; //释放对象，防止内存泄露
    }
}

//插入图片
function bindImageInsert() {
    var imagebar = $("input[data-editImage]");
    var insertbar = imagebar.eq(1);
    $("#Wyimage")["0"].onclick = function () {
        insertbar.click();
        insertbar["0"].onchange = function () {
            var fileInfo = this.files["0"];
            $.when(readFileIntoDataUrl(fileInfo)).done(function (dataUrl) {
                document.execCommand('insertimage', false, dataUrl);
                resizeImage(); //插入图片之后，立即设定尺寸

            }).fail(function (e) {
                console.log("failed to read image");
            });
            this.value = ''; //可以连续两次读取同一张图片
        };
    };
    imagebar = null;
}

//编辑区域内容选定，toolbar显示效果
//为编辑区初始化鼠标事件
function initSelectionMarker() {
    var editor = $("#WYeditor");
    editor.attr('contenteditable', true)
         .on('mouseup  mouseout', function () {
             var toolbar = $("a[data-edit]");

             var sel = window.getSelection();
             if (sel.getRangeAt && sel.rangeCount) {
                 range = sel.getRangeAt(0);    //鼠标离开文本框区域，初始化range为文本框选择的内容
             }

             for (var i = 0; i < toolbar.length; i++) {
                 var onebar = toolbar.eq(i);
                 var command = onebar.data("edit").split(" ")[0];
                 if (document.queryCommandState(command)) {
                     onebar["0"].classList.add("active");
                 } else {
                     onebar["0"].classList.remove("active");
                 }
             }
             toolbar = null;

         });
}

//设置图片尺寸
function resizeImage() {
    var imgelements = $("#WYeditor img");
    if (imgelements.length > 0) {
        imgelements.css("width", "200px");
    }
}

//读入的图片转成base64编码
function readFileIntoDataUrl(fileInfo) {
    var loader = $.Deferred();
    var fReader = new FileReader();
    fReader.onload = function (e) {
        loader.resolve(e.target.result);
    };
    fReader.onerror = loader.reject;
    fReader.onprogress = loader.notify;
    fReader.readAsDataURL(fileInfo);
    return loader.promise();
}
