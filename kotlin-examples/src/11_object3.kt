package ex11_3

// 용도 3. 익명 객체(Anonymous Object)
interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}

class Window {
    // lateinit var mouseListener: MouseAdapter
    // lateinit: 이후에 초기화를 수행하겠다.
    //           초기화를 수행하지 않은 경우 예외가 발생합니다.
    var mouseListener: MouseAdapter? = null

    fun click() {
        // mouseListener!!.mouseClicked()
        // !! - mouseListener가 null일 경우 프로그램이 종료됩니다. - assertion

        mouseListener?.mouseClicked()
        // mouseListner가 null이면 아무 동작도 수행하지 않는다.
    }

    fun enter() {
        mouseListener?.mouseEntered()
    }
}

class MyMouseAdapter : MouseAdapter {
    override fun mouseClicked() {
        println("mouse clicked")
    }

    override fun mouseEntered() {
        println("mouse entered")
    }
}

fun main(args: Array<String>) {
    val window = Window()
    // window.mouseListener = MyMouseAdapter()

    window.mouseListener = object : MouseAdapter {
        override fun mouseClicked() {
            println("mouse clicked")
        }

        override fun mouseEntered() {
            println("mouse entedred")
        }
    }

    // 새로운 객체를 생성한다. 싱글톤이 아니다.
    window.mouseListener = object : MouseAdapter {
        override fun mouseClicked() {
            println("mouse clicked")
        }

        override fun mouseEntered() {
            println("mouse entedred")
        }
    }


    window.click()
    window.enter()
}





