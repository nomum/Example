


var text1 = String.fromCharCode(31)
var str1 = "abc";
var str2 = "def";
//var out = str1 + text1 + str2;
console.log(str1 + str2);
console.log(str1 + text1 + str2);


delimiter = "(^|$|[\\x1e-\\x1f\\x20-\\x2f\\x3a-\\x40\\x5b-\\x60\\x7b-\\x7e])"; // デリミタ
//delimiter = "([\\x1e-\\x1f\\x20-\\x2f\\x3a-\\x40\\x5b-\\x60\\x7b-\\x7e])"; // デリミタ
var str = "abcdefg hijklmn";
var regex1 = RegExp(delimiter + "abcdefg" +  delimiter);
console.log(regex1.test(str));
var regex1 = RegExp(delimiter + "hijklmn" +  delimiter);
console.log(regex1.test(str));
var regex1 = RegExp(delimiter + "hijklm" +  delimiter);
console.log(regex1.test(str));



