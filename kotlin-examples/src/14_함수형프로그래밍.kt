// import ex15.lastChar

// 14_함수형 프로그래밍
//   : 함수를 일급 시민(first-class citizens)으로 취급한다.
//   1) 변수(variable)에 담을 수 있다.
//   2) 인자(parameter)에 전달 할 수 있다.
//   3) 반환 값(return value)에 전달할 수 있다.
//   4) 실행 시간(runtime)에 생성이 가능해야 한다.
//   5) 익명(anonymous)으로 생성이 가능해야 한다. - 커링, 부분 적용
//     : C++11/14, Java 8

/*
fun add(a: Int, b: Int): Int {
    return a + b
}
*/

// 1. 단일 표현식 함수
//   : 반환 타입을 추론하는 것이 가능하기 때문에, 생략이 가능합니다.
fun add(a: Int, b: Int) = a + b

fun sub(a: Int, b: Int) = a - b


// 2. 함수의 타입
//   : 함수의 시그니쳐에 의해서 결정됩니다.
//    - 함수의 시그니쳐: 인자의 타입, 개수, 반환 타입에 대한 정보가 동일하다면,
//                   같은 타입이다.

// add, sub
//   (Int, Int) -> Int

// main
//   (Array<String>) -> Unit

fun foo(a: Int, b: Double, c: Char) = "foo($a, $b, $c)"
// foo
//   (Int, Double, Char) -> String

//  * 자바에서는 함수 타입이 존재하지 않기 때문에,
//    add: KFunction2<P1, P2, R> -> KFunction2<Int, Int, Int>
//    foo: KFunction3<P1, P2, P3, R>
//      -> KFunction3<Int, Double, Char, String>

//  => KFunctionN<P1, P2, P3 ... PN, R>
//  KFunction 타입을 직접적으로 다루는 것은 더 이상 허용되지 않습니다.
/*
fun main(args: Array<String>) {
    // 3. 변수에 담을 수 있습니다.
    var fp = ::add
    fp = ::sub

    val fp3 = ::foo
    var fp2: (Int, Int) -> Int = ::add

    // 4. 변수를 통해 함수를 호출하는 것이 가능 합니다. - 간접 호출
    println(fp(10, 20))

    println(fp3.name)

    // println(fp3.invoke(10, 3.14, 'A'))
    println(fp3(10, 3.14, 'A'))
}
*/

/*
// 메소드: 객체와 연관되어 동작하는 함수
//  => This Call

class User(val name: String) {

    // () -> Unit
    // fun print(Unit this)
    fun print() = println("User($name)")
}

fun main(args: Array<String>) {
    // (User) -> Unit
    val fp = User::print

    val u1 = User("Tom")
    val u2 = User("Bob")

    // 메소드를 변수에 담아 호출할 때는 어떤 객체에 대해서 호출할지
    // 반드시 지정해야 한다.
    // fp(u1)
    // fp(u2)

    // () -> Unit
    //  : 바운드 메소드 참조
    //   어떤 객체에 대해서 메소드를 호출할지 미리 결정된 함수 참조
    val fp2 = u1::print
    val fp3 = u2::print

    fp2()
    fp3()

    // print(u1)
    // print(u2)
}
*/

// 5. 지역 함수를 만드는 것도 가능합니다.
/*
fun printArea(width: Int, height: Int) {

    fun calculateArea(width: Int, height: Int) = width * height

    val area = calculateArea(width, height)

    println("The area is $area")
}
*/

fun printArea(width: Int, height: Int) {
    // 지역 함수는 스코프 바깥의 파라미터 변수나 변수에 접근하는 것이 가능합니다.
    // => 클로져(Closure)
    //   : 암묵적으로 외부의 변수를 전달(capture)받는 기술

    var n = 2
    fun calculateArea(): Int {
        n = 1      // Ok!
        // width = 100 // Error!
        return width * height * n
    }

    val area = calculateArea()
    println("The area is $area")
}

fun main(args: Array<String>) {
    printArea(100, 30)

    // println("hello".lastChar())
}


















