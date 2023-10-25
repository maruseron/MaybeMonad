import java.util.function.Function;
import java.util.function.Predicate;

public record None<T>() implements Maybe<T> {

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override @SuppressWarnings("unchecked")
    public <R> Maybe<R> map(Function<? super T, ? extends R> fn) {
        return (Maybe<R>)this;
    }

    @Override @SuppressWarnings("unchecked")
    public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<R>> fn) {
        return (Maybe<R>)this;
    }

    @Override
    public Maybe<T> filter(Predicate<T> pred) {
        return this;
    }

    @Override
    public <R> R fold(R orElse, Function<? super T, ? extends R> fn) {
        return orElse;
    }
}
