package ru.ISerg999.checkers.utils;

public class CPair<T, U> {

    private T x;
    private U y;

    public CPair(T x, U y) {
        this.x = x;
        this.y = y;
    }
    public CPair(CPair<T, U> other) {
        this.x = other.x;
        this.y = other.y;
    }

    public T getX() { return x; }
    public U getY() { return y; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (null == obj) return false;
        if (!(obj instanceof CPair)) return false;
        CPair<T, U> newP = (CPair<T, U>) obj;
        return this.x.equals(newP.x) && this.y.equals(newP.y);
    }

    @Override
    public int hashCode() {
        int a = x == null ? 0: x.hashCode();
        int b = y == null ? 0: y.hashCode();
        return a ^ b;
    }
}
