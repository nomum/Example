$(function(){
    console.log("Start ");


    $('#UserList').val(localStorage["UserList"]);

    $('#entiry').click(function(e){
        console.log($('#UserList').val());
        localStorage["UserList"]=$('#UserList').val();
    });

    $('#teamSelect').change(function(){
        var str = $(this).val();
        if (str === "新規作成"){
            $('teameName').val("");
            $('teameName').disabled = false;
            //members
        }else {
            $('teameName').disabled = true;
        }
    });
    $('#memberAdd').click(function(){
        var member = $('#member').val();
        console.log(member);
        var appendText = '<optino value="' +member+ '">' + member + '</option>'  ;
        console.log(appendText);
        $option = $('<option>')
            .val(member)
            .text(member);
        $('#members').append($option);

        console.log($('#members'));
        
    });
    $('#memberDelete').click(function(){
        $('#members > option:selected').remove();
    });
    $('#teamUpdate').click(function(){
        console.log("[teamUpdate - click]--1")
        var teamname = $('#teameName').val();
        if ( teamname == ""){
            alert("チーム名が入力されていません。");
            return ;
        }
        console.log("[teamUpdate - click]--2")
        if ($('#members').val() == ""){
        }
        console.log("[teamUpdate - click]--3")
        var storage = localStorage["UserList2"];
        console.log(storage)
        if (storage == null || storage == ""){
            console.log("userlist2 is null");
            storage = {};
        }
        console.log("[teamUpdate - click]--4")
        /*
        var userList2 = storage[teamname];
        if (userList2 == null){
            console.log("teamename [" + teamname + "] not found");
            userList2 = [];
        }
        userListCreate();
        userList2[teamname] = userListCreate();
        */
        
        var userList =  userListCreate();
        console.log(userList);
        //storage[teamname] = userListCreate();
        storage[teamname] =userList;
        console.log("----teamname-------")
        console.log(teamname);
        console.log("----strages-------")
        console.log(storage);
        console.log("----strages-------")
        var temp = JSON.stringify( storage);
        console.log("temp------")
        console.log(temp)
        localStorage["UserList2"] = temp;
        //var s = localStorage["UserList2"];
        //console.log(s);

    });
    $('#teamStatus').click(function(){
        //var storage = JSON.parse( localStorage["UserList2"]);
        var temp = localStorage["UserList2"];
        var storage =  JSON.parse(temp);
        console.log(temp);
        console.log(storage);
        //for (key in storage) {
        //    console.log(key);
        //}
        /*
        Object.keys(storage).forEach(function(key){
            console.log(key);
        });
        */
    });
    $('#teamAllDelete').click(function(){
        localStorage["UserList2"] = null;
    });

    userListCreate = function(){
        var list = [];
        //console.log($('#members > option'));
        $('#members > option').each(function(index , e){
            //console.log($(e).val());
            list.push($(e).val());
        });
        console.log("userListCreate---------------------")
        console.log(list);
        //var list[] = 
        return list;
    }

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
