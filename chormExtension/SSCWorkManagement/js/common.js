$(function(){
	//window.alert("hoge");

	// ページ内を加工
    //console.log("aaaaa");
    /*
	$("body").html(
		$("body").html().replace( /ー/g, "━━━(ﾟ∀ﾟ)━━━" )
    );
    */
    console.log($("#ScheduleContent div:first"));
    $("#ScheduleContent div:first").append("<input id='customFilter' style='font-weight: bold;padding: 0.25em 0.5em;text-decoration: none;color: #00BCD4;background-color: #ECECEC;border-style: none;  border-radius: 0.5em;    ' type='button' value='チーム絞り込み' >")
    //console.log("a");
    

    /*
chrome.runtime.sendMessage({action: "getLocalStorage"}, function(response) {
  if (response["message"]) {
    alert(response["message"]);
  } else {
    alert("Hello, world!");
  }
});
    */

   eraseRow = function ( teams){
        $("#ScheduleTable  tr").each(function(i, elem) {
            //console.log(i + ': ' + $(elem).text());
            var cells =$(elem).children();
            var id = $(cells[1]).text().trim();
            console.log(id);
            //console.log($(cells[1]).text().trim());
            //console.log($(elem).eq(2).text())
            if (teams[id]){
                console.log("hit : " + $(cells[1]).text().trim() );
            }else{
                $(elem).remove();
            }

        });
    };

    $("#customFilter").on("click",function(e){
        console.log("call customFilter click");
        chrome.runtime.sendMessage({action: "getLocalStorage"}, function(response) {
            if (response["UserList"]) {
                var userList = response["UserList"];
                console.log("userlist : " + userList);
                var teams = [];
                teams["名前"] = "名前";
                /*
                var userListSplitResult = userList.split(',');
                console.log("userListSplitResult : " + userListSplitResult);
                for ( user in userListSplitResult){
                    teams[user] = user;
                    console.log("list add :" + user);
                }
                */
                userList.split(",").forEach(val => {
                    teams[val] = val;
                    console.log("list add :" + val);
                    
                });
                console.log(teams);
                eraseRow(teams);
            }else {
                return ;
            }
            /*
            if (response["message"]) {
              alert(response["message"]);
            } else {
              alert("Hello, world!");
            }*/
        });
    });
/*
    $("#customFilter").on("click",function(e){
        console.log("call customFilter click");

        //var teams = ["00122","00154","00181","00186","00206","00246"];

        var userList = localStorage["UserList"];
        console.log(userList);
        var teams = [];
        teams["名前"] = "名前";*/
        /*
        var userListSplitResult = userList.split(',');
        userListSplitResult.array.forEach(element => {
            teams[element] = element;
        });
        */
            
        /*
        var teams = [];
        teams["名前"] = "名前";
        teams["0122"] = "0122";
        teams["0154"] = "0154";
        teams["0181"] = "0181";
        teams["0186"] = "0186";
        teams["0206"] = "0206";
        teams["0246"] = "0246";
        */
        /*
        var teams = {
            "00122": "00122",
            "00154": "00154",
            "00181": "00181",
            "00186": "00186",
            "00206": "00206",
            "00246": "00246",
        };*/
        /*
        $("#ScheduleTable  tr").each(function(i, elem) {
            //console.log(i + ': ' + $(elem).text());
            var cells =$(elem).children();
            var id = $(cells[1]).text().trim();
            console.log(id);
            //console.log($(cells[1]).text().trim());
            //console.log($(elem).eq(2).text())
            if (teams[id]){
                console.log("hit : " + $(cells[1]).text().trim() );
            }else{
                $(elem).remove();
            }

        });*/
        //console.log("call customFilter click-2");
        /*
        var table = document.getElementById('ScheduleTable');
        console.log(table);
        var rowLength = table.rows.length;

        for(var i=0; i<rowLength; i+=1){
          var row = table.rows[i];
        
          //your code goes here, looping over every row.
          //cells are accessed as easy

          console.log(row.cells[1]);
        */

          /*
          var cellLength = row.cells.length;
          for(var y=0; y<cellLength; y+=1){
            var cell = row.cells[y];
            //do something with every cell here
          }*/
        //}


    //});

});