package ex12_2

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// Delegation for Property
//  : 프로퍼티를 위한 위임의 기능
//   -> Property에 대해서 getter / setter 동작을 다른 클래스의 객체에게 위임하는 것이 가능합니다.

// getter, setter의 동작은 연산자 재정의 함수를 통해 변경할 수 있습니다.
/*
class SampleDelegate {
    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return "$thisRef - ${property.name}"
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("$thisRef - ${property.name} to $value")
    }
}


class User {
    var name: String by SampleDelegate()
    var address: String by SampleDelegate()

    override fun toString(): String {
        return "User"
    }
}

fun main(args: Array<String>) {
    val user = User()

    user.name = "Tom"    // setter
    println(user.name)   // getter

    user.address = "Suwon" // setter
    println(user.address)  // getter
}
*/

// 코틀린이 이미 제공하는 다양한 표준 델리게이트가 있습니다.

// 1. lazy
//   : val을 '지연 초기화'를 통해 초기화 하고 싶은 경우 사용합니다.

//  by lazy vs lateinit var
//  => lateinit var는 var에서만 사용이 가능하다.
//  => lateinit var는 Primitive Type에 대해서는
//     사용할 수 없습니다.

/*
class Button

fun findViewById(id: Int): Button {
    return Button()
}

object R {
    object id {
        val button = 10
    }
}


fun foo(): String {
    return "foo"
}

class Person {
    init {
        println("Person 객체 생성")
    }

    // by lazy
    // => 스레드 안전하게 초기화됩니다.
    val name: String by lazy {
        println("name 초기화")
        foo()
    }

    // lateinit var age: Double
    val age: Int by lazy {
        42
    }
    // lateinit var는 Primitive Type에 대해서는
    // 사용할 수 없습니다.

    val helloButton: Button by lazy {
        findViewById(R.id.button)
    }

    fun onCreate() {
        // helloButton = findViewById(R.id.button)
    }
}

fun main(args: Array<String>) {
    val person = Person()
    println("------main------")
    println(person.name)
}
*/

/*
// 2. Observation - KVO
//     : 프로퍼티 값의 변경을 알림을 받을 수 있는 방법

// name: String   ->  nameTextView: TextView
//  user.name = "Hello"
//  nameTextView.text = "Hello"

class TextView {
    var text: String = ""
        set(value) {
            println("text value to $value")
            field = value
        }
}

class Activity {
    val nameTextView = TextView()

    // name 값이 변경될 때마다, nameTextView도 변경되어야 한다.
    var name: String by Delegates.observable("") { _, old, new->
        println("$old -> $new")

        nameTextView.text = new
    }
}

fun main(args: Array<String>) {
    val activity = Activity()

    activity.name = "Tom"
    // activity.nameTextView.text = "Tom"
    activity.name = "Bob"
}

*/


// 3. 프로퍼티를 map을 통해서 초기화 하는 방법을 제공한다.
//    KVC(Key-Value Coding)

// JSON - Javascript Object Notation
//  {
//     "name": "Tom",
//     "age" : 42
//  }

/*
// JSON Object -> Kotlin(HashMap<String, Any>) -> Person
class User(map: Map<String, Any>) {
    val name: String by map
    val age: Int by map
    val address: String by map

    // 프로퍼티가 추가될 때마다 아래의 코드는 수정되어야 한다.
    // init {
    //    name = map["name"] as String
    //    age = map["age"] as Int
    // }

    override fun toString(): String {
        return "User(name=$name, age=$age, address=$address)"
    }
}

class Response(map: Map<String, Any>) {
    val user: User by map
    val statusCode: Int by map

    override fun toString(): String {
        return "Response(user=$user)"
    }
}

fun main(args: Array<String>) {
    val map1 = mapOf(
            "name" to "Tom",
            "age" to 42,
            "address" to "Suwon"
    )

    val user = User(map1)
    println(user)

    val map2 = mapOf(
            "user" to user,
            "statusCode" to 200
    )
    val response = Response(map2)
    println(response)
}
*/
// iOS
//  HTTP Client  - AFNetworking(Obj C) -> Alamofire(Swift)
//                 OKHttpClient(Java)  -> ?(Kotlin)


// 4. vetoable
//   : Validation의 조건에 부합되지 않으면 값이 변경되지 않습니다.
class User {
    var name by Delegates.vetoable("Gildong")
    { _, _, new ->
        new.length >= 5 // true 면 값이 변경됩니다.
    }
}

fun main(args: Array<String>) {
    val user = User()
    user.name = "Tom22"

    println(user.name)
}



//-------
class VeryVeryLongClassName



























