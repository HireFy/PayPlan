var uuidBox = $("p.uuidBox")
var button = $("button");
var userName = $("h2")

/*userName.mouseover(function () {
    console.log(userName.text)
})*/

showOrHideUuidBox();

function showOrHideUuidBox() {
    if(uuidBox.text() == ''){
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
                uuidBox.text(data['uuid']);
                showOrHideUuidBox();
            } else {
                uuidBox.text("发生了错误")
            }

        },
        error:function (data) {
            console.log("error when get uuid from server!")
        }
    })
})