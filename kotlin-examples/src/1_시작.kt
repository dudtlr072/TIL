// 1_시작.kt
package ex1


// 1) 코틀린 언어 특징
//   : 모던 프로그래밍 언어
//     => Swift(Obj-C), Go(C), Kotlin(Java), Rust(C++)

//   1. 간결함
//     : 보일러플레이트 제거
//   2. 안전함
//     : 널 안전성
//       Nullable(Optional)
//   3. 상호 운용
//      Kotlin <-> Java
//     : Java로 작성된 코드를 이용할 수 있고,
//       Kotlin으로 작성된 코드를 Java에서 쉽게 이용 가능하다.

/*
public class Program {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}
*/

class User {}

fun main(args: Array<String>) {
    println("Hello, Kotlin")

    // val foo = BuilderExample()  // Java Class
}

/*
❯ javap -c HelloKt.class
Compiled from "Hello.kt"
public final class HelloKt {
  public static final void main(java.lang.String[]);
    Code:
       0: aload_0
       1: ldc           #9                  // String args
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkParameterIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
       6: ldc           #17                 // String Hello, Kotlin
       8: astore_1
       9: getstatic     #23                 // Field java/lang/System.out:Ljava/io/PrintStream;
      12: aload_1
      13: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      16: return
}

 */

// $ kotlinc Hello.kt -include-runtime -d Hello.jar
// $ java -jar Hello.jar


// 2) REPL(Read-Eval-Print-Loop)
//  => Intelli J Plugin
// $ wget https://github.com/JodaOrg/joda-time/releases/download/v2.9.9/joda-time-2.9.9.jar
// $ kotlinc-jvm -cp joda-time-2.9.9.jar


// Kotlin = OOP + Functional


















