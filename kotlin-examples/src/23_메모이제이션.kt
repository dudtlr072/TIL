package ex23

import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Test

// import org.junit.jupiter.api.Test


/*
fun fib(k: Int): Long = when (k) {
    0 -> 1
    1 -> 1
    else -> fib(k - 1) + fib(k - 2)
}
*/


// 메모이제이션: 중복된 계산을 캐시하는 방법
//     => 순수 함수(pure function)
//     => 함수의 입력값이 동일하면 결과도 동일하다.
//     => 참조적 투명성을 가진다.
//     => 동적 계획법(Dynamic Programming)
/*
val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = cache.getOrPut(k) {
    when (k) {
        0 -> 1
        1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}

class FibTest {
    @Test
    fun fib10() {
        fib(10)
    }

    @Test
    fun fib30() {
        fib(30)
    }

    @Test
    fun fib50() {
        fib(50)
    }
}
*/

/*
fun main(args: Array<String>) {
    println(fib(10))
    println(fib(30))
    println(fib(50))
}
*/

//val cache = mutableMapOf<Int, Long>()
//fun fib(k: Int): Long = cache.getOrPut(k) {
//    when (k) {
//        0 -> 1
//        1 -> 1
//        else -> fib(k - 1) + fib(k - 2)
//    }
//}

/*
// 기존의 함수에 메모이제이션을 제공하는 고차 함수
//  : 메모이제이션이 적용되어 있지 않은 함수에
//    메모이제이션을 적용한 함수를 만들어서 반환하는 함수
fun fib(k: Int): Long {
    return when (k) {
        0 -> 1
        1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}
*/

val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = cache.getOrPut(k) {
    when (k) {
        0 -> 1
        1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}

class FibTest {
    @Test
    fun fib10() {
        fib(10)
    }

    @Test
    fun fib30() {
        fib(30)
    }

    @Test
    fun fib50() {
        fib(50)
    }
}

// 아래 기능은 재귀에 사용할 수 없습니다.
fun <A, R> ((A) -> R).memoized(): (A) -> R {
    val cache = mutableMapOf<A, R>()
    return { k ->
        cache.getOrPut(k) {
            this(k)
        }
    }
}

// memoization: HashMap => Any.hashCode / Any.equals
data class User(val name: String, val age: Int)

// Gson: Google JSON Serialization / Deserialization Library
fun toJson(user: User): String {
    println("toJson")

    val gson = GsonBuilder().setPrettyPrinting().create()
    return gson.toJson(user)
}

fun main(args: Array<String>) {
    /*
    val users = listOf(
            User("Tom", 41),
            User("Bob", 42),
            User("Alice", 43)
    )
    */
    val users = listOf(
            User("Tom", 42),
            User("Tom", 42),
            User("Tom", 42)
    )

    val toJson = ::toJson.memoized()

    for (e in users) {
        println(toJson(e))
    }
}


























