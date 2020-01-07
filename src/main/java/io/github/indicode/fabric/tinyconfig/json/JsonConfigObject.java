/*package io.github.indicode.fabric.tinyconfig.json;

import blue.endless.jankson.JsonArray;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import blue.endless.jankson.impl.Marshaller;
import io.github.indicode.fabric.tinyconfig.Getter;

import java.util.function.Consumer;

import static io.github.indicode.fabric.tinyconfig.Common.LOGGER;

public class JsonConfigObject extends JsonObject implements ConfigObject<JsonElement, JsonConfigObject, JsonConfigArray> {
    public JsonConfigObject(Marshaller marshaller) {
        super();
        this.marshaller = marshaller;
    }
    public JsonConfigObject() {
        super();
    }
    public static JsonConfigObject of(JsonObject jsonObject) {
        if (jsonObject instanceof JsonConfigObject) return (JsonConfigObject) jsonObject;
        JsonConfigObject n = new JsonConfigObject(jsonObject.getMarshaller());
        jsonObject.forEach(n::put);
        return n;
    }
    @Override
    public JsonElement get(String key, Getter<JsonElement> defaultValueGetter, String comment) {
        if (comment != null) super.setComment(key, comment);
        if (super.get(key) == null) {
            JsonElement defaultValue = defaultValueGetter.get();
            super.put(key, defaultValue);
            return defaultValue;
        } else {
            return super.get(key);
        }
    }
    protected  <T> T getPrimitive(String key, Class<T> clazz, Getter<T> defaultValueGetter, String comment) {
        Object value = get(key, () -> new JsonPrimitive(defaultValueGetter.get()), comment);
        if (value instanceof JsonPrimitive) {
            if (((JsonPrimitive) value).getValue().getClass() == clazz) {
                return (T) ((JsonPrimitive) value).getValue();
            }
        }
        if (value != null) {
            LOGGER.warn("{} has an invalid value for type '{}', overwriting with default", key, clazz.getName());
        }
        T defaultValue = defaultValueGetter.get();
        set(key, new JsonPrimitive(defaultValue), comment);
        return defaultValue;
    }
    @Override
    public String getString(String key, Getter<String> defaultValueGetter, String comment) {
        return getPrimitive(key, String.class, defaultValueGetter, comment);
    }

    @Override
    public boolean getBool(String key, Getter<Boolean> defaultValueGetter, String comment) {
        return getPrimitive(key, Boolean.class, defaultValueGetter, comment);
    }

    @Override
    public int getInt(String key, Getter<Integer> defaultValueGetter, String comment) {
        return getPrimitive(key, Integer.class, defaultValueGetter, comment);
    }

    @Override
    public double getDouble(String key, Getter<Double> defaultValueGetter, String comment) {
        return getPrimitive(key, Double.class, defaultValueGetter, comment);
    }

    @Override
    public JsonConfigArray getArray(String key, Getter<JsonConfigArray> defaultValueGetter, String comment) {
        Object value = get(key, defaultValueGetter::get, comment);
        if (value instanceof JsonArray) {
            return JsonConfigArray.of((JsonArray) value);
        }
        if (value != null) {
            LOGGER.warn("{} has an invalid value for type 'Array', overwriting with default", key);
        }
        JsonConfigArray defaultValue = defaultValueGetter.get();
        set(key, defaultValue, comment);
        return defaultValue;
    }

    @Override
    public void setElement(String key, JsonElement value, String comment) {
        super.put(key, value);
        if (comment != null) super.setComment(key, comment);
    }

    @Override
    public void set(String key, Object value, String comment) {
        setElement(key, this.getMarshaller().serialize(value), comment);
    }

    @Override
    public void modifyChild(String key, Consumer<JsonConfigObject> modifier, String comment) {
        if (this.containsKey(key)) {
            JsonElement element = get(key);
            if (element instanceof JsonObject) {
                JsonConfigObject janksonObject = JsonConfigObject.of((JsonObject) element);
                modifier.accept(janksonObject);
                set(key, janksonObject, comment);
            } else {
                LOGGER.warn("{} has an invalid value for type 'Object', overwriting with default", key);
                JsonConfigObject janksonObject = new JsonConfigObject(getMarshaller());
                modifier.accept(janksonObject);
                set(key, janksonObject, comment);
            }
        } else {
            JsonConfigObject janksonObject = new JsonConfigObject(getMarshaller());
            modifier.accept(janksonObject);
            set(key, janksonObject, comment);
        }
    }
}
*/