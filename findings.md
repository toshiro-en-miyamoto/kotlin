# Findings

Training courses:

- [Android Developer: Training](https://developer.android.com/courses)
  - [Android Basic with Compose](https://developer.android.com/courses/android-basics-compose/course)
    - [Unit 1: Your first Android App](https://developer.android.com/courses/android-basics-compose/unit-1)
      - Pathway 1: Intro to Kotlin
      - Pathway 2: Set up Android Studio
      - Pathway 3: Build a basic layout

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
// fun greeting(): Unit {
fun greeting() {
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

## Jetpack Compose

References:

- [Thinking in Compose](https://developer.android.com/develop/ui/compose/mental-model)
- [API Guidelines for Jetpack Compose](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md#naming-unit-composable-functions-as-entities)
- [API Guidelines for `@Composable` components in Jetpack Compose](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-component-api-guidelines.md)

### Recomposition

In an imperative UI model, to change a widget, you call a setter on the widget to change its internal state. In Compose, you call the composable function again with new data. Doing so causes the function to be recomposed &mdash; the widgets emitted by the function are redrawn, if necessary, with new data. The Compose framework can intelligently recompose only the components that changed. [Recomposition, Thinking in Compose]

Recomposition is the process of calling your composable functions again when inputs change. This happens when the function's inputs change. When Compose recomposes based on new inputs, it only calls the functions or lambdas that might have changed, and skips the rest. By skipping all functions or lambdas that don't have changed parameters, Compose can recompose efficiently.

Never depend on side-effects from executing composable functions, since a function's recomposition may be skipped. If you do, users may experience strange and unpredictable behavior in your app. A side-effect is any change that is visible to the rest of your app. For example, these actions are all dangerous side-effects:

- Writing to a property of a shared object
- Updating an observable in `ViewModel`
- Updating shared preferences

[Recomposition] discusses a number of things to be aware of when you use Compose:

- Recomposition skips as many composable functions and lambdas as possible.
- Recomposition is optimistic and may be canceled.
- A composable function might be run quite frequently, as often as every 
  frame of an animation.
- Composable functions can execute in parallel.
- Composable functions can execute in any order.

### Composable functions

[Thinking in Compose], you can build your user interface by defining a set of *composable functions* that take in data and emit UI elements. A simple example is a `Greeting` widget, which takes in a `String` and emits a `Text` widget which displays a greeting message.

```
@Composable
fun Greeting(name: String) {
    Test("Hello $name")
}
```

A few noteworthy things about it:

- All composable functions must be annotated with the `@Composable` 
  annotation, which informs the Compose compiler that his function is 
  intended to convert data into UI.
- Composable functions that emit UI do not need to return anything.

A `@Composable` function that returns `Unit` and emits the UI when it is composed in a hierarchy is known as a `@Composable` component. [API Guidelines for `@Composable` components]

### Naming `Unit @Composable` functions as entities

[API Guidelines for Jetpack Compose] recommends to name any function that returns `Unit` and bears the `@Composable` annotation

- using `PascalCase`
- a noun, optionally prefixed by descriptive adjectives
- not a verb or verb phrase,
- nor a preposition, adjective or adverb

### Standard layout components

References:

- [Compose layout basics, UI Guides](https://developer.android.com/develop/ui/compose/layouts/basics)
- [Jetpack Compose Playground](https://foso.github.io/Jetpack-Compose-Playground/)
- [Jetpack Compose Reference: `foundation`](https://developer.android.com/reference/kotlin/androidx/compose/foundation/package-summary)
- [Jetpack Compose Reference: `material3`](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary)

Compose provides a collection of ready-to-use layouts to help you arrange your UI elements, and makes it easy to define your own, more-specialized layouts. In many cases, you can just use Compose's standard layout elements. Often these building blocks are all you need: [Compose layouts basics]

- `Column` to place items vertically on the screen
- `Row` to place items horizontally on the screen
- `Box` to put elements on top of another

They support configuring the alignment of the elements they contain. You can write your own composable function to combine these layouts into a more elaborate layout that suits your app.

### Trailing lambda syntax

References:

- [8. Arrange the text elements in a row and column, Pathway 3: Build a basic layout, Unit1: Your first Android app, Android Basics with Compose](https://developer.android.com/codelabs/basic-android-kotlin-compose-text-composables#7)

`Column`, `Row`, and `Box` are composable functions that take composable content as arguments, so you can place items inside these layout elements. [8. Arrange the text elements in a row and column]

```
Row {
  Text("First column")
  Text("Second column")
}
```

Notice in the previous code snippet that curly braces are used instead of parentheses in the Row composable function. This is called *Trailing Lambda Syntax*.

Kotlin offers a special syntax for passing functions as parameters to functions, when the last parameter is a function. When you pass a function as that parameter, you can use trailing lambda syntax.

- Instead of putting the function inside the parentheses, you can place it outside the parentheses in curly braces.
- This is a recommended and common practice in Compose, so you need to be familiar with how the code looks.

```
fun name(param1:T, param2:T, ..., function) {
  // body
}
```

For example, the last parameter in the `Row()` composable function is the `content` parameter, a function that describes the child UI elements. Suppose you wanted to create a row that contains three text elements. This code would work, but it's very cumbersome to use named parameter for the trailing lambda:

```
Row(
  content = {
    Text("Some text")
    Text("Some more text")
    Text("Last text")
  }
)
```

Because the `content` parameter is the last one in the function signature and you pass its value as a lambda expression, you can remove the `content` parameter and the parentheses as follows:

```
Row {
  Text("Some text")
  Text("Some more text")
  Text("Last text")
}
```

### Edge-to-edge

On Android 14 (API level 34) and lower, your app's UI does not draw underneath the system bars and display cutouts by default.

On Android 15 (API level 35) and higher, your app draws underneath the system bars and display cutouts once your app targets SDK 35. This results in a more seamless user experience and allows your app to take full advantage of the window space available to it.

Displaying content behind the system UI is called going [edge-to-edge](https://developer.android.com/develop/ui/compose/layouts/insets).

To enable edge-to-edge on previous Android versions, call `enableEdgeToEdge()` in `Activity.onCreate()`.

> By enabling edge-to-edge, `Column` under `Scaffold` behaves as if `verticalArrangement = Arrangement.Space` property is given. At this writing, unable to find a way to control the arrangement. As shown in the following code, a simple solution is not to enable edge-to-edge.

```
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            YourAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    YourApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
```

## Android Studio

### Markdown preview

Android Studio Ladybug | 2024.2.1 on Mac:

- find action: registry
- scroll to `ide.browser.jcef.sandbox.enable`
- disable that key, and close
- find action:  Choose Boot runtime for the IDE
- select the latest one
  - 21.0.4b509.17 JetBrains Runtime JBR with JCEF (bundled by default))
- restart

### Editor soft wrap

Enable `Settings` &gt; `Editor` &gt; `General` &gt; `Soft Wraps` &gt; 
`Soft-wrap these files`.

### Additional packages

When a first *Empty Activity* project is created, two packages are automatically installed:

- Android SDK Platform 34 (revision 3) in `~/Library/Android/sdk/platforms/android-34`
- Android SDK Build-Tools 34 in `~/Library/Android/sdk/build-tools/34.0.0`

### Build system

Gradle is used by default. Empty Activity projects created on Mac include the 
following `.gitignore` files by default.

- `.gitignore` at the root directory:
  ```gitignore
  *.iml
  .gradle
  /local.properties
  /.idea/caches
  /.idea/libraries
  /.idea/modules.xml
  /.idea/workspace.xml
  /.idea/navEditor.xml
  /.idea/assetWizardSettings.xml
  .DS_Store
  /build
  /captures
  .externalNativeBuild
  .cxx
  local.properties
  ```
- `gitignore` at the `app` directory:
  ```gitignore
  /build
  ```
- `.gitignore` at the `.idea` directory:
  ```gitignore
  # Default ignored files
  /shelf/
  /workspace.xml
  ```

## Android Devices

### Emulator

To see how your app looks and behaves on a device, you need to build and run it. You can deploy your app to a virtual or a physical device. If you don't have any devices configured, you need to either create an *Android Virtual Device* to use the *Android Emulator* or connect a physical device. [[Overview, Build and run your app, Android Studio](https://developer.android.com/studio/run)]

An Android Virtual Device (AVD) is a configuration that defines the characteristics of an Android phone, tablet, Wear OS, Android TV, or Automotive OS device that you want to simulate in the Android Emulator. The Device Manager is a tool you can launch from Android Studio that helps you create and manage AVDs. [[Create and manage virtual devices, Build and run your app, Android Studio](https://developer.android.com/studio/run/managing-avds)]

An AVD contains a hardware profile, system image, storage area, skin, and other properties.

- The hardware profile defines the characteristics of a device as shipped from the factory. The Device Manager comes pre-loaded with certain hardware profiles, such as Pixel devices, and you can define or customize the hardware profiles as needed.
- The AVD has a dedicated *storage area* on your development machine. It stores the device user data, such as installed apps and settings, as well as an emulated SD card. If needed, you can use the Device Manager to wipe user data so the device has the same data as if it were new.
- An emulator skin specifies the appearance of a device. The Device Manager provides some predefined skins. You can also define your own or use skins provided by third parties.

The Android Emulator simulates Android devices on your computer so that you can test your application on a variety of devices and Android API levels without needing to have each physical device. In most cases, the emulator is the best option for your testing needs. Alternatively, you can deploy your app to a physical device. [[Overview, Run your app with Android Emulator, Build and run your app, Android Studio](https://developer.android.com/studio/run/emulator)]

An AVD is a software version, also called an emulator, of a mobile device that runs on your computer and mimics the configuration of a particular type of Android device. [[3. Run your app on the Android Emulator, Code lab: Basic Android Kotlin Composer](https://developer.android.com/codelabs/basic-android-kotlin-compose-emulator)]

- To run an Android app in an emulator on your computer, you first create a virtual device.
- To configure the virtual device, the *Android system image* of your choice must be installed on your computer.
- Android system images use a lot of disk space, so only a few are part of your original installation.

The system image `UpsideDownCake` is the latest version of Android at the time of writing.

- **API Level**: 34
- **Type**: Google APIs
- **Android**: 14.0
- **System image**: `x86_64`

Android Studio displays the following message when installing `UpsideDownCake` completes:

```
Packages to install: - Google APIs Intel x86_64 Atom System Image (system-images;android-34;google_apis;x86_64)

Downloading https://dl.google.com/android/repository/sys-img/google_apis/x86_64-34_r14.zip

Installing Google APIs Intel x86_64 Atom System Image in ~/Library/Android/sdk/system-images/android-34/google_apis/x86_64
```

> When an emulator doesn't launch, open the SDK Manager (Android Studio menu: Tools > SDK Manager) and check the status of items in the SDK Platform and SDK Tools panes.

### Actions on devices

Refer to [[Overview, Run your app with Android Emulator, Build and run your app, Android Studio](https://developer.android.com/studio/run/emulator)]

Gestures for navigating app:

- Swipe the screen
- Drag an item
- Tap
- Double tap
- Touch and hold
- Type
- Pinch and spread
- Vertical swipe

Common actions to the device:

- Turn the screen on or off
- Turn the device on or off
- Volume up or down
- Rotate left or right
- Take screenshot
- Zoom in or out
