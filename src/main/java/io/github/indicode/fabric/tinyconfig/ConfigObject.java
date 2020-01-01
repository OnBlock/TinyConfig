package io.github.indicode.fabric.tinyconfig;

import java.util.function.Consumer;

public interface ConfigObject<T> {
    T get(String key);
    T get(String key, T defaultValue);
    T get(String key, T defaultValue, String comment);
    
    String getString(String key);
    String getString(String key, String defaultValue);
    String getString(String key, String defaultValue, String comment);

    int getInt(String key);
    int getInt(String key, int defaultValue);
    int getInt(String key, int defaultValue, String comment);

    double getDouble(String key);
    double getDouble(String key, double defaultValue);
    double getDouble(String key, double defaultValue, String comment);

    ConfigArray<T> getArray(String key);
    ConfigArray<T> getArray(String key, ConfigArray<T> defaultValue);
    ConfigArray<T> getArray(String key, ConfigArray<T> defaultValue, String comment);

    void set(String key, T value);
    void set(String key, T value, String comment);

    void setString(String key, String value);
    void setString(String key, String value, String comment);

    void setInt(String key, int value);
    void setInt(String key, int value, String comment);

    void setDouble(String key, double value);
    void setDouble(String key, double value, String comment);

    void setArray(String key, ConfigArray<T> value);
    void setArray(String key, ConfigArray<T> value, String comment);

    void modifyChild(String key, Consumer<ConfigObject<T>> modifier);
    void modifyChild(String key, Consumer<ConfigObject<T>> modifier, String comment);
}
