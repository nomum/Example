Function AddArg($arg1 , $arg2){
    $ret = $arg1 + $arg2
    $arg1.getType()
    $arg2.getType()
    $ret.getType()
    return $ret
}

Write-Output "test1"
addArg 1 2

#Write-Output "test2"
#AddArg(3,"3");

#Write-Output "test3"
#AddArg("HELLO", 1111);

#Write-Output "test4"
#AddArg(1111,"Hello");
