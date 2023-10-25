public class Main {
    public static <T> String showValue(Maybe<T> maybe) {
        return maybe.fold("No value", x -> "The value is " + x);
    }

    public static Maybe<String> shouldGiveMaybe() {
        return new Some<>("Hello world");
    }

    public static void main(String[] args) {
        var full = new Some<>(42);
        var empty = new None<>();
        System.out.println(showValue(full));
        System.out.println(showValue(empty));

        // records allow for pattern matching in java 21:
        System.out.println(switch(shouldGiveMaybe()) {
            case Some(var s) -> s;
            case None() -> "Nothing here";
        });
    }
}