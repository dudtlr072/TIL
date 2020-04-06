package ex5

// 5_객체 생성방법

// 디자인 패턴(Design Pattern) - GoF's Design Pattern

// 1. 생성자 오버로딩
/*
class User(val name: String,
           val address: String,
           val age: Int,
           val weight: Int,
           val height: Int) {

    // 부 생성자 오버로딩
    constructor(name: String, address: String, age: Int, weight: Int)
            : this(name, address, age, weight, 0)

    constructor(name: String, address: String, age: Int)
            : this(name, address, age, 0, 0)

    constructor(name: String, address: String)
            : this(name, address, 0, 0, 0)

    constructor(name: String)
            : this(name, "", 0, 0, 0)
}
*/


// Kotlin은 Default Parameter Value를 지원합니다.
//  => Java에서 지원하지 않습니다.
/*
class User(val name: String,
           val address: String = "",
           val age: Int = 0,
           val weight: Int = 0,
           val height: Int = 0)
*/

class User @JvmOverloads constructor(val name: String,
                                     val address: String = "",
                                     val age: Int = 0,
                                     val weight: Int = 0,
                                     val height: Int = 0)

fun main(args: Array<String>) {
//    var u1 = User("Tom", "Suwon", 42, 80, 180)
//    u1 = User("Tom", "Suwon")
//    u1 = User("Tom", "Suwon", 100)
//    u1 = User("Tom", "Suwon", 100, 80)

    val a = "Tom"
    val b = "Suwon"
    val c = 100
    val d = 80
    val e = 180

    // 파라미터 라벨 지정을 통한 함수 호출을 지원합니다.
    // val u1 = User(a, b, c, d, e)

    // Builder -> 파라미터 라벨 지정
    val u1 = User(name = a,
            age = c,
            address = b,
            height = e,
            weight = d)
}















