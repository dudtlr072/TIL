package ex4
// 4_접근지정자

// Java
//   private, package, protected, public
// 주의할 점
//   1) protected는 자식 클래스만 접근할 수 있는 것이 아니라,
//      같은 패키지 내의 모든 클래스가 접근이 가능하다.
//   2) 다른 모듈이라고 하더라도, 같은 패키지명을 사용하면 쉽게 접근할 수 있다.

// Kotlin
//   private, internal, protected, public
//  1) protected: 자식 클래스에서만 접근이 가능합니다.
//  2) internal: 같은 모듈에서만 접근이 가능합니다.

// Project        ->       Solution
//   Module_1                 Project_1
//   Module_2                 Project_2

// public이 기본입니다.
class User

// 같은 파일내에 여러개의 public 클래스를 제공할 수 있습니다.
class Foo

// 같은 '모듈'내에서만 접근이 가능합니다.
internal class Goo

// 같은 파일에서만 접근이 가능합니다.
private class Hoo

// 함수에서도 동일한 개념을 사용할 수 있습니다.
fun foo() {
    println("foo")
}

fun main(args: Array<String>) {
    foo()
}







