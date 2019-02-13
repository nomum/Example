'use strict'
{

    var userList = document.querySelector('#UserList')
    var teamSelect = document.querySelector('#teamSelect')
    var teamName = document.querySelector('#teameName')
    var members = document.querySelector('#members')
    var memberAdd = document.querySelector('#memberAdd')
    var member = document.querySelector('#member')
    /**
     * 画面初期処理
     * <li>
     *   <ul>初期表示時にユーザリストの除法を読み込む
     * </li>
     */
    document.addEventListener("DOMContentLoaded", function(){
        console.log("[Start]DOMContentLoaded")
        //console.log(localStorage["UserList"])
        userList.value = localStorage["UserList"]
        
    });
    /**
     * 登録ボタン押下処理
     * <ul>
     *   <li>ローカルストレージに設定を保存する.</li>
     * </ul>
     */
    document.querySelector('#entry').addEventListener('click',()=>{
        //alert("test")
        console.log("[Start]entory clicked")
        console.log(`save to local storage :${userList.value}`   )
        //localStorage["UserList"]=userList.text;
        localStorage.setItem("UserList",userList.value)
    });
    teamSelect.addEventListener('change',()=>{
        var str = teamSelect.value
        console.log(str)
        if (str === "新規作成"){
            teameName.value=""
            teamName.disabled = false;
        }else {
            teamName.disabled = true;
        }
    })
//     });
//     $('#memberAdd').click(function(){
//         var member = $('#member').val();
//         console.log(member);
//         var appendText = '<optino value="' +member+ '">' + member + '</option>'  ;
//         console.log(appendText);
//         $option = $('<option>')
//             .val(member)
//             .text(member);
//         $('#members').append($option);

//         console.log($('#members'));
        
//     });
//     $('#memberDelete').click(function(){
//         $('#members > option:selected').remove();
//     });
    /**
     * メンバー追加ボタンクリック
     */
    // memberAdd.addEventListener('click',()=>{
    //     //const addMember = member.value
    //     //console.log(`add member at {$addMember}`)

    // })

    //     $('#memberAdd').click(function(){
//         var member = $('#member').val();
//         console.log(member);
//         var appendText = '<optino value="' +member+ '">' + member + '</option>'  ;
//         console.log(appendText);
//         $option = $('<option>')
//             .val(member)
//             .text(member);
//         $('#members').append($option);

//         console.log($('#members'));
        
//     });
//     $('#memberDelete').click(function(){
//         $('#members > option:selected').remove();
//     });
    /**
     * 画面初期処理
     * @param {number} duration スクロール時間のミリ秒です。
     */
}

// $(function(){
//     console.log("Start ");


//     $('#UserList').val(localStorage["UserList"]);

//     $('#entiry').click(function(e){
//         console.log($('#UserList').val());
//         localStorage["UserList"]=$('#UserList').val();
//     });

//     $('#teamSelect').change(function(){
//         var str = $(this).val();
//         if (str === "新規作成"){
//             $('teameName').val("");
//             $('teameName').disabled = false;
//             //members
//         }else {
//             $('teameName').disabled = true;
//         }
//     });
//     $('#memberAdd').click(function(){
//         var member = $('#member').val();
//         console.log(member);
//         var appendText = '<optino value="' +member+ '">' + member + '</option>'  ;
//         console.log(appendText);
//         $option = $('<option>')
//             .val(member)
//             .text(member);
//         $('#members').append($option);

//         console.log($('#members'));
        
//     });
//     $('#memberDelete').click(function(){
//         $('#members > option:selected').remove();
//     });

//     $('#teamUpdate').click(function(){
//         console.log("[teamUpdate - click]--1")
//         var teamname = $('#teameName').val();
//         console.log('teamNmae:' + teamname);
//         if ( teamname == ""){
//             alert("チーム名が入力されていません。");
//             return ;
//         }
//         console.log("[teamUpdate - click]--2")
//         if ($('#members').val() == ""){
//         }
//         console.log("[teamUpdate - click]--3")
//         var storage = localStorage["UserList2"];
//         console.log("storage---------------")
//         console.log(storage)
//         console.log("storage---------------")
//         console.log("storage == null : " + storage === null)
//         console.log("storage == undefined : " + storage === undefined)
//         console.log("typeof :" + typeof storage)
//         console.log("storage=== :" + storage === "")
//         console.log("storage== :" + storage == "")
//         //if (storage == null ){
//         if (storage === undefined || storage === null){
//         //if (storage == "null" || storage == ""){
//             console.log("userlist2 is null");
//             //storage = {};
//             storage =  new Array();

//         }
//         console.log("[teamUpdate - click]--4")
//         console.log("storage---------------")
//         console.log(storage)
//         console.log("storage---------------")
//         /*
//         var userList2 = storage[teamname];
//         if (userList2 == null){
//             console.log("teamename [" + teamname + "] not found");
//             userList2 = [];
//         }
//         userListCreate();
//         userList2[teamname] = userListCreate();
//         */
        
//         var userList =  userListCreate();
//         //console.log(userList);
//         //storage[teamname] = userListCreate();
//         storage[teamname] =userListCreate();
        
//         console.log("----storage[teamname]-------")
//         console.log(storage[teamname])
//         console.log("----teamname-------")
//         console.log(teamname);
//         console.log("----strages-------")
//         console.log(storage);
//         console.log("----strages-------")
//         var temp = JSON.stringify( storage);
//         console.log("temp------")
//         console.log(temp)
//         localStorage["UserList2"] = temp;
//         //var s = localStorage["UserList2"];
//         //console.log(s);

//     });
//     $('#teamStatus').click(function(){
//         //var storage = JSON.parse( localStorage["UserList2"]);
//         var temp = localStorage["UserList2"];
//         var storage =  JSON.parse(temp);
//         console.log(temp);
//         console.log(storage);
//         //for (key in storage) {
//         //    console.log(key);
//         //}
//         /*
//         Object.keys(storage).forEach(function(key){
//             console.log(key);
//         });
//         */
//     });
//     $('#teamAllDelete').click(function(){
//         localStorage["UserList2"] = null;
//     });

//     userListCreate = function(){
//         var list = [];
//         //console.log($('#members > option'));
//         $('#members > option').each(function(index , e){
//             //console.log($(e).val());
//             list.push($(e).val());
//         });
//         console.log("userListCreate---------------------")
//         console.log(list);
//         //var list[] = 
//         return list;
//     }

// });
// /*
// $(function(){
//     console.log("Start");
//     $('#GroupSelect').change(function(){
//         console.log("Selection Changed");
//         //選択したvalue値を変数に格納
//         var val = $(this).val();

//         //選択したvalue値をp要素に出力
//         $('GroupName').text(val); 
//     });
//     $('#entiry').click(function(e){
//         console.log($('#UserList'));
//     });

// });*/
