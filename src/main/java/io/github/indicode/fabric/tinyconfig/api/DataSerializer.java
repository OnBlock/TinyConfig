package io.github.indicode.fabric.tinyconfig.api;

public interface DataSerializer<T, R> {
    R serialize(T data);
    T deserialize(R data);
}
