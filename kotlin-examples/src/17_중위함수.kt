// 중위 함수(Infix Function)
// : '인자가 하나'뿐인 일반 메소드나 확장 함수에 대해서 중위 호출을 사용할 수 있습니다.

infix fun Any.pair(other: Any) = Pair(this, other)

class User(var age: Int) {

    infix fun inc(n: Int) : Int {
        age += n
        return age
    }
}

/*
fun main(args: Array<String>) {
    val p1 = Pair("name", "Tom")
    val p2 = "name".pair("Tom")

    val p3 = "name" pair "Tom"

    val map = mapOf(
            // "name" to "Tom",
            "name" to "Tom",
            "age" to 42
    )
}
*/

// 비트 연산자가 대표적인 '중위 함수'로 설계되어 있습니다.
fun main(args: Array<String>) {

    val user = User(10)
    println(user.inc(10))
    println(user inc 5)


    // val n = 42
    // 32 + 8 + 2
    // 101010

    // 2진수 리터럴 문법
    val n = 0b101010

    println(n.toString(2))
    // 비트 시프트 연산
    //  Java
    //    <<,    >>(산술 쉬프트), >>>(논리 쉬프트)

    //  Kotlin
    //    shl    shr           ushr
    println(n shl 1)
    println(n shr 1)
    println(n ushr 1)

    // 비트 논리 연산자
    // and, or, xor
    println((0b101010 and 0b111000).toString(2))
    println((0b101010 or 0b010101).toString(2))
    println((0b101010 xor 0b101010).toString(2))
    println(0b101010.inv())
}












