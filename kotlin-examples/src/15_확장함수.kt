// 확장 함수(Extension Function)
//  : 기존 자바 API를 수정하지 않고도 코틀린이 제공하는 편리한 기능을 사용할 수 있도록
//    해준다.

// 확장(Extension)
//   1. 수직 확장   ->  상속
//   2. 수평 확장   ->  확장 함수

// 수직 확장(상속)의 문제점
//   1. 깨지기 쉬운 기반 클래스 문제
//   2. 상속 금지되어 있는 클래스는 확장이 불가능하다.

// 수평 확장
//   1. C#: Extension Method
//   2. Swift: Extension
//   3. ObjC: Category
//   4. Javascript: Prototype
//     => Kotlin: Extension Function

// DSL(Domain Specific Language)
//   : 각각에 도메인에 특화된 언어를 쉽게 설계 가능하다.
//   Anko(Jetbrains) - Android DSL
package ex15

/*
// fun lastChar(text: String) = text.get(text.length - 1)
// fun lastChar(text: String) = text[text.length - 1]
//  -> lastChar(s)

// String: 수신 객체 타입
// this:   수신 객체 참조
fun String.lastChar(/* text: String */) = this[length - 1]
//  -> s.lastChar()
// * 외부의 함수이다. 클래스가 공개하고 있는 프로퍼티에만 접근이 가능하다.
//   protected / private 에 대해서는 접근이 불가능하다.

fun main(args: Array<String>) {
    val s = "Tom"

    // lastChar(s) // (String) -> Char
    val result = s.lastChar()


    println(result)
}
*/

// 아래의 함수는 Collection의 모든 원소를 하나의 문자열로 변경하는 함수
// template <typename T>
/*
fun <T> joinToString(collection: Collection<T>,
                     sperator: String = ", ",
                     prefix: String = "",
                     postfix: String = ""): String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0)
            result.append(sperator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4)
    // val result = joinToString(list, sperator = ", ", prefix = "[ ", postfix = " ]")
    val result = joinToString(list, sperator = "-")

    println(result)
}
*/


// Collection<T>에 해당 joinToString을 확장해보세요.
/*
fun <T> Collection<T>.joinToString(/*collection: Collection<T>,*/
                     sperator: String = ", ",
                     prefix: String = "",
                     postfix: String = ""): String {

    val result = StringBuilder(prefix)

    for ((index, element) in withIndex()) {
        if (index > 0)
            result.append(sperator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}
*/

// 특정한 제네릭 타입(Collection<Int>)에 대해서만 동작하도록 하고 싶다.
//   : C++ 부분 전문화와 비슷한 기능
fun Collection<Int>.joinToString(/*collection: Collection<T>,*/
        sperator: String = ", ",
        prefix: String = "",
        postfix: String = ""): String {

    val result = StringBuilder(prefix)

    for ((index, element) in withIndex()) {
        if (index > 0)
            result.append(sperator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}



fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4)
    // val result = joinToString(list, sperator = ", ", prefix = "[ ", postfix = " ]")
    // val result = joinToString(list, sperator = "-")
    val result = list.joinToString(sperator = "-")

    println(result)
}






























