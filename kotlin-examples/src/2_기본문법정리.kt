// 2_기본 문법 정리
package ex2;

import java_examples.Program

// 1. main 함수를 만드는 방법
// 2. 함수를 선언하는 방법
// fun 함수이름(파라미터 이름: 타입): 반환 타입
//  : static void main(String[] args)

// 3. 함수형 언어 - Functional Programming
//  => 모든 함수는 순수 함수(pure function)로 만들어야 한다.
//   : 함수의 입력이 동일하면 결과도 항상 동일해야 한다.
//  => void 함수는 존재하면 안됩니다.
//  => 결과가 없음을 나타내는 타입: Unit

/*
fun foo() {}

fun main(args: Array<String>): Unit {
    println(foo())
    println(Program.foo())
}
*/

// 4. 타입 시스템
//  1) Java
//   * Primitive Type
//      : int, double, float, char, byte
//     int n = 42
//    (1) Collection에 저장할 수 없다.
//    (2) 필드와 메소드를 가질 수 없다.

//   * Reference Type
//    Integer n = 42;
//      : String, Integer, Date, Thread ...

//  2) Kotlin
//   * 모든 것은 객체(데이터 + 메소드)이다.
//   Int n = 42 -> int
//   List<Int>  -> Integer

// 5. 변수를 선언하는 방법
//   * var - variable(변수)
//     Integer n = 42;
//   * val - value   (상수)
//    주의할 점: 객체가 불변이 아니라, 레퍼런스 변수가 불변이다.
//     final Integer n = 42;

// 6. 강력한 타입 언어
//    : 암묵적으로 타입의 변환이 거의 허용되지 않는다.
/*
class User {
    var name: String = "Tom"
}

fun main() {
    // 42.toLong()
    val n: Int = 42
    // n = 100
    val l: Long = n.toLong()
    val n1 = 42
    // n1 = 100

    var u1 = User()
    u1.name = "Bob"

    u1 = User()
}
*/

// 7. Type deduction(타입 추론)
//  : 우항의 타입을 통해 변수의 타입을 컴파일 타임에 추론한다.
//  1) 암묵적 타입 지정
//    val n = 42
//  2) 명시적 타입 지정
//    val n: Long = 42
class User {
    fun foo() {}
}

fun main(args: Array<String>) {
    val n: Long = 42

    val n1 = 42
    val s1 = "Hello"

    // val vs var

    // : val을 사용하는 것이 좋습니다.

    // Nullable: null을 가질 수 있는 타입
    //   : String?, User?, Int?
    val u1: User? = User()

    if (u1 != null) {
        u1.foo()
    }
}
















































