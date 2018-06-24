var uuidP = $("p.uuidP")
var button = $("button");
var userName = $("h2")

/*userName.mouseover(function () {
    console.log(userName.text)
})*/

showOrHideUuidP();

function showOrHideUuidP() {
    if(uuidP.text() == ''){
        button.show()
    }
    else{
        button.hide()
    }
}


button.click(function () {
    var name = userName.text();
    $.ajax({
        type: "post",
        url: "/getUuid",
        data:{
            name: name
        },
        dataType: "json",
        success:function (data) {
            if (data['uuid'] != 'error') {
                uuidP.text(data['uuid']);
                showOrHideUuidP();
            } else {
                uuidP.text("发生了错误")
            }

        },
        error:function (data) {
            console.log("error when get uuid from server!")
        }
    })
})