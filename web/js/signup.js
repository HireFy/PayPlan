/*发送验证码*/
var signup = $("input[value='SIGN UP']")

var verifyMailButton = $("#verifyMail")

var inputMail = $("input[name='mail']")

var inputMailVerify = $("input[name='mailVerify']")

var inputConfirmPass = $("input[name='confirmPassword']")

var inputPass = $("input[name='password']")

var inputName = $("input[name='name']")

/*TODO 检测验证码按钮是否点击*/
/*验证码按钮状态*/
var isVerifyClicked = false

inputMail.blur(function () {
    checkMailValid()
});

signup.mouseover(function () {
    checkForm()
})

inputConfirmPass.blur(function () {
    console.log("inputConfirmPass.blur()")
    checkPass()
})

inputName.blur(function () {
    checkName()
})

verifyMailButton.click(function () {
    console.log("verifyButtom clicked")
    /*确认验证码按钮点击*/
    isVerifyClicked = true
    sendMailNumber()
})


/*TODO 验证码输入框不能为空*/
function checkMailNumver() {
    var val = inputMailVerify.val()
    if (val == '') {
        return false;
    } else {
        return true;
    }
}

function sendMailNumber() {
    var mailValue = inputMail.val();
    $.ajax({
        type: "POST",
        url: "/verifyMail",
        data: {
            mail: mailValue
        },
        dataType: "json",
        success: function (data) {
            /*console.log(data);
            console.log("success, verifynumber:" + data['number_server'])*/
            verifyMailButton.next().show()
            verifyMailButton.next().text("验证码发送成功")
        },
        error: function (data) {
            console.log("send mail to server Error:" + data)
        }
    })
}

function checkMailValid() {
    /*检测邮箱是否合法是否注册*/
    var value = inputMail.val();
    if (value == '') {
        return false
    }

    var patten = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);
    if (patten.test(value)) {
        var mailValue = $("input[name='mail']").val();
        //检测邮箱是否已经注册
        $.ajax({
            type: "POST",
            url: "/verifyMailExist",
            data: {
                mail: mailValue
            },
            dataType: "json",
            success: function (data) {
                if (data['isExist'] == "true") {
                    inputMail.next().show()
                    inputMail.next().text("邮箱已注册");
                    return false;

                } else {
                    inputMail.next().show()
                    inputMail.next().text("OK");
                    verifyMailButton.show();
                    return true;
                }

            },
            error: function (data) {
                console.log(" verify mail Error connot connect server")
                return false
            }
        })
        // $(this).next().text("OK")
        // $("#verifyMailButton").show()
    } else {
        inputMail.next().show()
        inputMail.next().text("ERROR")
        verifyMailButton.hide()
        return false
    }
    return true
}

function checkForm() {
    /*检查提交表单*/
    /*var nameOk = new Boolean(checkName());
    var passOk = new Boolean(checkPass());
    var mailOk = new Boolean(checkMailValid());*/
    var nameOk = checkName();
    var passOk = checkPass();
    var mailOk = checkMailValid();
    var mailNumberOk = checkMailNumver();

    // console.log("hover!");
    // var objectArray = new Array(inputName, inputPass, inputConfirmPass,
    //     inputMail, inputMailVerify);
    // for (var i = 0; i < objectArray.length; i++) {
    //     if (objectArray[i].val() == '') {
    //         console.log("objectArray[i].val():为空")
    //         signup.attr("type", "button");
    //         signup.next().show();
    //         signup.next().text("有空值");
    //         break;
    //     } else if (i == objectArray.length - 1) {
    //         console.log("objectArray[i].val():不为空")
    //         signup.attr("type", "submit");
    //         signup.next().hide();
    //         /*signup.next().text("有空值");*/
    //     }
    // }


    if (mailOk && passOk && nameOk && isVerifyClicked && mailNumberOk) {
        console.log("mailOk, passOk, nameOk");
        console.log("mailOk?:" + mailOk);
        console.log("passOk?:" + passOk);
        console.log("nameOk?:" + nameOk);
        console.log("isVerifyClicked: " + isVerifyClicked)
        console.log("mailNumberOk:" + mailNumberOk)
        signup.attr("type", "submit");
        signup.next().hide();
    }
    else {
        console.log("something wrong");
        console.log("mailOk?:" + mailOk);
        console.log("passOk?:" + passOk);
        console.log("nameOk?:" + nameOk);
        console.log("isVerifyClicked: " + isVerifyClicked)
        console.log("mailNumberOk:" + mailNumberOk)
        signup.attr("type", "button");
        signup.next().show();
        signup.next().text("something wrong");
    }
}

function checkPass() {
    /*检测两次输入的密码是否一致*/
    var pass_origin = inputPass.val();
    var pass_confirm = inputConfirmPass.val();

    if (pass_confirm == '' || pass_origin == '') {
        console.log("密码检测为空")
        inputConfirmPass.next().show();
        inputConfirmPass.next().text("密码不能为空");
        return false
    }

    if (pass_confirm == pass_origin) {
        inputConfirmPass.next().hide();
        return true;
    } else {
        inputConfirmPass.next().show();
        inputConfirmPass.next().text("两次输入不一致");
        return false;
    }
}

function checkName() {
    /*检测用户名是否存在*/
    var registerName = inputName.val()
    if (registerName == '') {
        inputName.next().show();
        inputName.next().text("用户名不为空");
        return false;
    }
    $.ajax({
        type: "POST",
        url: "/verifyRegisterName",
        data: {
            registerName: registerName
        },
        dataType: "json",
        success: function (data) {
            console.log("signup checkName ajax: " + data)
            if (data['isExist'] == 'true') {
                inputName.next().show();
                inputName.next().text("用户名已存在");
                return false;
            } else {
                inputName.next().hide();
                return true;
            }
        },
        error: function (data) {
            console.log("register name error connot connect server")
            return false;
        }
    });
    return true;

}


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
