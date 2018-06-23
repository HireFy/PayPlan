var inputName = $("input[name='name']");
var inputPass = $("input[name='password']");
var inputNameNextP = inputName.next();
var inputPassNextP = inputPass.next();
var loginButton = $("input[value='login']");
var loginButtonNextP = loginButton.next();

inputName.blur(function () {
    checkName()
})

inputPass.blur(function () {
    checkPass()
})

loginButton.mouseover(function () {
    console.log("login hover")
    checkForm()
})


/*ajax传输数据有问题*/
// loginButton.click(function () {
//     var name = inputName.val()
//     var pass = inputPass.val()
//     $.ajax({
//         type:"post",
//         url:"/login",
//         data:{
//             name:name,
//             pass:pass
//         },
//         datatype:"json",
//         success:function (data) {
//             console.log("recieve data successful!")
//             var data_parsed = JSON.parse(data)
//             console.log("data_parsed['isExist']: " + data_parsed['isExist'])
//             if (data_parsed['isExist'] == 'false') {
//                 console.log("should show info about wrong name or pass")
//                 loginButtonNextP.show()
//                 loginButtonNextP.text("用户名或密码不正确")
//             }
//         },
//         error:function (data) {
//
//         }
//     })
// })

function checkForm() {

    var nameOk = checkName();
    var passOk = checkPass();

    if (nameOk && passOk) {
        loginButton.attr("type", "submit");
    } else {
        loginButton.attr("type", "button");
    }
}

function checkName() {
    var name = inputName.val();
    if (name == '') {
        inputNameNextP.show()
        inputNameNextP.text("登录名不为空")
        return false;
    } else {
        inputNameNextP.hide()
        return true;
    }
}

function checkPass() {
    var pass = inputPass.val();
    if (pass == '') {
        inputPassNextP.show()
        inputPassNextP.text("密码不能为空")
        return false;
    } else {
        inputPassNextP.hide()
        return true;
    }
}