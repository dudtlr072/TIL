package java_examples;

// Java에서 복제를 구현하는 방법
// 1. Object.clone 오버라이드
// 2. protected -> public 변경
// 3. 예외를 밖으로 던지는 것이 아니라 안에서 처리해야 한다.
// 4. 공변 반환의 룰
//     : 반환 타입을 하위 타입으로 변경하는 것을 허용한다.
// 5. Object.clone()의 구현을 사용하기 위해서는 반드시 Point 객체가
//    Cloneable 인터페이스의 하위 타입이어야 한다.
//    Cloneable -> mark up interface

class Point implements Cloneable {
    private int x;
    private int y;

    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Rect {
    private Point leftTop;

    @Override
    public Rect clone() {
        try {
            Rect copy = (Rect) super.clone();
            copy.leftTop = leftTop.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}



public class CloneExample {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        Point p2 = p1.clone();

        System.out.println(p2);
    }
}







