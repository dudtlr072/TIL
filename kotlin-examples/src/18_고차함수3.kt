package ex18_3

import java_examples.examples.OnClickListener

// 자바에서 Event를 처리하는 설계는 인터페이스 기반으로 되어 있습니다.
//interface OnClickListener {
//    fun onClick(view: Button)
//}

class Button {
    var listener: OnClickListener? = null

    fun click() {
        // listener?.onClick(this)
        listener?.onClick()
    }
}


// 1. 인터페이스를 직접 구현한다.
//   => 어떤 객체로부터 이벤트가 발생했는지를 통해 분기하는 코드를 작성해야 한다.
/*
class Activity : OnClickListener {
    override fun onClick(view: Button) {
        // println("Button clicked")
        when (view) {
            button1 -> println("Button1 clicked")
            button2 -> println("Button2 clicked")
        }
    }

    var button1 = Button()
    var button2 = Button()

    fun onCreate() {

        button1.listener = this
        button2.listener = this


        button1.click()
        button2.click()
    }
}
*/

class Activity {
    var button1 = Button()
    var button2 = Button()

    fun onCreate() {

        // Java
        //   오직 한개의 추상 메소드를 가지는 인터페이스: Functional Interface
        //     => 람다 표현식을 허용한다.

        // SAM(Single Abstract Method) Interface -> 람다 허용
        //  : '자바의 함수형 인터페이스'를 코틀린에서 람다 표현식을 통해 사용할 수 있게 해주는 문법
        button1.listener = OnClickListener {
            println("Button1 clicked")
        }

        // 익명 객체
        button1.listener = object : OnClickListener {
            override fun onClick() {
                println("Button1 clicked")
            }
        }

        button2.listener = object : OnClickListener {
            override fun onClick() {
                println("Button2 clicked")
            }
        }


        button1.click()
        button2.click()
    }
}


fun main(args: Array<String>) {
    val activity = Activity()
    activity.onCreate()
}








