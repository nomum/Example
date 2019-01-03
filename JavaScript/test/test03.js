var a = "abc(0, 1, 2)dfasaf";
console.log(a);
console.log(hex(a));
a = a.replace(/&#x201c;/g, "\x1c").replace(/&#x201d;/g, "\x1d").replace(/\(/g, "\x1e").replace(/\)/g, "\x1f");
console.log(a)
console.log(hex(a));
pattern = "abc[\\x1e\\x1f0-9, ]+dfasaf";
a = a.replace(new RegExp(pattern, "g"), "bcr");
//.replace(/\x1e/g, '(').replace(/\x1f/g, ')');
console.log(a)
function hex(s){
    var result = '';
    for (var i = 0;i < s.length; i++){
        var h = ("0"+s.charCodeAt(i).toString(16)).substr(-2);
        result += h
    }
    return result;
}