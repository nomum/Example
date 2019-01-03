
var s = `abcaadeeeeeabcdefdcwwwww`;
var p = '.*?(abc).*?(de)';
var r = new RegExp(p , "g");
s = s.replace(r,"$1:$2")
console.log(s)

