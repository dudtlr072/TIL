package java_examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Concurrent Modification Exception
//  => 하나의 스레드가 컬렉션을 순회하는 도중에 컬렉션에 새로운 요소가 추가되거나, 삭제되는 경우
//     발생하는 문제

// Android Framework
//   Main Thread -> UI 업데이트

//   Worker Thread -> Collection -> Main Thread
//    : 생산자 소비자 모델 - mutex 2개
//      Concurrent Collection - CopyOnWriteArrayList<>
//       => Lock free

/*
public class CollectionExample {
    public static void main(String[] args) {

        // mutable
        List<Integer> s = new CopyOnWriteArrayList<>();
        s.add(10);
        s.add(20);
        s.add(30);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Integer e : s) {
                        System.out.println(e);
                    }
                }
            }
        });
        thread.start();

        while (true) {
            s.add(40);
            s.add(50);
        }
    }
}
*/
public class CollectionExample {
    // foo 에서는 절대 s를 수정하면 안된다.
    //  : List를 Immutable로 만들어야 한다.
    static void foo(List<Integer> s) {
        for (Integer e : s)
            System.out.println(e);

        // 예외가 발생한다.
        // 문제점: 실행 시간에 실수를 찾는 것이 아니라,
        //       컴파일 시간에 실수를 찾는 것이 좋다.
        s.add(20);
    }


    public static void main(String[] args) {

        // mutable
        List<Integer> s = new CopyOnWriteArrayList<>();
        s.add(10);
        s.add(20);
        s.add(30);

        s = Collections.unmodifiableList(s);
        foo(s);



    }

}