import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public record Some<T>(T value) implements Maybe<T> {
    public Some {
        Objects.requireNonNull(value);
    }

    @Override
    public boolean isPresent() {
        return true;
    }

    @Override
    public <R> Maybe<R> map(Function<? super T, ? extends R> fn) {
        return new Some<>(fn.apply(value));
    }

    @Override
    public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<R>> fn) {
        return fn.apply(value);
    }

    @Override
    public Maybe<T> filter(Predicate<T> pred) {
        return pred.test(value)
                ? this
                : new None<>();
    }

    @Override
    public <R> R fold(R orElse, Function<? super T, ? extends R> fn) {
        return fn.apply(value);
    }
}
