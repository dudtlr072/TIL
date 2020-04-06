package java_examples;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Functional Interface = Single Abstract Method(SAM)
@FunctionalInterface
interface OnClickListener {
    void onClick();
}

class Button {
    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    void click() {
        if (listener != null)
            listener.onClick();
    }
}

// 익명 클래스 => 사용하는 만큼 .class 생성된다.
//             JVM에서 class 로드하는 부담이 있다.

// Java 8 람다
//   1) 보일러플레이트 없앤다.
//   2) 불필요한 class를 생성하지 않는다.
//   3) this가 익명 클래스와 다르게 동작한다.
//       익명 클래스 - 익명 클래스 자신
//       람다      - 현재 자신이 호출된 메소드의 this

// Android
//  Oreo 전 -> Java 6/7 : RetroLambda(보일러플레이트)
//  Oreo   -> Java 8

// this가 달라진다.
class MainActivity {
    @Override
    public String toString() {
        return "MainActivity";
    }

    public void onCreate() {
        Button button = new Button();

        button.setOnClickListener(new OnClickListener() {
            @Override
            public String toString() {
                return "익명 클래스";
            }

            @Override
            public void onClick() {
                System.out.println("Button cilcked - " + this);
            }
        });

        button.click();

        button.setOnClickListener(() -> {
            System.out.println("Button cilcked - " + this);
        });

        button.click();
    }

    void create() {
        onCreate();
    }
}

public class LambdaExample {
    // @NotNull: Kotlin에서 Nullable이 아닌 타입으로 인식하게 해준다.
    public static void foo(@NotNull String name) {
        // System.out.println("hello, " + name);

        // ko.HelloKt.hoo
    }


    /*
    public static void main(String[] args) {

        MainActivity activity = new MainActivity();
        activity.create();
    }
    */

    public static void main(String[] args) {

        /*
        List<Integer> result = Arrays.asList(10, 87, 97, 43, 121, 20)
                .stream()
                .map(e -> e * 2)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        */
        List<Integer> result = Arrays.asList(10, 87, 97, 43, 121, 20)
                .parallelStream()
                .map(e -> e * 2)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

    }
}










