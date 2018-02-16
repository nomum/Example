@startuml
title title
' single-line comment
'*  aaaa *'

package jp.co.toshiba-sol.btr{
    package common{
        package controller{

        }
        package service{
            interface 
        }

    }
}

interface AA{

}

class User {
  username
  password
  +sign_in()

}
class Group {
  name
}

class Member {
  roles
}

User .. Member
Group .. Member

@enduml