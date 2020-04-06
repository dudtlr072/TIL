package java_examples;



import ex11_3.MouseAdapter;
import ex11_3.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        int n;
        System.out.println(n = 42);

        // Collection - List, Set, Map
        //  => Collections: 컬렉션을 다루는 공통 메소드 집합
        //  => Arrays: 배열을 다루는 공통 메소드 집합
        //  => Objects: 객체에 대해서 동작하는 공통 메소드 집합
        //    : 유틸리티 메소드 클래스

        // Collections
        // Arrays

        // User user = new User("Tom", "Suwon", 100, 200);
//        User user = new User("Tom", 42);
//        user.setAge(42);
//        System.out.println(user.getAge());


//        List<Integer> list = new ArrayList<>();
//        list.add(10);
//        list.add(20);
//        list.add(30);

        List<Integer> list = Arrays.asList(10, 20, 30);

        for (Integer e : list) {
            System.out.println(e);
        }


        // User u = User.Companion.newFacebookUser(100);
        // User u = User.Factory.newFacebookUser(100);

        Window window = new Window();
        window.setMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked() {

            }

            @Override
            public void mouseEntered() {

            }
        });



    }
}

/*
public class Program {
    public static void foo() {
    }

    public static void main(String[] args) {
        User user = new User();
//        user.age = 42;


        // System.out.println(foo());

//        System.out.println("Hello, Java");
//        User user = new User();  // Kotlin


        // val - final Integer
        // var - Integer
        final Integer n1 = new Integer(42);
        // n1 = new Integer(100);

        final int n2 = 42;
        // n2 = 100;

    }
}
*/










