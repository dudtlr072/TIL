package ex7_2

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

// 중첩(Nested Class)
// 내부(Inner Class)

interface State : Serializable
interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class Button : View, Serializable {
    // 중첩 클래스(Nested class)가 기본입니다.
    inner class ButtonState : State

    // 내부 클래스(Inner)를 만드는 방법
    // inner class ButtonState : State
    // -> 외부 클래스에 필드에 반드시 접근해야 하는 경우에만 한정적으로 사용해야 합니다.!!

    override fun getCurrentState(): State {
        return ButtonState()
    }
}

// Java - Exception
//  1. Checked
//     => 반드시 try-catch 구문을 작성해야 합니다.
//  2. Unchecked

// Kotlin은 Unchecked 예외만 존재합니다.
fun main(args: Array<String>) {
    val button = Button()
    val state = button.getCurrentState()

    val fos = FileOutputStream("state.dat")
    val oos = ObjectOutputStream(fos)

    oos.writeObject(state)
}















