package ex15_2

// 바인딩(Binding)
//  : 어떤 함수를 호출할지 결정되는 시점

// 정적 바인딩
//   view.click()
//   : view의 타입을 보고 결정한다.
//   1. 컴파일 타임에 결정되므로 성능이 좋다.
//   2. 인라인화가 가능하다.

// 동적 바인딩
//   view.click()
//   : view 현재 객체의 타입을 보고 결정한다.
//   1. 합리적으로 동작한다.
//   2. 다형성을 구현하는 핵심 기능


open class View {
    open fun click() = println("View clicked")

    // 1. 동일한 이름의 메소드가 이미 존재한다면, 동일 이름의 메소드가 우선시 된다.
    fun showOff() = println("I'm Real View")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

// 2. 확장된 함수는 정적 바인딩 합니다.
fun View.showOff() = println("I'm a View")      // 1
fun Button.showOff() = println("I'm a Button")  // 2

fun main(args: Array<String>) {
    val view: View = Button()

    view.click()
    view.showOff()
}







