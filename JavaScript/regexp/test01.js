



var text1 = String.fromCharCode(31);

var temp = "aaaa" +String.fromCharCode(31) + "bcd";

//console.log(temp);

var out = temp.split(text1);
//console.log(out);


// (0x00 ～ 0x1F または 0x7F) 
//[0-9]

var val = "she is mother ";
test1(val,"the");
test1(val,"mother");
test1("she is :mother@","mother");
test1("she is [mother`","mother")
test1("she is [mother} mother ","mother")


function test1(val ,word){
    

    //var b = "[" + String.fromCharCode(0) + "-" + String.fromCharCode(31)  + "]"    ;
    //var b = '[\\0-\\s]' ;
    //var b = '[^0-9a-zA-Z<>\\#\\r\\n\\0-\\s]|[\\s]';
    //var b = '[\\s-\\\\:-@\\[-\\`]\\{\\~]';
    //var b = '[\\s-\\\\:-@\\[-\`\\x7d-\\x7f]';
    var b = "([\\s-\\\\:-@\\[-\`\\x7d-\\x7f])";
    var searchWord = b + "(" + word + ")" + b + "";
    //console.log(searchWord);
    
    var pattern = new RegExp(searchWord , "g");
    
    
    //var result = val.match( pattern );
    //console.log(result);
    //var a = val.replace(searchWord,"$1\<\>$2\<\/\>$3");
    //var a = val.replace(searchWord,"$2");
    var a = val.replace(pattern,function(all,g1,g2,g3){
        //console.log("all:");
        //console.log(all);
        //console.log("group:");
        //console.log(g1);
        //console.log(g2);
        //console.log(g3);
        return g1+"<mark>" + g2 + "</mark>" + g3;
    });
    console.log(a);

    var b = val.replace(pattern,"$1<>$2<>$3");
    console.log(b);
}

function test2(val ,word){
    
}

var a1 = "abc12defg";
var t = a1.replace(/([1-9])/,'<$1>');
//console.log(t);

var t2 = a1.match("([1-9])");
//console.log(t2);
