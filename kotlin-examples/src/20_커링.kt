package ex20

import java.time.LocalDateTime

// 커링(Currying)
//  : 다중 인수를 갖는 함수를 단일 인수를 함수들의 함수열로 바꾸는 것을 의미한다.

// sum(10, 20)      -> 30
//  => sum(10)      -> (Int) -> Int
//            (20)  -> 30

// -> fun sum(a: Int)(b: Int)(c: Int) ; Scala

// 일반 함수


// * 2개 커링 함수
//fun sum(a: Int): (Int) -> Int = { b ->
//    a + b
//}

// * 3개 커링 함수
// sum(10)       : (Int) -> (Int) -> Int
// sum(10)(20)
// sum(30)(20)(30)

//fun sum(a: Int): (Int) -> (Int) -> Int = { b ->
//    { c ->
//        a + b + c
//    }
//}


// (P1, P2) -> R

fun sum(a: Int, b: Int): Int = a + b
fun sum3(a: Int, b: Int, c: Int) = a + b + c

// 인자가 2개인 함수에 대해서 커링 함수를 생성하는 고차 함수
fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1 ->
    { p2 ->
        { p3 ->
            this(p1, p2, p3)
        }
    }
}

/*
fun main(args: Array<String>) {
    // val s = sum(10)(20)(30)
    // println("sum: $s")

    // sum(10, 20)
    val curriedSum = ::sum3.curried()

    val result = curriedSum(10)(20)(30)
    println("result: $result")
}
*/

// 용도
// 1) 함수의 실행을 지연할 수 있다.
// 2) 부분 적용
//   : 인자를 고정해서, 새로운 함수를 만드는 기술

// 라이브러리 함수: 우리가 수정하는 것이 불가능합니다.
fun compute(logger: (String) -> Unit) {
    logger("before computing")
    println("computing....")
    logger("after computing")
}

enum class Level { INFO, WARN, ERROR, CRITICAL }
fun log(level: Level, appendable: Appendable, message: String) {
    appendable.appendln("[${level.name}]:[${LocalDateTime.now()}]: $message")
}

//--------------------------------------

fun main(args: Array<String>) {
    log(Level.INFO, System.out, "Program started...")

    // log 인자가 compute에 요구하는 logger인자가 많은 경우
    // 해결 방법 2가지
    // 1. 람다
    compute { message ->
        log(Level.INFO, System.out, message)
    }

    // 2. 부분 적용: C++: bind
    val logger = ::log.curried()(Level.INFO)(System.out)
    compute(logger)
    // logger("computing...")
}























