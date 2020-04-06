
// 고차(고계) 함수
//  : Higher Order Function

//  정의?
//  : 함수를 인자로 전달 받거나 함수를 반환하는 함수

//  고차 함수를 왜 사용하는가?
//   1) 다양한 시나리오에서 동작하는 함수 코드 중복을 없앨 수 있다.
//   2) 함수의 재사용성을 극대화!

//  Kotlin Collection Interface
//      List<E>,        Set<E>,        Map<K, V>        : Immutable
//   MutableList<E>, MutableSet<E>, MutableMap<K, V>    : Mutable

/*
fun filterEvens(data: List<Int>) : List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (e % 2 == 0)
            result.add(e)
    }

    return result
}

fun filterOdds(data: List<Int>) : List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (e % 2 == 1)
            result.add(e)
    }

    return result
}
*/

// 해결 방법
//   => 알고리즘은 동일하지만, 필터의 정책은 다릅니다.
//      정책을 사용자가 결정할 수 있도록 인자로 전달 받으면 됩니다.
//   => 전략(Strategy) 패턴

// 구현 방법

interface Predicate {
    fun test(e: Int): Boolean
}

//  1) Java: 동작 파라미터화
fun filter(data: List<Int>, pred: Predicate) : List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (pred.test(e))
            result.add(e)
    }

    return result
}

/*
class EvenPredicator : Predicate {
    override fun test(e: Int) = e % 2 == 0
}

class OddPredicator : Predicate {
    override fun test(e: Int) = e % 2 == 1
}

fun filterEvens(data: List<Int>) = filter(data, EvenPredicator())
fun filterOdds(data: List<Int>) = filter(data, OddPredicator())
*/

// 새로운 정책 객체가 생성된다. object 선언을 통해 하나를 공유해서 사용하는 것이 좋다.
object EvenPredicator : Predicate {
    override fun test(e: Int) = e % 2 == 0
}

object OddPredicator : Predicate {
    override fun test(e: Int) = e % 2 == 1
}

fun filterEvens(data: List<Int>) = filter(data, EvenPredicator)
fun filterOdds(data: List<Int>) = filter(data, OddPredicator)

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val evens = filterEvens(list)
    val odds = filterOdds(list)

    println("evens: $evens")
    println("odds: $odds")

    // 무명 객체를 생성해서 전달하는 방법
    val result = filter(list, object : Predicate {
        override fun test(e: Int) = e >= 5
    })

    println("result: $result")
}














