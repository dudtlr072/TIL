package ex12
// 12_Delegate.kt

// Delegate Pattern
//   : 하나 이상의 메소드 호출을 다른 타입의 객체로 전달한다.
interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

class Rectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}


// 상속 -> 정적이다.
// 포함 -> 동적이다.

// 1. UIElement 인터페이스에서 노출한 메소드에 대한 호출을 이를 구현하고 있는 Rectangle 객체로 전달한다.
// 2. by
// 3. 반드시 프로퍼티로 만들 필요도 없습니다.
class Panel(rectangle: Rectangle) : UIElement by rectangle

//
//  OOP -> '포함을 통한 설계를 상속을 통한 설계보다 편리하게 사용할 수 있도록 한다.'
/*
class Panel(val rectangle: Rectangle) : UIElement {
    override fun getHeight(): Int {
        return rectangle.getHeight()
    }

    override fun getWidth(): Int {
        return rectangle.getWidth()
    }
}
*/

fun main(args: Array<String>) {
    val panel = Panel(Rectangle(10, 100, 30, 50))
    println(panel.getHeight())
    println(panel.getWidth())
}










