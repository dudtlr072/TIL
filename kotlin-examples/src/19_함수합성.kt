package ex19

// Function Composition(함수 합성)

// f(x) -> y
// g(y) -> z
//
// h(x) -> z

// x -> f(x) * g(y) -> g(f(x)) -> z


// x: String -> f(x) -> y: Int -> g(y) -> z: Int
// h(x): (String) -> Int

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    val y = f(x)
    g(y)
}
*/

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    g(f(x))
}
*/

//   String          Int             Int
// x: A -> f(x) -> y: B -> g(y) -> z: C

// * 인자를 한개 가지고 있는 함수를 합성할 수 있는 확장 함수
// * 확장함수나 메소드나 인자를 한개만 가지고 있다면 infix 함수로 만들면 편리하다.
infix fun <A, B, C> ((A) -> B).compose(g: (B) -> C): (A) -> C = { x ->
    g(this(x))
}

// fog = f * g

fun main(args: Array<String>) {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    // val fog = compose(f, g)
    //  : 문자열의 길이를 통해 해시 코드를 구하는 함수가 합성됩니다.

    // val fog = f.compose(g)
    val fog = f compose g

    val s1 = "Tom"
    val s2 = "Bob"

    println(fog(s1))
    println(fog(s2))
}








