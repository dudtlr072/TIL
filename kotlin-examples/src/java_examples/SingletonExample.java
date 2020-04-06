package java_examples;

// Singleton Pattern
//  : 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해
//    객체를 참조할 수 있도록 한다.
/*
class Cursor {
    // 2. 오직 한개의 객체
    private static final Cursor INSTANCE = new Cursor();
    // static final
    //  : Cursor.class가 JVM에 로드되는 시점에 객체가 생성된다.
    //    여러 개의 스레드에 대해서 안전하게 생성되는 것을 보장한다.

    // 문제점: Cursor의 객체가 생성하는 시간이 오래 걸린다면,
    //       프로그램의 초기 구동 속도에 악영향을 미칠 수 있다.
    //   => 지연 초기화 싱글톤

    // 1. private 생성자
    private Cursor() {}

    // 3. 동일한 방법을 통한 객체 접근
    public static Cursor getInstance() {
        return INSTANCE;
    }
}
*/

// 처음으로 getInstance() 호출한 시점에 객체를 생성하겠다. - Lazy Initialization
class Cursor {
    private Cursor() {}

    // IODH(Initialization On Demand Holder) - Java Idiom
    //  : 중첩 클래스의 정적 필드는 '최초에 접근된 시점에 초기화' 된다.
    private static class Singleton {
        private static final Cursor INSTANCE = new Cursor();
        // static final - 스레드 안전하게 초기화 된다.
    }

    public static Cursor getInstance() {
        return Singleton.INSTANCE;
    }

    /*
    public static Cursor getInstance() {
        if (sInstance == null) {
            synchronized (Cursor.class) {
                if (sInstance == null) {
                    sInstance = new Cursor();
                }
            }
        }

        return sInstance;
    }
    */
}

public class SingletonExample {
    public static void main(String[] args) {
        Cursor c1 = Cursor.getInstance();
        Cursor c2 = Cursor.getInstance();

        System.out.println(c1);
        System.out.println(c2);
    }
}
