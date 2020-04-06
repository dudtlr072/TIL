package ex10


class User(val name: String, val age: Int) {
    // Ctrl + O
    // toString
    // 1. 객체를 문자열로 표현하는 기능입니다.
    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }

    // 2. 객체 동등성: Any.equals / Any.hashCode
    //  Ctrl + N
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        return result
    }

    // 연산자 메소드 재정의 - 비구조화 선언
    //  : 함수의 이름으로 어떤 연산인지 결정된다.
    //  componentN
    operator fun component1(): String {
        return name
    }

    operator fun component2(): Int {
        return age
    }

    operator fun component3(): String {
        return "$name - $age"
    }
}



// data class User(val name: String, val age: Int)

fun main(args: Array<String>) {
    val user = User("Tom", 42)
    println(user)

    val other = User("Tom", 42)
    if (user == other) {
        println("동일한 내용을 가지고 있습니다.")
    }

    // 객체를 복제하는 방법
    //  Java: Object.clone

    // 3. 자동으로 복사 기능을 만들어줍니다. = copy
    //  : 1. Object.clone() 재정의할 때 고려해야하는 수많은 사항들을 직접 처리해줍니다.
    //    2. Object.clone()은 필드값을 변경하는 것이 불가능하다.
    //       copy()는 원하는 필드를 사용자 정의값으로 변경하는 것이 가능하다.
    // val other2 = user.copy()
    // val other2 = user.copy(name = "Bob")

    // List<User>
    val users = listOf(User("Tom", 42),
            User("Bob", 30),
            User("Alice", 15))

    for (e in users)
        println(e)

    // 4. 비구조화 선언
    for ((name, age, str) in users) {
        println("$name($age): $str")
    }

    val map = mapOf(
            "name" to "Tom",     // Pair<String, Any>
            "age"  to 42
    )

    for ((k, v) in map) {
        println("$k -> $v")
    }
}









