$(function(){
    console.log("Start ");


    $('#UserList').val(localStorage["UserList"]);

    $('#entiry').click(function(e){
        console.log($('#UserList').val());
        localStorage["UserList"]=$('#UserList').val();
    });
});
/*
$(function(){
    console.log("Start");
    $('#GroupSelect').change(function(){
        console.log("Selection Changed");
        //選択したvalue値を変数に格納
        var val = $(this).val();

        //選択したvalue値をp要素に出力
        $('GroupName').text(val); 
    });
    $('#entiry').click(function(e){
        console.log($('#UserList'));
    });

});*/
