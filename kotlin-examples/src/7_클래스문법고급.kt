package ex7

// Java: Reference Type
//     class, interface, enum

// enum: Soft Keyword
//  enum만 사용할 경우, 의미가 없다.
enum class Level {
    INFO, WARN
}

// Kotlin의 인터페이스는 Java의 인터페이스와 차이점이 있습니다.
//  1. 인터페이스에 '기본 구현'을 제공하는 것이 가능합니다.
//    : Java 8에는 인터페이스에 기본 구현을 제공하는 것이 가능합니다.
//       default method / defender method
interface Clickable {
    fun click()

    fun showOff() {
        println("I'm Clickable")
    }
}

interface Focusable {
    fun showOff() {
        println("I'm Focusable")
    }
}

// 인터페이스 2개 이상이 동일한 기본 구현함수를 제공할 경우, 자식은 반드시 해당 메소드를
// 오버라이딩 해야 한다.

// public final class Button
// open: 상속 가능하도록 만들어 주겠다.
open class Button : Clickable, Focusable {
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    override fun click() {
        println("Button clicked")
    }

    // public final void move(int x, int y)
    // open: 함수를 오버라이딩 가능하도록 허용하겠다.
    open fun move(x: Int, y: Int) {
        println("move to ($x, $y)")
    }

}

// class MouseButton extends Button      - 상속
// class MouseButton implements Button   - 구현

// Effective Java
//  : 상속을 위한 설계와 문서를 갖추거나, 그럴 수 없다면 상속을 금지하라

// 상속
//  : 부모 클래스와 자식 클래스간의 강한 결합이 형성된다.
//    기반 클래스를 변경하는 경우, 하위 클래스의 동작이 예기치 않게 변경될 수 있다.

class MouseButton : Button() {
    override fun move(x: Int, y: Int) {
        println("MouseButton moved")
    }
}

fun main(args: Array<String>) {
    val button = Button()
    button.showOff()
}

// https://android-developers.googleblog.com/2018/05/learn-kotlin-fast-with-new-bootcamp.html











