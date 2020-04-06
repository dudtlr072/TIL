package java_examples;

// java enum -> enum class

enum Color {
    RED(255, 0, 0) {
        @Override
        String getName() {
            return "Red";
        }
    }, GREEN(0, 255, 0) {
        @Override
        String getName() {
            return "Green";
        }
    }, ORANGE(255, 255, 0) {
        @Override
        String getName() {
            return "Blue";
        }
    };

    public final int r;
    public final int g;
    public final int b;

    abstract String getName();

    Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int rgb() {
        return (r * 256 + g) * 256 + b;
    }
}


public class EnumExample {
    public static void main(String[] args) {

    }
}
