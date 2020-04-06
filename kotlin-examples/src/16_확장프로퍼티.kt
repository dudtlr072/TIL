package ex16

// 프로퍼티를 확장하는 것도 가능합니다.
//  => 프로퍼티를 메소드처럼 사용하는 것이 가능하기 때문에

// 확장 함수
fun String.lastChar() = this[length - 1]

// String: Immutable Object(불변 객체)
//  -> 객체가 생성된 이후로 상태가 변경되지 않는 객체

// 확장 프로퍼티
//  : 이미 존재하는 클래스에 새로운 필드를 추가하는 것은 불가능하다.
// => 확장 프로퍼티는 필드가 없는 프로퍼티만 가능합니다.
val String.lastChar: Char
    get() = this[length - 1]

var StringBuilder.lastChar: Char
    get() = this[length - 1]
    // set(value) = set(length - 1, value)
    set(value) {
        this[length - 1] = value
    }


fun main(args: Array<String>) {
    // var n: Int = 0
    // println(n = 42)

    // val result = "Hello".lastChar()
    val result = "Hello".lastChar

    val sb = StringBuilder("Hello, Kotlin?")
    sb.lastChar = '!'

    println(sb.toString())  // Hello, Kotlin!
}







