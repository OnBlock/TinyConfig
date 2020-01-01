package io.github.indicode.fabric.tinyconfig.api;

import io.github.indicode.fabric.tinyconfig.Getter;

public interface ConfigArray<T, A extends ConfigArray<T, A>> extends Iterable<T> {
    default T get(int index) {
        return get(index, (T) null);
    }
    default T get(int index, T defaultValue) {
        return get(index, defaultValue, null);
    }
    default T get(int index, T defaultValue, String comment) {
        return get(index, () -> defaultValue, comment);
    }
    default T get(int index, Getter<T> defaultValue) {
        return get(index, defaultValue, null);
    }
    T get(int index, Getter<T> defaultValue, String comment);

    default String getString(int index) {
        return getString(index, (String) null);
    }
    default String getString(int index, String defaultValue) {
        return getString(index, defaultValue, null);
    }
    default String getString(int index, String defaultValue, String comment) {
        return getString(index, () -> defaultValue, comment);
    }
    default String getString(int index, Getter<String> defaultValue) {
        return getString(index, defaultValue, null);
    }
    String getString(int index, Getter<String> defaultValue, String comment);

    default boolean getBool(int index) {
        return getBool(index, false);
    }
    default boolean getBool(int index, boolean defaultValue) {
        return getBool(index, defaultValue, null);
    }
    default boolean getBool(int index, boolean defaultValue, String comment) {
        return getBool(index, () -> defaultValue, comment);
    }
    default boolean getBool(int index, Getter<Boolean> defaultValue) {
        return getBool(index, defaultValue, null);
    }
    boolean getBool(int index, Getter<Boolean> defaultValue, String comment);

    default int getInt(int index) {
        return getInt(index, 0);
    }
    default int getInt(int index, int defaultValue) {
        return getInt(index, defaultValue, null);
    }
    default int getInt(int index, int defaultValue, String comment) {
        return getInt(index, () -> defaultValue, comment);
    }
    default int getInt(int index, Getter<Integer> defaultValue) {
        return getInt(index, defaultValue, null);
    }
    int getInt(int index, Getter<Integer> defaultValue, String comment);

    default double getDouble(int index) {
        return getDouble(index, 0);
    }
    default double getDouble(int index, double defaultValue) {
        return getDouble(index, defaultValue, null);
    }
    default double getDouble(int index, double defaultValue, String comment) {
        return getDouble(index, () -> defaultValue, comment);
    }
    default double getDouble(int index, Getter<Double> defaultValue) {
        return getDouble(index, defaultValue, null);
    }
    double getDouble(int index, Getter<Double> defaultValue, String comment);

    default A getArray(int index) {
        return getArray(index, (A) null);
    }
    default A getArray(int index, A defaultValue) {
        return getArray(index, defaultValue, null);
    }
    default A getArray(int index, A defaultValue, String comment) {
        return getArray(index, () -> defaultValue, comment);
    }
    default A getArray(int index, Getter<A> defaultValue) {
        return getArray(index, defaultValue, null);
    }
    A getArray(int index, Getter<A> defaultValue, String comment);

    default void setElement(int index, T value) {
        set(index, value, null);
    }
    void setElement(int index, T value, String comment);

    default void set(int index, Object value) {
        set(index, value, null);
    }
    void set(int index, Object value, String comment);
    
    default void addArray(ConfigArray<T, A> value) {
        addArray(value, null);
    }
    void addArray(ConfigArray<T, A> value, String comment);
}
