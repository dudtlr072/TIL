package ex24

import java_examples.LambdaExample
import java.util.*

// Nullable - compiler
//  => 레퍼런스가 null인 경우 발생하는 오류가 많다.
//  => 컴파일러를 통해 체크하는 기술
//  => Java 8(Optional - library)

// var a: Type  = null    // Error!
// var b: Type? = null    // Ok!

fun hoo(name: String) {
    println("Hello, $name")
}

/*
fun main(args: Array<String>) {
    val n1: Optional<Int> = Optional.empty()  // null, Java 8

    val n2: Int? = null                       // Kotlin

    // Nullable 타입에 대해서는 반드시 null 체크가 필요합니다.
    // LambdaExample.foo(null)

    // Java와의 상호 운용
    //  Kotlin        Java
    //   String   ->   @NotNull String
    //   String?  ->   String

    val name: String? = null

    // Nullable 타입에 대해서는 반드시 null 체크가 필요합니다.
    // 1. Smart Cast를 통해서 null 가능성을 없앤다.
    // if (name == null)
    //    return

    // 2. 안전한 널 참조 연산(Safe null access operator)
    //     ?.
    //   ; null이면 전체 연산의 결과가 null이 된다.
    // var length = name?.length

    // 3. 강제 접근
    //   : 예외가 발생한다.
    // var length = name!!.length
}
*/

// 안전한 널 참조 연산 활용
class Country(val name: String)
class City(val name: String, val country: Country?)
class Address(val name: String, val postcode: String, val city: City?)
class Person(val name: String, val address: Address?)

/*
fun getCountryName(person: Person?) : String? {
    var countryName: String? = null

    if (person != null) {
        val address = person.address
        if (address != null) {
            val city = address.city
            if (city != null) {
                val country = city.country
                if (country != null)
                    countryName = country.name
            }
        }
    }

    return countryName
}
*/

/*
fun getCountryName(person: Person?) : String? {
    return person?.address?.city?.country?.name
}
*/

// Elvis Operator(엘비스 연산자)
// : 특정한 표현식의 결과가 null 인경우 default 값을 지정하는 용도로 사용한다.
fun getCountryName(person: Person?) : String {
    return person?.address?.city?.country?.name ?: ""
}

fun main(args: Array<String>) {
    val country = Country("Korea")
    val city = City("Seoul", country)
    val address = Address("Hadong", "16512", city)
    val person = Person("Tom", address)

    val countryName = getCountryName(person)
    println("country: $countryName")
}









