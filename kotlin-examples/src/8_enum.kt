// 8_enum
package ex8


/*
enum class Color {
    RED, ORANGE, YELLOW, GREEN, INDIGO
}
*/

// 1. Kotlin의 enum은 프로퍼티나 메소드를 가질 수 있습니다.
enum class Color(val r: Int, val g: Int, val b: Int) {
    // 프로퍼티
    RED(255, 0, 0) {
        override fun getName2(): String {
            return "Red"
        }
    },
    ORANGE(255, 165, 0) {
        override fun getName2(): String {
            return "Orange"
        }
    },
    YELLOW(255, 255, 0) {
        override fun getName2(): String {
            return "Yellow"
        }
    },
    GREEN(0, 255, 0) {
        override fun getName2(): String {
            return "Green"
        }
    },
    INDIGO(75, 0, 130) {
        override fun getName2(): String {
            return "Indigo"
        }
    };    // 메소드를 정의하기 위해서는 반드시 세미콜론을 제공해야 합니다.

    abstract fun getName2(): String

    val rgb: Int
        get() {
            return (r * 256 + g) * 256 + b
        }

    fun rgb(): Int {
        return (r * 256 + g) * 256 + b
    }
}

/*
fun main(args: Array<String>) {
    val color = Color.RED
    println(color.name)
    println(color.r)     // Red
    println(color.rgb)   // rgb property
    println(color.rgb()) // rgb method
}
*/


// 2. Kotlin에는 switch 문(statement)이 없습니다.
//             -> when 식(expression)

/*
// statement(문): 결과가 존재하지 않습니다.
// expression(식): 결과가 존재합니다.
fun main(args: Array<String>) {
    val age = 42

    // if 문
    var name: String? = null
    if (age > 50) {
        name = "old"
    } else {
        name = "young"
    }

    // if 식
    val name1 = if (age > 50 && age < 60) {
        "old"
    } else if (age > 30 && age < 40) {
        "young"
    } else {
        "young old"
    }
}
*/


/*
switch (color) {
    case Color.RED: break;
    case Color.ORANGE: break;
    ...
    default:

}
*/
fun getName(color: Color): String {
    // 1. break 문을 사용하지 않습니다.
    // 2. default -> else
    // 3. expression
    //     : 결과가 존재합니다.
    return when (color) {
        Color.RED -> "Red"
        Color.ORANGE -> "Orange"
        Color.YELLOW -> "Yellow"
        Color.GREEN -> "Green"
        else -> "Else Color"
    }
}

// 여러개의 값을 사용하는 when 예제
fun getWarmth(color: Color): String {
    return when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
        Color.GREEN -> "neutral"
        Color.INDIGO -> "cold"
    }
}

// setOf -> Set<Color>
// when 식의 인자로 상수 뿐 아니라 임의의 객체도 허용한다.
fun mix(c1: Color, c2: Color): Color {
    return when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        else -> throw Exception("Dirty color")
    }
}

data class User(val name: String, val age: Int)

fun foo(user: User): String {
    return when (user) {
        User("Tom", 42) -> "Tom is 42"
        else -> "Unknown"
    }
}

/*
class Minus : Expr() {

}
*/

fun main(args: Array<String>) {

    println(foo(User("Tom", 42)))
    println(foo(User("Bob", 100)))


    val c1 = Color.RED
    val c2 = Color.YELLOW

    // Num(10)


    println(getName(c1))  // Red
    println(getName(c2))  // Orange


    println(getWarmth(c1)) // "warm"

    println(mix(c1, c2))   // "ORANGE"
    println(mix(c2, c1))   // "ORANGE"
}




























