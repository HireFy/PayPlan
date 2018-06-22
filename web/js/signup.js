/*发送验证码*/
var signup = $("input[value='SIGN UP']")

var verifyMailButton = $("#verifyMail")

var inputMail = $("input[name='mail']")

var inputMailVerify = $("input[name='mailVerify']")

var inputConfirmPass = $("input[name='confirmPassword']")

var inputPass = $("input[name='password']")

var inputName = $("input[name='name']")


verifyMailButton.click(function() {
    var mailValue = inputMail.val();
    $.ajax({
        type: "POST",
        url: "/verifyMail",
        data: {
            mail: mailValue
        },
        dataType: "json",
        success: function(data) {
            /*console.log(data);
            console.log("success, verifynumber:" + data['number_server'])*/
            verifyMailButton.next().show()
            verifyMailButton.next().text("验证码发送成功")
        },
        error: function(data) {
            console.log("send mail to server Error:" + data)
        }
    })
});

/*检测邮箱是否合法是否注册*/
inputMail.blur(function() {
    var value = inputMail.val();
    if (value == '') {
        return
    }
    var patten = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);
    if (patten.test(value)) {
        inputMail.next().show()
        var mailValue = $("input[name='mail']").val();
        //检测邮箱是否已经注册
        $.ajax({
            type: "POST",
            url: "/verifyMailExist",
            data: {
                mail: mailValue
            },
            dataType: "json",
            success: function(data) {
                if (data['isExist'] == "true") {
                    inputMail.next().text("邮箱已注册");

                } else {
                    inputMail.next().text("OK");
                    verifyMailButton.show();
                }

            },
            error: function(data) {
                console.log(" verify mail Error")
            }
        })
        // $(this).next().text("OK")
        // $("#verifyMailButton").show()
    } else {
        inputMail.next().show()
        inputMail.next().text("ERROR")
        verifyMailButton.hide()
    }
})

/*提交表单*/
/*todo 检查所有要填的表单
* */
signup.mouseover(function() {
    var objectArray = new Array(inputName, inputPass, inputConfirmPass,
                                inputMail, inputMailVerify);
    for (x in objectArray) {
        if (x.val() == '') {
            signup.attr("disabled", true);
            signup.next().show();
            signup.next().text("有空值");
            break;
        }
    }

})

/*检测两次输入的密码是否一致*/
inputConfirmPass.blur(function() {
    var pass_origin = inputPass.val();
    var pass_confirm = inputConfirmPass.val();

    if (pass_confirm == pass_origin) {
        inputConfirmPass.next().hide()
    } else {
        inputConfirmPass.next().show();
        inputConfirmPass.next().text("两次输入不一致");
    }
})

/*检测用户名是否存在*/
inputName.blur(function() {
    var registerName = inputName.val()
    $.ajax({
        type: "POST",
        url: "/verifyRegisterName",
        data: {
            registerName: registerName
        },
        dataType: "json",
        success: function(data) {
            if (data['isExist'] == 'true') {
                inputName.next().show();
                inputName.next().text("用户名已存在");
            } else {
                inputName.next().hide();
            }
        },
        error: function(data) {
            console.log("register name error")
        }
    })
})


/*检测验证码输入是否正确*/
/*检测验证码输入不应该在用户输入的时候检测
 * 应该再用户提交整个表单的时候检测*/
// $("input[name='mailVerify']").blur(function () {
//     var number = $(this).val();
//     $.ajax({
//         type: "POST",
//         url:"/verifyMail",
//         data:{number:number},
//         dataType:"json",
//         success: function (data) {
//             if (data['isCorrect'] == "true") {
//             } else {
//                 $("input[name='mailVerify']").next().text("验证码错误")
//             }
//         },
//         error: function (data) {
//             alert(data)
//         }
//     })
// })
