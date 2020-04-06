package ex21

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock


/*
class IncThread: Thread() {
    companion object {
        var n = 0
        // IncThread가 공유하는 프로퍼티
        //  : 동시에 2개 이상의 스레드가 하나의 프로퍼티에 값을 변경하거나 읽을 경우
        //    제대로 동작하지 않는다.
        //    => 경쟁 조건(Race Condition)
        //    => 동기화(Mutual Exclusion) - 상호배제
    }

    override fun run() {
        // 1 ~ 1000000
        // Java: for (int i = 0 ; i < 1000000 ; i++)
        // Range: 1..1000000

        for (e in 1..1000000) {
            ++n
        }
    }
}

fun main(args: Array<String>) {
    val t1 = IncThread()
    val t2 = IncThread()

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}

*/


// 문제점: action()에서 예외가 발생할 경우, unlock이 호출되지 않는 문제가 있다.
//     => 데드락의 위험이 있다.
/*
fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    val result = action()
    lock.unlock()

    return result
}
*/

// action: 함수이다.
//  1. 함수 호출에 대한 부담이 있다.
//  2. Kotlin
//      Java 6: JVM - Lambda X
//      : 람다를 구현하기 위해서는 매번 별도의 객체를 생성해야 한다.
//        생성된 객체를 수거하는 GC 부담이 있다.
//     => inline: 람다 파라미터를 가지고 있는 함수에서 작동하는 기능으로
//                실행한 메소드 안으로 코드를 붙여넣기 하는 기능이다.

//      Java 8: JVM - Lambda O
//      : 오직 한개의 객체를 생성해서 호출한다.
//     => Closure: 람다의 컨텍스트에서 외부의 변수를 암묵적으로 참조하는 것

// Kotlin sychronized 함수의 구현 원리
inline fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}


class IncThread(private val lock: Lock) : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        for (e in 1..1000000) {
            withLock(lock) {
                ++n
            }
        }
    }
}


fun main(args: Array<String>) {
    val lock = ReentrantLock()

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}



