package ex22

/*
class Person(val name: String)

fun lookForAlice1(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }
    }

    println("Alice isn't found")
}

fun lookForAlice2(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            return
            // 람다에 대한 반환이 아니라, lookForAlice 함수에 대한 반환이다.
            //  => for-loop를 for-each 람다 구문으로 변경할 때,
            //     코드 블록 수정이 거의 필요 없다.
        }

    }

    println("Alice isn't found")
}

fun main(args: Array<String>) {
    val people = listOf(Person("Alice"), Person("Alice"))

    lookForAlice1(people)
    println("---------------")
    lookForAlice2(people)
}
*/

/*
class Person(val name: String)

inline fun List<Person>.forEach2(action: (Person) -> Unit) {
    for (e in this)
        action(e)
}

fun lookForAlice(people: List<Person>) {
    people.forEach2 {
        if (it.name == "Alice") {
            println("Found!")
            return
            // 람다에 대한 반환이 아니라, lookForAlice 함수에 대한 반환이다. => 비지역 반환
            //  => for-loop를 for-each 람다 구문으로 변경할 때,
            //     코드 블록 수정이 거의 필요 없다.
            //  => inline 함수에서만 가능하다.
        }

    }

    println("Alice isn't found")
}

fun main(args: Array<String>) {
    val people = listOf(Person("Alice"), Person("Alice"))

    lookForAlice(people)
}
*/



class Person(val name: String)

inline fun List<Person>.forEach2(action: (Person) -> Unit) {
    for (e in this)
        action(e)
}

// 지역 반환하는 방법 2가지
//  1. 라벨(Label)을 이용한 지역 반환
/*
fun lookForAlice(people: List<Person>) {
    people.forEach2 hello@{
        if (it.name == "Alice") {
            println("Found!")
            // return@forEach2  // 람다에 대한 반환
            return@hello
        }
    }

    println("Alice isn't found")
}
*/

//  2. 익명 함수를 전달하면 된다.
//    : 지역 반환을 수행한다.
fun lookForAlice(people: List<Person>) {
    people.forEach2(fun(person) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }
    })

    println("Alice isn't found")
}

fun main(args: Array<String>) {
    val people = listOf(Person("Alice"), Person("Alice"))

    lookForAlice(people)
}


