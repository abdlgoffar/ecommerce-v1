package ecommerce.v1.helpers.lambda;


@FunctionalInterface
public interface Function02<P1, P2, R> {
    abstract public R apply(P1 p1, P2 p2);
}
