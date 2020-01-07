package io.github.indicode.fabric.tinyconfig.json;

import io.github.indicode.fabric.tinyconfig.Getter;

import java.util.function.Consumer;

public interface ConfigObject<T, C extends ConfigObject<T, C, A>, A extends ConfigArray<T, A>> {
    default T get(String key) {
        return get(key, (T) null);
    }
    default T get(String key, T defaultValue) {
        return get(key, defaultValue, null);
    }
    default T get(String key, T defaultValue, String comment) {
        return get(key, () -> defaultValue, comment);
    }
    default T get(String key, Getter<T> defaultValueGetter) {
        return get(key, defaultValueGetter, null);
    }
    T get(String key, Getter<T> defaultValueGetter, String comment);

    default String getString(String key) {
        return getString(key, (String) null);
    }
    default String getString(String key, String defaultValue) {
        return getString(key, defaultValue, null);
    }
    default String getString(String key, String defaultValue, String comment) {
        return getString(key, () -> defaultValue, comment);
    }
    default String getString(String key, Getter<String> defaultValueGetter) {
        return getString(key, defaultValueGetter, null);
    }
    String getString(String key, Getter<String> defaultValueGetter, String comment);

    default boolean getBool(String key) {
        return getBool(key, false);
    }
    default boolean getBool(String key, boolean defaultValue) {
        return getBool(key, defaultValue, null);
    }
    default boolean getBool(String key, boolean defaultValue, String comment) {
        return getBool(key, () -> defaultValue, comment);
    }
    default boolean getBool(String key, Getter<Boolean> defaultValueGetter) {
        return getBool(key, defaultValueGetter, null);
    }
    boolean getBool(String key, Getter<Boolean> defaultValueGetter, String comment);

    default int getInt(String key) {
        return getInt(key, 0);
    }
    default int getInt(String key, int defaultValue) {
        return getInt(key, defaultValue, null);
    }
    default int getInt(String key, int defaultValue, String comment) {
        return getInt(key, () -> defaultValue, comment);
    }
    default int getInt(String key, Getter<Integer> defaultValueGetter) {
        return getInt(key, defaultValueGetter, null);
    }
    int getInt(String key, Getter<Integer> defaultValueGetter, String comment);

    default double getDouble(String key) {
        return getDouble(key, 0);
    }
    default double getDouble(String key, double defaultValue) {
        return getDouble(key, defaultValue, null);
    }
    default double getDouble(String key, double defaultValue, String comment) {
        return getDouble(key, () -> defaultValue, comment);
    }
    default double getDouble(String key, Getter<Double> defaultValueGetter) {
        return getDouble(key, defaultValueGetter, null);
    }
    double getDouble(String key, Getter<Double> defaultValueGetter, String comment);

    default A getArray(String key) {
        return getArray(key, (A) null);
    }
    default A getArray(String key, A defaultValue) {
        return getArray(key, defaultValue, null);
    }
    default A getArray(String key, A defaultValue, String comment) {
        return getArray(key, () -> defaultValue, comment);
    }
    default A getArray(String key, Getter<A> defaultValueGetter) {
        return getArray(key, defaultValueGetter, null);
    }
    A getArray(String key, Getter<A> defaultValueGetter, String comment);

    default void setElement(String key, T value) {
        setElement(key, value, null);
    }
    void setElement(String key, T value, String comment);

    default void set(String key, Object value) {
        set(key, value, null);
    }
    void set(String key, Object value, String comment);

    default void modifyChild(String key, Consumer<C> modifier) {
        modifyChild(key, modifier, null);
    }
    void modifyChild(String key, Consumer<C> modifier, String comment);
}