# Findings

## Kotlin

Refer to [Kotlin language specification](https://kotlinlang.org/spec/introduction.html)

Kotlin version 1.9 supports compilation to the following platforms:

- JVM
- JavaScript
- Native

When Kotlin code compiles down to JVM bytecode, your Kotlin code can call directly into Java code and vice-versa. This means that you can leverage existing Java libraries directly from Kotlin.

### Programming style

Refer to [Kotlin style guide](https://developer.android.com/kotlin/style-guide).

### Data types

Refer to [Kotlin basic types](https://kotlinlang.org/docs/basic-types.html).

In Kotlin, everything is an object in the sense that you can call member functions and properties on any variable. While certain types have an optimized internal representation as primitive values at runtime (such as numbers, characters, booleans and others), they appear and behave like regular classes to you.

#### Number types

| signed    |  bits | literal    |
|-----------|------:|------------|
| `Byte`    |     8 | `127`      |
| `Short`   |    16 | `32_767`   |
| `Int`     |    32 | `123_000`  |  
| `Long`    |    64 | `123_000L` |

| floating  |  bits | whole |   exp |  digits | literal |
|-----------|------:|------:|------:|--------:|---------|
| `Float`   |    32 |    24 |     8 |     6-7 | `1.2f`  |
| `Double`  |    64 |    53 |    11 |   25-26 | `1.2`   |

On the JVM platform, numbers are stored as primitive types: `int`, `double`, and so on. Exceptions are cases when you create a **nullable number reference** such as `Int?` or use generics. In these cases numbers are boxed in Java classes `Integer`, `Double`, and so on.

| unsigned  |  bits | literal     |
|-----------|------:|-------------|
| `UByte`   |     8 | `255u`      |
| `UShort`  |    16 | `65_535u`   |
| `UInt`    |    32 | `123_000u`  |
| `ULong`   |    64 | `123_000uL` |

The main use case of unsigned numbers is utilizing the full bit range of an integer to represent positive values.

```kotlin
val yellow = Color(0xFF_CC_00_CCu)
```

While unsigned integers can only represent positive numbers and zero, it's not a goal to use them where application domain requires non-negative integers. For example, as a type of collection size or collection index value.

- Using signed integers can help to detect accidental overflows and signal error conditions, such as `List.lastIndex` being `-1` for an empty list.
- Unsigned integers cannot be treated as a range-limited version of signed ones because their range of values is not a subset of the signed integers range. Neither signed, nor unsigned integers are subtypes of each other.

#### Characters and strings

Characters are represented by the type `Char`. Character literals go in single quotes: `'1'`.

> On the JVM, a character stored as primitive type: `char`, represents a 16-bit Unicode character.

> On the JVM, characters are boxed in Java classes when a nullable reference is needed, just like with numbers. Identity is not preserved by the boxing operation.

Generally, a string value is a sequence of characters in double quotes (`"`):

```kotlin
val str = "Hello, world!\n"
```

*Multiline strings* can contain newlines and arbitrary text. It is delimited by a triple quote (`"""`), contains no escaping and can contain newlines and any other characters:

```kotlin
val text = """
    >Tell me and I forget.
    >Teach me and I remember.
    >Involve me and I learn.
    >(Benjamin Franklin)
    """.trimMargin(">")
```

String literals may contain *template expressions*. A template expression starts with a dollar sign (`$`) and consists of either a variable name:

```kotlin
val i = 10
println("i = $i") 
// i = 10

val letters = listOf("a","b","c","d","e")
println("Letters: $letters") 
// Letters: [a, b, c, d, e]
```

or an expression in curly braces:

```kotlin
val s = "abc"
println("$s.length is ${s.length}") 
// abc.length is 3
```

#### The `Unit` type

By default, if you don't specify the return type of a function, the default return type is `Unit`. `Unit` means the function doesn't return a value. `Unit` is equivalent to `void` return types in other languages (`void` in Java and C; `Void` or empty tuple `()` in Swift; `None` in Python, etc.). Any function that doesn't return a value implicitly returns `Unit`.

```kotlin
fun greeting(): Unit {
    println("Hello, world!")
}
```

It's optional to specify the `Unit` return type in Kotlin.

> You'll see the `Unit` type again when you learn about a Kotlin feature called lambdas.

### Functions

```kotlin
fun birthdayGreeting(name: String, age: Int): String {
    return """
    	>Happy birthday, $name.
        >You are now $age years old.
    """.trimMargin(">")
}
```

Function parameters in Kotlin are immutable. You cannot reassign the value of a parameter from within the function body.

#### Named arguments

You may call a function with many parameters or you may want to pass your arguments in a different order, such as putting the age parameter before the name parameter. When you include the parameter name when you call a function, it's called a *named argument*.

```kotlin
fun main() {
    println(birthdayGreeting(age = 1_500, name = "E"))
}
```

#### Default arguments

Function parameters can also specify *default arguments*.

```kotlin
fun main() {
    println(birthdayGreeting(age = 1_500))
}

fun birthdayGreeting(name: String = "?", age: Int): String {
    return """
    	>Happy birthday, $name.
        >You are now $age years old.
    """.trimMargin(">")
}
```

## Android Studio

### Flutter application

Flutter application development is supported. In the *Welcome to Android Studio* window, you can find the *New Flutter Project* menu.

### Additional packages

When a first *Empty Activity* project is created, two packages are automatically installed:

- Android SDK Platform 34 (revision 3) in `~/Library/Android/sdk/platforms/android-34`
- Android SDK Build-Tools 34 in `~/Library/Android/sdk/build-tools/34.0.0`

### Build system

Gradle is used by default.

### Java compatibility

In the `app` folder, programs and test codes are placed beneath the `kotlin+java` folder.
