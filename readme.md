## Optional is dead, long live pattern matching!
Just kidding. Optional is probably getting a deconstructor once Valhalla comes around. 
Anyway, Java 21's record patterns + pattern matching for switch 
open the door for some very expressive code.  The ability to destructure any Record 
into its members within a type match allows for type safe manipulation of inner values. 
What you could only do with either `Optional.isPresent()` or `Optional.orElseGet(...)`:
```java
public class Main {
    public static void main(String[] args) {
        var opt1 = getOptional();
        if (opt.isPresent()) { // intrusive scope bind
            var value = opt.get();
        }

        // sometimes we want side effects instead
        var opt2 = someOtherOptional().orElseGet();
        
        var opt3 = Optional.empty(); // holds a null reference
    }
}
```
Is now also possible through switch pattern matching:
```java
public class Main {
    public static Maybe<Integer> shouldGiveMaybe() {
        return new Some<>(42);
    }

    public static void main(String[] args) {
        Maybe<Integer> maybe = shouldGiveMaybe();
        System.out.println(switch(maybe) {
            case Some(var i) -> i;
            case None() -> "Nothing here";
        });
    }
}
```
## The Maybe type
Similar to its uglier cousin Optional, Maybe is a type describing values which may not exist.
This is achieved through a common sealed interface permitting two children (sealing guarantees
pattern matching exhaustiveness):
```java
public sealed interface Maybe<T> permits Some, None { /* ... */ }

public record Some<T>(T value) implements Maybe<T> { /* ... */ }

public record None<T>() implements Maybe<T> { /* ... */ }
```
Being records allow for type and record patterns:
```java
if (myMaybe instanceof Some(var value)) { /* ... */ }
```
