package ex13

import ex10.User as User1
import ex11.User as User2

import ex12_2.VeryVeryLongClassName as VLC

// 1. 동일한 이름의 클래스를 import 하는 경우,
//    이름 충돌을 방지하는 용도로 사용할 수 있다.
// 2. 복잡한 이름의 클래스 또는 인터페이스를 간결한 이름으로 사용할 수 있다.
fun main(args: Array<String>) {
    val u1 = User1("Tom", 42)
    val u2 = User2("Tom")

    println(u1)
    println(u2)

    val vlc = VLC()
}

