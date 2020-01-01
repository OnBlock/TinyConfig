package io.github.indicode.fabric.tinyconfig;

public interface ConfigArray<T, A> extends Iterable<T> {
    default T get(int key) {
        return get(key, (T) null);
    }
    default T get(int key, T defaultValue) {
        return get(key, defaultValue, null);
    }
    default T get(int key, T defaultValue, String comment) {
        return get(key, () -> defaultValue, comment);
    }
    default T get(int key, Getter<T> defaultValue) {
        return get(key, defaultValue, null);
    }
    T get(int key, Getter<T> defaultValue, String comment);

    default String getString(int key) {
        return getString(key, (String) null);
    }
    default String getString(int key, String defaultValue) {
        return getString(key, defaultValue, null);
    }
    default String getString(int key, String defaultValue, String comment) {
        return getString(key, () -> defaultValue, comment);
    }
    default String getString(int key, Getter<String> defaultValue) {
        return getString(key, defaultValue, null);
    }
    String getString(int key, Getter<String> defaultValue, String comment);

    default boolean getBool(int key) {
        return getBool(key, false);
    }
    default boolean getBool(int key, boolean defaultValue) {
        return getBool(key, defaultValue, null);
    }
    default boolean getBool(int key, boolean defaultValue, String comment) {
        return getBool(key, () -> defaultValue, comment);
    }
    default boolean getBool(int key, Getter<Boolean> defaultValue) {
        return getBool(key, defaultValue, null);
    }
    boolean getBool(int key, Getter<Boolean> defaultValue, String comment);

    default int getInt(int key) {
        return getInt(key, 0);
    }
    default int getInt(int key, int defaultValue) {
        return getInt(key, defaultValue, null);
    }
    default int getInt(int key, int defaultValue, String comment) {
        return getInt(key, () -> defaultValue, comment);
    }
    default int getInt(int key, Getter<Integer> defaultValue) {
        return getInt(key, defaultValue, null);
    }
    int getInt(int key, Getter<Integer> defaultValue, String comment);

    default double getDouble(int key) {
        return getDouble(key, 0);
    }
    default double getDouble(int key, double defaultValue) {
        return getDouble(key, defaultValue, null);
    }
    default double getDouble(int key, double defaultValue, String comment) {
        return getDouble(key, () -> defaultValue, comment);
    }
    default double getDouble(int key, Getter<Double> defaultValue) {
        return getDouble(key, defaultValue, null);
    }
    double getDouble(int key, Getter<Double> defaultValue, String comment);

    default A getArray(int key) {
        return getArray(key, (A) null);
    }
    default A getArray(int key, A defaultValue) {
        return getArray(key, defaultValue, null);
    }
    default A getArray(int key, A defaultValue, String comment) {
        return getArray(key, () -> defaultValue, comment);
    }
    default A getArray(int key, Getter<A> defaultValue) {
        return getArray(key, defaultValue, null);
    }
    A getArray(int key, Getter<A> defaultValue, String comment);

    default void set(int key, T value) {
        set(key, value, null);
    }
    void set(int key, T value, String comment);

    default void setString(int key, String value) {
        setString(key, value, null);
    }
    void setString(int key, String value, String comment);

    default void setInt(int key, int value) {
        setInt(key, value, null);
    }
    void setInt(int key, int value, String comment);

    default void setDouble(int key, double value) {
        setDouble(key, value, null);
    }
    void setDouble(int key, double value, String comment);

    default void setArray(int key, A value) {
        setArray(key, value, null);
    }
    void setArray(int key, A value, String comment);
    
    void addArray(ConfigArray<T, A> value);
    void addArray(ConfigArray<T, A> value, String comment);
}
