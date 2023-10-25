import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Maybe<T> permits Some, None {
    boolean isPresent();
    <R> Maybe<R> map(Function<? super T, ? extends R> fn);
    <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<R>> fn);
    Maybe<T> filter(Predicate<T> pred);
    <R> R fold(R orElse, Function<? super T, ? extends R> fn);
}
