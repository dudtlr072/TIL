package ex26

import java.io.DataOutputStream
import java.io.FileOutputStream

// 범위 지정 함수
//   1. let
fun sendEmail(to: String): Boolean {
    println("send email to $to")
    return true
}


class User {
    var email: String? = null
}


/*
    // Swift
    if let name = name {

    }
*/

/*
fun main(args: Array<String>) {
    val user = User()
    // user.email = "chansigi@gmail.com"

    // email이 null 이면 비정상 종료 합니다.
    // sendEmail(user.email!!)

    /*
    if (user.email != null)
        sendEmail(user.email)
    */

    // fun<T, R> T.let(block: (T) -> R): R
    val result = user.email?.let { email -> sendEmail(email) } ?: false
    user.email?.let { sendEmail(it) }
}
*/

/*
class User {
    lateinit var email: String
}


fun main(args: Array<String>) {
    val user = User()
    user.email = "chansigi@gmail.com"

    // email이 null 이면 비정상 종료 합니다.
    // sendEmail(user.email!!)

    sendEmail(user.email)
}
*/

//   2. with
//   3. apply
/*
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z')
        result.append(letter)

    result.append("\n")
    return result.toString()
}

// 수신 객체 지정 람다 - with
//  : with 표현식의 결과값은 람다 블록의 결과 입니다.
fun alphabet_with1(): String {
    val result = StringBuilder()


    return with(result) {
        for (letter in 'A'..'Z')
            append(letter)
        append("\n")
        toString()
    }
}

fun alphabet_with2() = with(StringBuilder()) {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
    toString()
}

// 수신 객체 지정 람다 - apply
//   apply 표현식의 결과는 무조건 수신 객체 입니다.
fun alphabet_apply1(): String {
    return StringBuilder().apply {
        for (letter in 'A'..'Z')
            append(letter)
        append("\n")
    }.toString()
}

fun alphabet_apply2() = StringBuilder().apply {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
}.toString()

fun alphabet_apply3() = buildString {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
}

class MyThread : Thread()

fun main(args: Array<String>) {
    println(alphabet())
    println(alphabet_with1())
    println(alphabet_with2())

    println(alphabet_apply1())
    println(alphabet_apply2())
    println(alphabet_apply3())

    /*
    val thread = MyThread().apply {
        isDaemon = true
    }
    */
}
*/

//  Java의 메모리 자원은 JVM이 관리합니다.
//  Java에서 사용하는 비 메모리 자원은 명시적인 종료 메소드 호출을 해야 합니다.
//    : Try with Resource - Java 7

//  finalize: JVM이 객체를 파괴할 때 호출되는 함수
//    'finalize에서 close()를 처리하면 안돼는 이유'
//    1) finalize 호출 시점이 명확하지 않다.
//    2) finalize 호출을 보장하지 않는다.

//  비 메모리 자원
//    : File, Thread, Mutex => 운영체제 서비스

fun main(args: Array<String>) {
    val fos = FileOutputStream("a.txt")
    val dos = DataOutputStream(fos)

    // Try with Resources
    fos.use {
        dos.use {
            dos.writeChar('A'.toInt())
        }
    }

    // fos.close()
    // dos.close()
}






















