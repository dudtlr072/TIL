package java_examples;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResourceExample {
    /*
    public static void main(String[] args) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream("a.txt");
            dos = new DataOutputStream(fos);

            dos.writeChar('A');

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dos != null) {
                try {
                    dos.close()
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */

    // AutoClosable
    static class MyResource implements AutoCloseable {
        // ...
        @Override
        public void close() {
            System.out.println("MyResource Closed");
        }

        public void foo() {}
    }

    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            resource.foo();
        }
    }

    /*
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("a.txt");
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeChar('A');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

}
