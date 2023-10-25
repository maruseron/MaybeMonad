import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Maybe<T> extends Comparable<Maybe<? extends Comparable<? super T>>> permits Some, None {
    boolean isPresent();
    <R> Maybe<R> map(Function<? super T, ? extends R> fn);
    <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<R>> fn);
    Maybe<T> filter(Predicate<T> pred);
    <R> R fold(R orElse, Function<? super T, ? extends R> fn);

    @Override
    default int compareTo(Maybe<? extends Comparable<? super T>> o) {
        return switch (o) {
            case None() when this instanceof None() -> 0;                         // EQ
            case None() when this instanceof Some(var ignored1) -> 1;             // GT
            case Some(var ignored2) when this instanceof None() -> -1;            // LT
            case Some(var x) when this instanceof Some(var y) -> x.compareTo(y);
            default -> throw new IllegalStateException("Unreachable");
        };
    }
}
