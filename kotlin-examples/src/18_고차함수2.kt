package ex18_2


/*
interface Predicate {
    fun test(e: Int): Boolean
}
*/

// fun test(e: Int) : Boolean
//  => (Int) -> Boolean

class User {
    inner class A
    inner class B
}

typealias UserA = User.A
typealias UserB = User.B

// Kotlin에서도 복잡한 타입의 표현을 간략화하는 문법을 제공합니다. - typealias
typealias Predicate = (Int) -> Boolean

fun filter(data: List<Int>, pred: Predicate): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (pred(e))
            result.add(e)
    }

    return result
}

fun isOdd(e: Int) = e % 2 == 1
fun isEven(e: Int) = e % 2 == 0

fun filterEvens(data: List<Int>) = filter(data, ::isEven)
fun filterOdds(data: List<Int>) = filter(data, ::isOdd)

fun greaterThan5(e: Int) = e >= 5

fun main(args: Array<String>) {
    // User.A
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val evens = filterEvens(list)
    val odds = filterOdds(list)

    println("evens: $evens")
    println("odds: $odds")

    var result = filter(list, ::greaterThan5)


    // 1. 익명(무명) 함수: 이름이 없는 함수
    result = filter(list, fun(e: Int) = e >= 5)

    // 2. 람다(Lambda): 코드 조각을 인자로 전달하는 기술
    result = filter(list, { e: Int ->
        e >= 5
    })

    // 만약 호출하는 함수의 마지막 인자가 함수인 경우,
    // 다음과 같이 표현하는 것도 가능합니다.
    //  : Trailing Lambda
    result = filter(list) { e: Int ->
        e >= 5
    }

    // 람다의 인자는 타입이 추론이 가능합니다.
    result = filter(list) { e ->
        e >= 5
    }

    // 만약 람다의 인자가 1개인 경우, 인자 표현을 생략할 수 있습니다.
    result = filter(list) { it >= 5 }

    // Kotlin에서는 정책을 받는 함수를 만들때 반드시 마지막 인자를 정책인자로 사용합니다.
    // * Kotlin 표준 라이브러리
    result = list.filter { it >= 5 }
    println("result: $result")

    // return을 생략 가능한 곳
    //  1. 단일 표현식 함수
    //  2. 람다
}








