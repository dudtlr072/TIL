package ex25

import java.io.File

// 1. 조건 확인 함수
//   check(condition): condition 거짓이면 예외가 발생한다.
//                     -> IllegalStateException

//   require(condition): IllegalArgumentException
//     -> 함수를 설계할 때 인자를 체크하는 용도
fun log(filename: String, message: String) {

    // filename에 해당하는 파일이 이미 존재하는지 여부를 확인하고 싶다.
    // 존재하면 안된다.
    val file = File(filename)
    // if (file.exists()) {
    //    throw IllegalStateException("이미 존재하는 파일입니다.")
    // }
    check(!file.exists())

    // message의 길이가 1 이상인지 체크한다.
    require(message.isNotEmpty())
}

fun log(message: String?) {
    require(message != null) // Exception

    // val value = requireNotNull(message)
    // message: null이 아니면 nullable을 없앤 타입을 반환한다.

    require(message != null)
    // 스마트캐스트가 적용되지 않습니다.
    // message.length
}

// 2. 명시적인 종료 함수
//    error() / TODO()

/*
fun main(args: Array<String>) {
    // log("hello.log", "Hello, Kotlin")
    // log("hello.log", "")
    // log("state.dat", "Hello, Kotlin")

    // TODO("Not Implemented")  -> NotImplementedError
    // error("Not Implemented") -> IllegalStateException
}
*/

// 3. 스트림 함수
//   : 컬렉션에 포함된 자료들을 편리하게 다룰 수 있는 함수의 집합
//     컬렉션에 포함된 데이터를 변환하거나 새로운 데이터를 생성하거나 등의 작업을
//     편리하게 수행할 수 있습니다.

//  명령형 프로그래밍: 프로그래머가 직접 알고리즘을 작성하는 형태의 코드
//  선언적 프로그래밍: 컬렉션을 다룰 때 SQL을 이용하는 듯한 컨셉으로 코드를 작성하는 것
//     Java: Stream API
//     Kotlin: 표준 라이브러리
//     Javascript: Underscore.js, lodash.js
//     C++: <algorithm>
//    => 지향해야 한다.
//      문제점
//        1) 익숙하지 않다. 학습이 필요하다.
//        2) 디버깅이 어렵다.

//    => 병렬화가 쉽다.


// 아래의 함수는 원하지 않는 데이터는 null을 반환합니다.
/*
fun transform(s: String): String? =
        if (s.startsWith("S"))
            s.toLowerCase()
        else
            null

// map
fun main(args: Array<String>) {
    val cities = listOf("Seoul", "Busan", "Daegu", "Suwon")

    // 변환: transform, map
    //  : 각 원소를 변환하여 새로운 컬렉션을 생성하는 연산
    var result: List<String> = cities.map { it.toUpperCase() }
    println(result)

    result = cities.mapNotNull(::transform)
    println(result)

    val result2 = cities.map(::transform)
    //           List<String> => (String) -> String? => List<String?>
    println(result2)

    // val names = setOf("Tom", "Bob", "Alice")
}
*/


// ::fn1
//  : (T) -> R

// ::fn2
//  : (T) -> List<R>

// List<T>  -> map(::fn1)     -> List<R> : map
// List<T>  -> flatMap(::fn2) -> List<R> : flatMap


// flatMap
//      1    2     3        4           5
//      1    1 2   1 2 3    1 2 3 4     1 2 3 4 5
/*
fun main(args: Array<String>) {
    // Range
    val numbers = 1..5

    val result = numbers.flatMap { number -> 1..number }
    println("result: $result")
}
*/

/*
// C# => LINQ(Language-Integrated Query)
//    => 에릭 마이어
//    => Rx(Reactive Extension)

// groupBy
fun main(args: Array<String>) {
    val cities = listOf("Seoul", "Busan", "Daegu", "Suwon")

    // List<String> => groupBy =>  Map<K, List<String>>
    //               (String) -> K
    //                   'S'       Map<Char, List<String>>

    /*
    val result = cities
            .groupBy { city -> city.first() }
            .toSortedMap()
    //  Map<Char, List<String>>


    result.forEach { k, v -> println("$k -> $v") }

    // for ((k, v) in result) {
    //    println("$k -> $v")
    // }

    */

    cities.groupBy { city -> city.first() }
            .toSortedMap()
            .forEach { k, v -> println("$k -> $v") }
}
*/

/*
// zip: 두 컬렉션 내의 자료들을 조합하여 새로운 자료를 만듭니다.
fun main(args: Array<String>) {
    val countries = listOf("Korea", "United States", "China", "Japan")
    val codes = listOf("KR", "US", "CN", "JP")

    // countries.zip(codes).forEach { println(it) }

    countries.zip(codes) { country, code ->
        "$country($code)"
    }.forEach(::println)
}
*/

// Kotlin의 아래의 각 연산은 임시적으로 컬렉션을 생성합니다.
//  => 즉시 처리되지만, 데이터의 개수가 많을 수록 성능이 떨어지는 문제가 있습니다.

//  해결방법
//  => 데이터의 개수가 많을 경우,
//     지연된 계산을 사용하는 것이 효과적입니다.

//   Sequence(Kotlin) => Java 7 까지
//   Stream           => Kotlin을 사용하고 있다고 하더라도, Stream을 사용하는 것이 좋을 때가
//                       있습니다.(parallelStream을 이용하기 위해서)
fun main(args: Array<String>) {
    val result = listOf(10, 87, 97, 43, 121, 20)
            .asSequence()
            .flatMap { e -> (0..e).asSequence() }
            .map { it * 2 }
            .distinct()                 // 중복된 값을 제거한다. (unique)
            .sorted()
            .sum()
    // Sequence 반환 타입이 아니면, 연산이 수행됩니다.

    println("result: $result")  // 이때 계산됩니다.


    /*
    val result = listOf(10, 87, 97, 43, 121, 20)
            .flatMap { e -> 0..e }
            .distinct()                 // 중복된 값을 제거한다. (unique)
            .sorted()

    println("result: $result")
    */
}




















