package io.github.indicode.fabric.tinyconfig.api;

import blue.endless.jankson.JsonElement;
import io.github.indicode.fabric.tinyconfig.Getter;

import java.util.Collection;

public interface ConfigArray<T, A extends ConfigArray<T, A>> extends Collection<T> {
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

    void removeElement(T value);
    void remove(int index);
    
    default void addElement(T value) {
        addElement(value, null);
    }
    void addElement(T value, String comment);

    void add(Object value, String comment);
}
