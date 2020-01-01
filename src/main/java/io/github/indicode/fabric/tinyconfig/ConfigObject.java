package io.github.indicode.fabric.tinyconfig;

import java.util.function.Consumer;

public interface ConfigObject<T, C, A> {
    default T get(String key) {
        return get(key, (T) null);
    }
    default T get(String key, T defaultValue) {
        return get(key, defaultValue, null);
    }
    default T get(String key, T defaultValue, String comment) {
        return get(key, () -> defaultValue, comment);
    }
    default T get(String key, Getter<T> defaultValue) {
        return get(key, defaultValue, null);
    }
    T get(String key, Getter<T> defaultValue, String comment);

    default String getString(String key) {
        return getString(key, (String) null);
    }
    default String getString(String key, String defaultValue) {
        return getString(key, defaultValue, null);
    }
    default String getString(String key, String defaultValue, String comment) {
        return getString(key, () -> defaultValue, comment);
    }
    default String getString(String key, Getter<String> defaultValue) {
        return getString(key, defaultValue, null);
    }
    String getString(String key, Getter<String> defaultValue, String comment);

    default boolean getBool(String key) {
        return getBool(key, false);
    }
    default boolean getBool(String key, boolean defaultValue) {
        return getBool(key, defaultValue, null);
    }
    default boolean getBool(String key, boolean defaultValue, String comment) {
        return getBool(key, () -> defaultValue, comment);
    }
    default boolean getBool(String key, Getter<Boolean> defaultValue) {
        return getBool(key, defaultValue, null);
    }
    boolean getBool(String key, Getter<Boolean> defaultValue, String comment);

    default int getInt(String key) {
        return getInt(key, 0);
    }
    default int getInt(String key, int defaultValue) {
        return getInt(key, defaultValue, null);
    }
    default int getInt(String key, int defaultValue, String comment) {
        return getInt(key, () -> defaultValue, comment);
    }
    default int getInt(String key, Getter<Integer> defaultValue) {
        return getInt(key, defaultValue, null);
    }
    int getInt(String key, Getter<Integer> defaultValue, String comment);

    default double getDouble(String key) {
        return getDouble(key, 0);
    }
    default double getDouble(String key, double defaultValue) {
        return getDouble(key, defaultValue, null);
    }
    default double getDouble(String key, double defaultValue, String comment) {
        return getDouble(key, () -> defaultValue, comment);
    }
    default double getDouble(String key, Getter<Double> defaultValue) {
        return getDouble(key, defaultValue, null);
    }
    double getDouble(String key, Getter<Double> defaultValue, String comment);

    default A getArray(String key) {
        return getArray(key, (A) null);
    }
    default A getArray(String key, A defaultValue) {
        return getArray(key, defaultValue, null);
    }
    default A getArray(String key, A defaultValue, String comment) {
        return getArray(key, () -> defaultValue, comment);
    }
    default A getArray(String key, Getter<A> defaultValue) {
        return getArray(key, defaultValue, null);
    }
    A getArray(String key, Getter<A> defaultValue, String comment);

    default void set(String key, T value) {
        set(key, value, null);
    }
    void set(String key, T value, String comment);

    default void setString(String key, String value) {
        setString(key, value, null);
    }
    void setString(String key, String value, String comment);

    default void setInt(String key, int value) {
        setInt(key, value, null);
    }
    void setInt(String key, int value, String comment);

    default void setDouble(String key, double value) {
        setDouble(key, value, null);
    }
    void setDouble(String key, double value, String comment);

    default void setArray(String key, A value) {
        setArray(key, value, null);
    }
    void setArray(String key, A value, String comment);

    void modifyChild(String key, Consumer<C> modifier);
    void modifyChild(String key, Consumer<C> modifier, String comment);
}