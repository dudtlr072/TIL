package ex18_4


// 함수를 반환하는 함수
//  왜?
//   => 함수를 런타임에 동적으로 생성 가능하다.

/*
fun foo(): (String) -> String {
    val result: (String) -> String = {

    }

    return result
}
*/

// 함수를 반환하는 고차 함수
//  => 타입 추론을 허용하는 단일 표현식 함수를 사용하는 것이 편리하다.

/*
typealias ReturnFunction = (String) -> String

fun foo(): ReturnFunction = { str: String ->
    str.reversed()
}

// fun foo(): (String) -> String = { it.reversed() }

fun main(args: Array<String>) {
    var r = { str: String ->
        str.reversed()
    }

    // 함수를 반환받았습니다.
    // reverse: (String) -> String
    val reverse = foo()

    val str = reverse("hello")
    println(str)
}
*/

// 아래 함수는 2개의 Int 인자를 전달 받아서, 1개의 Int 인자를 받고 Boolean을 반환하는
// 함수를 반환하는 함수입니다.
fun modulo(k: Int, r: Int) : (Int) -> Boolean = { value ->
    value % k == r
}

// fun isOdd(e: Int)  = e % 2 == 1
// fun isEven(e: Int) = e % 2 == 0

// Filter의 예제에 적용해봅시다.
fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 동적으로 함수를 생성하는 것이 가능하다.
    val isEven = modulo(2, 0)  // e % 2 == 0
    val isOdd = modulo(2, 1)   // e % 2 == 1

    var result = list.filter(isEven)
    println("evens: $result")

    result = list.filter(isOdd)
    println("odds: $result")
}













