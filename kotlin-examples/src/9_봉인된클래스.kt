package ex9

/*
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// 문제점: 새로운 Expr 기반의 클래스가 생성될 경우, 심각한 버그가 발생할 수 있다.
// 해결 방법: Num, Sum 이외의 Expr 하위 클래스 생성을 막으면 된다.
//   => 봉인된 클래스: 계층 확장 제한 문법

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }
}
*/

// Composite Pattern
sealed class Expr {
    // 봉인된 클래스의 하위 클래스는 반드시 부모 클래스의 중첩 클래스로 만들어야 한다. - Kotlin 1.0
    // class Minus(val left: Expr, val right: Expr) : Expr()
}
// 같은 파일 내에서는 봉인된 클래스의 상속을 허용합니다. - Kotlin 1.1
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
    }
}

fun main(args: Array<String>) {
    val n1 = Num(10)
    val n2 = Num(20)
    val n3 = Num(30)

    val s1 = Sum(n1, n2)   // 10 + 20
    val s2 = Sum(s1, n3)   // (10 + 20) + 30
    val s3 = Sum(s1, s2)   // 10 + 20 + 10 + 20 + 30

    println(eval(s3))
}

















