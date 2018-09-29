
var s1 = '{"stataus":0,"text":null}';
1
2
3
json = JSON.parse( s1 );
 
if (json.text === null){
    console.log(" ")
}else {
    console.log( json.text );
}
