
//checkOverlap("abcde","bc")
checkOverlap2("abcde","bc")
checkOverlap2("bcde","bc")
checkOverlap2("cde","bc")
checkOverlap2("abc","bc")
checkOverlap2("ab","bc")

function checkOverlap(str1 , str2){

    console.log("checkOverlap str1:" + str1 + "  str2:" + str2);
    
    //最初の文字からずらしていく
    /*
    int str2Start = 0;
    int str2End = 0;
    int searchlen = 0;
    for ()
    */
}

function checkOverlap2(str1 , str2){

    console.log("checkOverlap str1:" + str1 + "  str2:" + str2);
    

    //含まれているか？
    if (str1.indexOf(str2) != -1){
        console.log("含まれている");
        return true;
    }
    
    //最初にかぶっているか？
    for (var i = 1 ; i <= str2.length ; i++){
        var temp = str2.slice(str2.length - i);
        //console.log(temp)
        if (str1.indexOf(temp) == 0){
            console.log("先頭に一致");
            return true;
        }
    }
    console.log("後ろにかぶっているか？")
    //後ろにかぶっているか？
    var j = 0;
    for (var i = str2.length ; i > 0 ; i--){
        
        var temp = str2.substr(str2.lengt- i,i);

        if ((str1.length - str1.lastIndexOf(temp) )  ==  temp.length){
            console.log("後ろに一致");
            return true;
        }

    }
}