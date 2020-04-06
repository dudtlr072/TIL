package ex6

// Property: Field + Accessor Method(getter + setter)
//           자동으로 생성해주는 문법

// var = getter + setter
// val = getter

/*
// Formatting: ctrl + alt + L
class User(var name: String, age: Int) {
    var age: Int = 0
        get() {
            println("age - getter")
            return field + 10
        }
        private set
        // 클래스 내부에서만 필드값을 수정하는 것이 가능합니다.
        // private set(value) {
        //     println("age - setter")
        //    field = value - 5
        // }

    init {
        this.age = age  // setter
    }
}

fun main(args: Array<String>) {
    val u = User("Tom", 42)
    // u.age = 42       // setter
    println(u.age)      // getter
}
*/


/*
public class User {
    private String name;   // Field

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
*/

// 프로퍼티를 메소드처럼 사용하는 것도 가능합니다.
//  => 필드가 없는 프로퍼티도 만들 수 있습니다.

// 프로피터 vs 메소드
//  1) 복잡한 코드는 반드시 메소드를 사용해야 한다.
//  2) 시간이 오래걸리는 작업은 반드시 메소드를 사용해야 한다.
//  3) 프로퍼티의 값을 얻어오는 연산이 부수효과의 원인이 되서는 안된다.
//  4) 프로퍼티는 예외를 던지면 안됩니다.
//  5) 다른 타입으로 변경하는 연산이 반드시 메소드로 만들어야 한다.
//     : toLong(), toString()

//  일반적인 프로퍼티: 필드 + 접근자 메소드
class User(var firstName: String, var lastName: String) {
    var fullName: String
        get() {
            // return firstName + ", " + lastName
            //  : String template
            return "$firstName, $lastName: ${lastName.length} "
        }
        set(value) {
            val arr = value.split(", ", " ", ",")
            firstName = arr[0]
            lastName = arr[1]
        }
}

fun main(args: Array<String>) {
    val user = User("Chansik", "Yun")

    user.fullName = "Gildong Hong"

    println(user.fullName)
}














