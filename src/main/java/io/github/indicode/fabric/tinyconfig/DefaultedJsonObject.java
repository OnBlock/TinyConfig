package io.github.indicode.fabric.tinyconfig;

import blue.endless.jankson.*;
import blue.endless.jankson.impl.Marshaller;

import java.util.function.Consumer;

/**
 * @author Indigo Amann
 */
public class DefaultedJsonObject extends JsonObject {
    public DefaultedJsonObject(Marshaller marshaller) {
        super();
        this.marshaller = marshaller;
    }
    public DefaultedJsonObject() {
        super();
    }
    public static DefaultedJsonObject of(JsonObject jsonObject) {
        if (jsonObject instanceof DefaultedJsonObject) return (DefaultedJsonObject) jsonObject;
        DefaultedJsonObject n = new DefaultedJsonObject(jsonObject.getMarshaller());
        jsonObject.forEach(n::put);
        return n;
    }
    public JsonElement get(String key, Getter<? extends JsonElement> defaultValueGetter, String comment) {
        if (comment != null) super.setComment(key, comment);
        if (super.get(key) == null) {
            JsonElement defaultValue = defaultValueGetter.get();
            super.put(key, defaultValue);
            return defaultValue;
        } else {
            return super.get(key);
        }
    }
    public JsonElement get(String key, Getter<? extends JsonElement> defaultValueGetter) {
        return get(key, defaultValueGetter, null);
    }
    public JsonElement get(String key, JsonElement defaultValue, String comment) {
        return get(key, () -> defaultValue, comment);
    }
    public JsonElement get(String key, JsonElement defaultValue) {
        return get(key, defaultValue, null);
    }
    public <T> T get(String key, Class<T> tClass, Getter<? extends T> defaultValueGetter, String comment) {
        if (comment != null) super.setComment(key, comment);
        if (super.get(key) == null) {
            T defaultValue = defaultValueGetter.get();
            super.put(key, this.getMarshaller().serialize(defaultValue));
            return defaultValue;
        } else {
            return super.get(tClass, key);
        }
    }
    public <T> T get(String key, Class<T> tClass, T defaultValue, String comment) {
        return get(key, tClass, (Getter<? extends T>) () -> defaultValue, comment);
    }
    public <T> T get(String key, Class<T> tClass, Getter<? extends T> defaultValueGetter) {
        return get(key, tClass, defaultValueGetter, null);
    }
    public <T> T get(String key, Class<T> tClass, T defaultValue) {
        return get(key, tClass, defaultValue, null);
    }
    public DefaultedJsonArray getArray(String key, Getter<? extends DefaultedJsonArray> defaultValueGetter, String comment) {
        if (comment != null) super.setComment(key, comment);
        if (this.containsKey(key)) {
            JsonElement value = this.get(key, defaultValueGetter, comment);
            if (value instanceof JsonArray) {
                return DefaultedJsonArray.of((JsonArray) value);
            } else {
                DefaultedJsonArray defaultValue = defaultValueGetter.get();
                System.out.println("[TinyConfig] " + key + " has an incorrect value type, replacing.");
                set(key, defaultValue, comment);
                return defaultValue;
            }
        } else {
            DefaultedJsonArray defaultValue = defaultValueGetter.get();
            set(key, defaultValue, comment);
            return defaultValue;
        }
    }
    public DefaultedJsonArray getArray(String key, Getter<? extends DefaultedJsonArray> defaultValueGetter) {
        return getArray(key, defaultValueGetter, null);
    }
    public DefaultedJsonArray getArray(String key, DefaultedJsonArray defaultValue, String comment) {
        return getArray(key, () -> defaultValue, comment) ;
    }
    public DefaultedJsonArray getArray(String key, DefaultedJsonArray defaultValue) {
        return getArray(key, defaultValue, null);
    }
    public void accessChild(String key, Consumer<DefaultedJsonObject> modifier, String comment) {
        if (this.containsKey(key)) {
            JsonElement element = get(key);
            if (element instanceof JsonObject) {
                DefaultedJsonObject janksonObject = DefaultedJsonObject.of((JsonObject) element);
                modifier.accept(janksonObject);
                set(key, janksonObject, comment);
            } else {
                System.out.println("[TinyConfig] " + key + " has an incorrect value type, replacing.");
                DefaultedJsonObject janksonObject = new DefaultedJsonObject(getMarshaller());
                modifier.accept(janksonObject);
                set(key, janksonObject, comment);
            }
        } else {
            DefaultedJsonObject janksonObject = new DefaultedJsonObject(getMarshaller());
            modifier.accept(janksonObject);
            set(key, janksonObject, comment);
        }
    }
    public void accessChild(String key, Consumer<DefaultedJsonObject> modifier) {
        accessChild(key, modifier, null);
    }
    public void set(String key, JsonElement value, String comment) {
        super.put(key, value);
        if (comment != null) super.setComment(key, comment);
    }
    public void set(String key, JsonElement value) {
        set(key, value, null);
    }
    public void set(String key, Object value, String comment) {
        set(key, this.getMarshaller().serialize(value), comment);
    }
    public void set(String key, Object value) {
        set(key, this.getMarshaller().serialize(value));
    }
    public JsonPrimitive getPrimitive(String key, Getter<? extends Object> defaultValueGetter, Class clazz, String comment) {
        Class type = getPrimitiveType(key);
        if (type != null && !type.equals(clazz)) {
            System.out.println("[TinyConfig] " + key + " has an incorrect value type, replacing.");
            JsonPrimitive defaultValue = new JsonPrimitive(defaultValueGetter.get());
            set(key, defaultValue, comment);
            return defaultValue;
        } else return (JsonPrimitive) get(key, () -> new JsonPrimitive(defaultValueGetter.get()), comment);
    }
    public JsonPrimitive getPrimitive(String key, Getter<? extends Object> defaultValueGetter, Class clazz) {
        return getPrimitive(key, defaultValueGetter, clazz, null);
    }
    public JsonPrimitive getPrimitive(String key, Object defaultValue, Class clazz, String comment) {
        return getPrimitive(key, () -> defaultValue, clazz, comment);
    }
    public JsonPrimitive getPrimitive(String key, Object defaultValue, Class clazz) {
        return getPrimitive(key, defaultValue, clazz, null);
    }
    public <T extends Enum<T>> Enum<T> getEnum(String key, Enum<T> defaultValue, String comment) {
        if (comment != null) super.setComment(key, comment);
        if (this.containsKey(key)) {
            Enum<T> value = getMarshaller().marshall(defaultValue.getClass(), get(key));
            if (value == null) {
                set(key, getMarshaller().serialize(defaultValue), comment);
                return  defaultValue;
            } else {
                return value;
            }
        } else {
            set(key, getMarshaller().serialize(defaultValue), comment);
            return defaultValue;
        }
    }
    public <T extends Enum<T>> Enum<T> getEnum(String key, Enum<T> defaultValue) {
        return getEnum(key, defaultValue, null);
    }
    public Number getNumber(String key, Getter<? extends Number> defaultValueGetter, String comment) {
        if (!isNumber(key)) {
            System.out.println("[TinyConfig] " + key + " has an incorrect value type, replacing.");
            Number defaultValue = defaultValueGetter.get();
            set(key, new JsonPrimitive(defaultValue), comment);
            return defaultValue;
        } else return (Number) ((JsonPrimitive)get(key, () -> new JsonPrimitive(defaultValueGetter.get()), comment)).getValue();
    }
    public Number getNumber(String key, Getter<? extends Number> defaultValueGetter) {
        return getNumber(key, defaultValueGetter, null);
    }
    public Number getNumber(String key, Number defaultValue, String comment) {
        return getNumber(key, () -> defaultValue, comment);
    }
    public Number getNumber(String key, Number defaultValue) {
        return getNumber(key, defaultValue, null);
    }
    protected Class getPrimitiveType(String key) {
        if (get(key) == null) return null;
        else {
            JsonElement element = get(key);
            if (element instanceof JsonPrimitive) {
                JsonPrimitive primitive = ((JsonPrimitive) element);
                return primitive.getValue().getClass();
            } else return JsonElement.class;
        }
    }
    public boolean isNumber(String key) {
        if (get(key) == null) return false;
        else {
            JsonElement element = get(key);
            if (element instanceof JsonPrimitive) {
                JsonPrimitive primitive = ((JsonPrimitive) element);
                return primitive.getValue() instanceof Number;
            } else return false;
        }
    }
    public String getString(String key, Getter<String> defaultValue, String comment) {
        return getPrimitive(key, defaultValue, String.class, comment).asString();
    }
    public boolean getBool(String key, Getter<Boolean> defaultValue, String comment) {
        return (boolean) getPrimitive(key, defaultValue, Boolean.class, comment).getValue();
    }
    public int getInt(String key, Getter<Integer> defaultValue, String comment) {
        return getNumber(key, defaultValue, comment).intValue();
    }
    public double getDouble(String key, Getter<Double> defaultValue, String comment) {
        return getNumber(key, defaultValue, comment).doubleValue();
    }
    public long getLong(String key, Getter<Long> defaultValue, String comment) {
        return getNumber(key, defaultValue, comment).longValue();
    }
    public void setString(String key, String value, String comment) {
        set(key, new JsonPrimitive(value), comment);
    }
    public void setBool(String key, boolean value, String comment) {
        set(key, new JsonPrimitive(value), comment);
    }
    public void setInt(String key, int value, String comment) {
        set(key, new JsonPrimitive(value), comment);
    }
    public void setDouble(String key, double value, String comment) {
        set(key, new JsonPrimitive(value), comment);
    }
    public void setLong(String key, long value, String comment) {
        set(key, new JsonPrimitive(value), comment);
    }
    public String getString(String key, String defaultValue, String comment) {
        return getPrimitive(key, defaultValue, String.class, comment).asString();
    }
    public boolean getBool(String key, Boolean defaultValue, String comment) {
        return (boolean) getPrimitive(key, defaultValue, Boolean.class, comment).getValue();
    }
    public int getInt(String key, Integer defaultValue, String comment) {
        return getNumber(key, defaultValue, comment).intValue();
    }
    public double getDouble(String key, Double defaultValue, String comment) {
        return getNumber(key, defaultValue, comment).doubleValue();
    }
    public long getLong(String key, Long defaultValue, String comment) {
        return getNumber(key, defaultValue, comment).longValue();
    }

    public String getString(String key, Getter<String> defaultValue) {
        return getPrimitive(key, defaultValue, String.class).asString();
    }
    public boolean getBool(String key, Getter<Boolean> defaultValue) {
        return (boolean) getPrimitive(key, defaultValue, Boolean.class).getValue();
    }
    public int getInt(String key, Getter<Integer> defaultValue) {
        return getNumber(key, defaultValue).intValue();
    }
    public double getDouble(String key, Getter<Double> defaultValue) {
        return getNumber(key, defaultValue).doubleValue();
    }
    public long getLong(String key, Getter<Long> defaultValue) {
        return getNumber(key, defaultValue).longValue();
    }
    public void setString(String key, String value) {
        set(key, new JsonPrimitive(value));
    }
    public void setBool(String key, boolean value) {
        set(key, new JsonPrimitive(value));
    }
    public void setInt(String key, int value) {
        set(key, new JsonPrimitive(value));
    }
    public void setDouble(String key, double value) {
        set(key, new JsonPrimitive(value));
    }
    public void setLong(String key, long value) {
        set(key, new JsonPrimitive(value));
    }
    public String getString(String key, String defaultValue) {
        return getPrimitive(key, defaultValue, String.class).asString();
    }
    public boolean getBool(String key, Boolean defaultValue) {
        return (boolean) getPrimitive(key, defaultValue, Boolean.class).getValue();
    }
    public int getInt(String key, Integer defaultValue) {
        return getNumber(key, defaultValue).intValue();
    }
    public double getDouble(String key, Double defaultValue) {
        return getNumber(key, defaultValue).doubleValue();
    }
    public long getLong(String key, Long defaultValue) {
        return getNumber(key, defaultValue).longValue();
    }
}