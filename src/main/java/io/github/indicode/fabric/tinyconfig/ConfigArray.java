package io.github.indicode.fabric.tinyconfig;

public interface ConfigArray<T> extends Iterable<T> {
    T get(int key);
    T get(int key, T defaultValue);
    T get(int key, T defaultValue, String comment);

    String getString(int key);
    String getString(int key, String defaultValue);
    String getString(int key, String defaultValue, String comment);

    int getInt(int key);
    int getInt(int key, int defaultValue);
    int getInt(int key, int defaultValue, String comment);

    double getDouble(int key);
    double getDouble(int key, double defaultValue);
    double getDouble(int key, double defaultValue, String comment);

    ConfigArray<T> getArray(int key);
    ConfigArray<T> getArray(int key, ConfigArray<T> defaultValue);
    ConfigArray<T> getArray(int key, ConfigArray<T> defaultValue, String comment);

    void set(int key, T value);
    void set(int key, T value, String comment);

    void setString(int key, String value);
    void setString(int key, String value, String comment);

    void setInt(int key, int value);
    void setInt(int key, int value, String comment);

    void setDouble(int key, double value);
    void setDouble(int key, double value, String comment);

    void setArray(int key, ConfigArray<T> value);
    void setArray(int key, ConfigArray<T> value, String comment);

    void add(T value);
    void add(T value, String comment);

    void addString(String value);
    void addString(String value, String comment);

    void addInt(int value);
    void addInt(int value, String comment);

    void addDouble(double value);
    void addDouble(double value, String comment);

    void addArray(ConfigArray<T> value);
    void addArray(ConfigArray<T> value, String comment);
}
