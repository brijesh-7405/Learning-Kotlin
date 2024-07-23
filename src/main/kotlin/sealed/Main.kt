package sealed

import java.io.File
import javax.sql.DataSource

fun main() {
    println("Hello World!")
    println("sum of 19 and 23 is ${sum(19, 23)}")
    printSum(-1, 8)
    val rectangle = Rectangle(5.0, 2.0)
    println("The perimeter is ${rectangle.perimeter}")

    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }

    //When
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }

    for (x in 1..10 step 2) {
        print("${x} ")
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print("${x} ")
    }

    var a = 1
    var b = 2
    a = b.also { b = a }
    println("\na is $a and b is $b")


    InitOrderDemo("hello")

    var m = 1
    m = m.also { it + 1 }.also { it + 1 }
    println("m is $m ")

    var person = Person("Anupam", "Kotlin")

    var l = person.let { it.tutorial = "Android"
        it}

    var al = person.also { it.tutorial = "Hello" }

    println(l)
    println(al)
    println(person)

    println(greetMammal(Cat("Snowy")))

    val func = operation()                                          // 3
    println(func(2))

    //Lamba Function
    val upperCase1: (String) -> String = { str: String -> str.uppercase() } // 1

    val upperCase2: (String) -> String = { str -> str.uppercase() }         // 2

    val upperCase3 = { str: String -> str.uppercase() }                     // 3

// val upperCase4 = { str -> str.uppercase() }                          // 4

    val upperCase5: (String) -> String = { it.uppercase() }                 // 5

    val upperCase6: (String) -> String = String::uppercase                  // 6

    println(upperCase1("hello"))
    println(upperCase2("hello"))
    println(upperCase3("hello"))
    println(upperCase5("hello"))
    println(upperCase6("hello"))
//   var mamm = Mammal("hello")
}

open class Shape

class Rectangle(val height: Double, val length: Double) : Shape() {
    val perimeter = (height + length) * 2
}

fun sum(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun describe(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
    }

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

data class Person(var name: String, var tutorial : String)

// open class Mammal(val name: String)                                                   // 1

class Cat(val catName: String) : Mammal(catName)        {
    init {
        println("calling cat  $catName")
    }
}                                // 2
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun greetMammal(mammal: Mammal): String {
    when (mammal) {                                                                     // 3
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
        is Cat -> return "Hello ${mammal.name}"                                         // 5
    }                                                                                   // 6
    return "default"
}

fun operation(): (Int) -> Int {                                     // 1
    return ::square
}

fun square(x: Int) = x * x


sealed interface Error

class FileReadError(val file: File): IOError()
class DatabaseError(val source: DataSource): IOError()

object RuntimeError : Error


/*
fun sum(a: Int, b: Int): Int {
    return a + b
}*/



//fun main(args: Array<String>) {
//    println("Hello World!")
//
//    // Try adding program arguments via Run/Debug configuration.
//    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")
//}