var uuidBox = $("p.uuidBox")
var button = $("button");
var name = $("h1");

if(uuidBox.text() == ''){
    button.show()
}
else{
    button.hide()
}

button.click(function () {
    $.ajax({
        type: "post",
        url: "/getUuid",
        data:{
            name: name.text()
        },
        dataType: "json",
        success:function (data) {
            uuidBox.text(data['uuid'])
        },
        error:function (data) {
            console.log("error when get uuid from server!")
        }
    })
})