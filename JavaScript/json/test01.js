


var map = [];

console.log(map);
map.push("a","bbbb");
map.push("b","ccccc");
map.push("a","103456");
console.log(map);
console.log(map)
console.log( JSON.stringify(map))


var map2 = {}
console.log(map2);
map2['a'] = 'ABC'
map2['b'] = 'DEF'
map2['a'] = 'JHK'
console.log(map2);
console.log( JSON.stringify(map2))
var map3 = JSON.parse(JSON.stringify(map2));
console.log(map3);