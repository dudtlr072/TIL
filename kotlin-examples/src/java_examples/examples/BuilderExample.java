package java_examples.examples;


// 생성자의 인자가 많을 경우, 빌더 패턴을 고려해라
//  => 4개 이상
// 1) 어떤 인자로 전달되는지 정확하게 파악하기 힘들다.
// 2) 같은 타입이 잘못전달되었을 경우, 파악이 힘들다.

// AOP -> Lombok
class User {
    final private String name;
    private String address;
    private int age;
    private int weight;

    static class Builder {
        private final String name;
        private String address;
        private int age;
        private int weight;

        Builder(String name) {
            this.name = name;
        }

        Builder address(String value) {
            address = value;
            return this;
        }

        Builder age(int value) {
            age = value;
            return this;
        }

        Builder weight(int value) {
            weight = value;
            return this;
        }

        User build() {
            return new User(this);
        }
    }

    private User(Builder b) {
        this.name = b.name;
        this.address = b.address;
        this.age = b.age;
        this.weight = b.weight;
    }
}


public class BuilderExample {
    public static void main(String[] args) {
        String a = "Tom";
        String b = "Suwon";
        int c = 42;
        int d = 100;

        // User u = new User(a, b, c, d);
        User u = new User.Builder("Tom")
                .address(b)
                .age(c)
                .weight(d)
                .build();
    }
}


