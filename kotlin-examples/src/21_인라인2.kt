package ex21_2

// Generic
//  왜?
//    1. 알고리즘은 동일하지만, 타입이 다른 경우 사용한다.
//    2. 컬렉션(List, Set, Map)에 고정된 타입의 데이터가 들어갈 수 있도록 한다.

//  원리
//    1. 자바의 제네릭은 타입 소거(Type erasure)를 사용해 구현된다.
//    2. 실행 시점에 제네릭 클래스의 인스턴스에 타입 인자 정보는 존재하지 않는다.

//  * 코틀린에서도 제네릭 타입 인자 정보는 실행 시간에 존재하지 않는다.

/*
fun foo(data: List<String>) {   // fun foo(data: List)

}

fun foo(data: List<Int>) {      // fun foo(data: List)

}
*/

/*
// 실행 시간에 지워지지 않는 제네릭 타입 인자
//   => 실체화된 타입 파라미터
//   * 위의 기술을 사용한 함수는 자바에서 호출이 불가능합니다.

// 실체화된 타입 파라미터를 사용하기 위해서는 반드시 인라인 함수이어야 한다.
inline fun <reified T> isA(value: Any) = value is T

fun main(args: Array<String>) {
    val s = "Hello"
    val n = 42

    // is
    var result = isA<String>(s)
    println(result)

    result = isA<Boolean>(n)
    println(result)
}
*/

open class Context {

    // 기존 안드로이드 프레임워크가 사용하던 방법
    //  => 프레임워크가 다음 액티비티를 생성해야 하는데,
    //     객체를 동적으로 생성하기 위해서 제네릭 인자를 활용하는 것이 불가능하다.
    fun startActivity(context: Context, clazz: Class<out Activity>) {
        val newActivity = clazz.newInstance()
        newActivity.create()
    }
}

open class Activity : Context() {
    open fun onCreate() {
        println("${this.javaClass.simpleName} onCreate()")
    }

    fun create() {
        onCreate()
    }
}

class MainActivity : Activity() {
    override fun onCreate() {
        super.onCreate()

        // startActivity(this, DetailActivity::class.java)
        startActivity<DetailActivity>()
    }
}

class DetailActivity : Activity()

// Anko
inline fun <reified T: Activity> Context.startActivity() {
    startActivity(this, T::class.java)
}

fun main(args: Array<String>) {
    val mainActivity = MainActivity()
    mainActivity.create()
}




















