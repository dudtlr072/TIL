package ex3

import java.util.*

// 3_클래스 문법

/*
class User {
    val name: String
    val age: Int

    // constructor: 생성자 함수를 정의하는 키워드
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

/*
// Annotation을 사용하는 경우, constructor 키워드를 생략하면 안됩니다.
class User constructor(name: String, age: Int) {
    val name: String
    val age: Int

    init {
        this.name = name
        this.age = age
    }
}
*/


// 동등성 판단
//  1) 참조 동등성 판단
//    Java: u1 == u2
//  Kotlin: u1 === u2 / u1 !== u2

//  2) 객체 동등성 판단
//    Java: u1.equals(u2)
//  Kotlin: u1 == u2
//        -> null에 대한 별도의 체크가 필요하지 않습니다.

// Primary constructor(주 생성자)
/*
class User(val name: String, val age: Int) {

    // Object.equals 메소드를 재정의해야 합니다.
    //  : Object 타입이 아니라 Any 라는 이름으로 사용됩니다.
    //  - Ctrl + o

    // override: 부모로부터 재정의하는 메소드는 반드시 override 키워드를
    //           사용해야 한다.
    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }

    // equals / hashCode
    //  equals를 재정의하면 반드시 hashCode도 재정의해야 한다.
    override fun equals(other: Any?): Boolean {
        // 1. other가 null 인지 체크한다.
        if (other === null)
            return false

        // Any? -> Any

        // 2. 참조 동등성을 확인한다.
        if (this === other)
            return true

        // 3. User 타입인지 확인한다.
        // Java:
        // if (other.getClass() != User.class)
        //  -> if (other.javaClass !== javaClass)
        // if (other instanceOf User)
        //  -> if (!(other is User))
//        if (other.javaClass !== javaClass)
//            return false
         if (other !is User)
            return false

        // 4. User 타입으로 캐스팅한다.
        // other as User              // 스마트 캐스트
        // Any -> User

        // 5. 필드에 대한 비교를 수행한다.
        return name == other.name &&
                age == other.age
    }
}
*/

// equals, hashCode, toString
data class User(val name: String, val age: Int)

fun main(args: Array<String>) {
    // new 키워드는 사용하지 않습니다.
    val u1 = User("Tom", 42)
    val u2 = u1
    // val u2 = User("Tom", 42)

    println(u1)
    println(u2)

    if (u1 === u2) {
        println("동일한 참조 입니다.")
    } else {
        println("동일한 참조가 아닙니다.")
    }

    // u1 != null && u1.equals(u2)
    // Objects.equals(u1, u2)
    if (u1 == u2) {
        println("동일한 내용을 가진 객체 입니다.")
    } else {
        println("동일한 내용을 가지고 있지 않습니다.")
    }

    // foo()
}






