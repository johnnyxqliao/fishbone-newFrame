function gotoLogin() {
    var URL = window.location.href;
    this.location = "http://innovation.xjtu.edu.cn/singleSignOnServer/user/CASLogin?serviceURL=" + URL;
}

function logout() {
    //获取当前URL
    var URL = window.location.href;
    //清楚token
    var date = new Date();
    date.setTime(date.getTime() - 10000);
    // document.cookie = "token" + "=a; expires=" + date.toGMTString();
    document.cookie = "token" + "=a;domain=" + document.domain + ";path=/; expires=" + date.toGMTString();
    //跳转登出
    // window.location.href = "http://innovation.xjtu.edu.cn/singleSignOnServer/user/logout?serviceURL=${serviceURL}";
    this.location = "http://innovation.xjtu.edu.cn/singleSignOnServer/user/logout?serviceURL=" + URL;
}