package java_examples.examples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

interface State extends Serializable {
}

interface View {
    State getCurrentState();

    // Java 8 - default method
    default void restoreState() {}
}

class Button implements View {
    // 내부 클래스(Inner Class)
    //  : 외부 클래스의 '필드'에 쉽게 접근이 가능하다. - 문제점!!!
    //  -> 외부 객체에 대한 암묵적인 참조가 생성된다.
    //    : 강한 참조가 생성되기 때문에 외부 객체가 파괴되지 못하는 문제가 발생할 수 있습니다.

    // class ButtonState implements State {}

    // 중첩 클래스(Nested Class)
    static class ButtonState implements State {}

    @Override
    public State getCurrentState() {
        return new ButtonState();
    }
}

public class InnerClass {
    public static void main(String[] args) {
        Button button = new Button();
        State state = button.getCurrentState();

        // Try with Resource
        try (FileOutputStream fos = new FileOutputStream("state.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(state);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



