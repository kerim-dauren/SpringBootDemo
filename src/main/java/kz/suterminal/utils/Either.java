package kz.suterminal.utils;

public final class Either<L, R> {

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(value, null);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(null, value);
    }

    public final L left;
    public final R right;
    public final boolean isLeft;

    private Either(L l, R r) {
        left = l;
        right = r;
        isLeft = l != null;
    }
}

