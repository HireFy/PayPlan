/*发送验证码*/
$("#verifyMail").click(function () {
    var mailValue = $("input[name='mail']").val();
    $.ajax({
        type: "POST",
        url:"/verifyMail",
        data:{mail:mailValue},
        dataType:"json",
        success: function (data) {
            /*console.log(data);
            console.log("success, verifynumber:" + data['number_server'])*/
            $("#verifyMail").next().show()
            $("#verifyMail").next().text("验证码发送成功")
        },
        error:function (data) {
            console.log("send mail to server Error:" + data)
        }
    })
});

/*检测邮箱是否合法是否注册*/
$("input[name='mail']").blur(function () {
    var value = $(this).val();
    var patten = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);
    if (patten.test(value)) {
        $(this).next().show()
        var mailValue = $("input[name='mail']").val();
        //检测邮箱是否已经注册
        $.ajax({
            type: "POST",
            url:"/verifyMailExist",
            data:{mail:mailValue},
            dataType:"json",
            success: function (data) {
                if (data['isExist'] == "true") {
                    $("input[name='mail']").next().text("邮箱已注册");

                } else {
                    $("input[name='mail']").next().text("OK");
                    $("#verifyMail").show();
                }

            },
            error:function (data) {
                alert("Error")
            }
        })
        // $(this).next().text("OK")
        // $("#verifyMail").show()
    }
    else {
        $(this).next().show()
        $(this).next().text("ERROR")
        $("#verifyMail").hide()
    }
})

/*提交表单*/



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