package io.github.indicode.fabric.tinyconfig.api;

import io.github.indicode.fabric.tinyconfig.Getter;

public interface ConfigArray<T, A extends ConfigArray<T, A>> extends Iterable<T> {
    default T get(int index) {
        return get(index, null);
    }
    T get(int index, String comment);

    default String getString(int index) {
        return getString(index, null);
    }
    String getString(int index, String comment);

    default boolean getBool(int index) {
        return getBool(index, null);
    }
    boolean getBool(int index, String comment);

    default int getInt(int index) {
        return getInt(index, null);
    }
    int getInt(int index, String comment);

    default double getDouble(int index) {
        return getDouble(index, null);
    }
    double getDouble(int index, String comment);

    default A getArray(int index) {
        return getArray(index, null);
    }
    A getArray(int index, String comment);

    default void setElement(int index, T value) {
        set(index, value, null);
    }
    void setElement(int index, T value, String comment);

    default void set(int index, Object value) {
        set(index, value, null);
    }
    void set(int index, Object value, String comment);
    
    default void addArray(A value) {
        addArray(value, null);
    }
    void addArray(A value, String comment);
}
