package io.github.indicode.fabric.tinyconfig.json;

import blue.endless.jankson.JsonArray;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonPrimitive;
import io.github.indicode.fabric.tinyconfig.Getter;
import io.github.indicode.fabric.tinyconfig.api.ConfigArray;

import java.lang.reflect.Field;
import java.util.List;

import static io.github.indicode.fabric.tinyconfig.Common.LOGGER;

public class JsonConfigArray extends JsonArray implements ConfigArray<JsonElement, JsonConfigArray> {

    @Override
    public JsonElement get(int index, String comment) {
        if (comment != null) super.setComment(index, comment);
        return get(index);
    }
    protected  <T> T getPrimitive(int index, Class<T> clazz, String comment) {
        Object value = get(index, comment);
        if (value instanceof JsonPrimitive) {
            if (((JsonPrimitive) value).getValue().getClass() == clazz) {
                return (T) ((JsonPrimitive) value).getValue();
            }
        }
        if (value != null) {
            LOGGER.warn("#{} has an invalid value for type '{}'", index, clazz.getName());
        }
        return null;
    }
    @Override
    public String getString(int index, String comment) {
        return getPrimitive(index, String.class, comment);
    }

    @Override
    public boolean getBool(int index, String comment) {
        return getPrimitive(index, Boolean.class, comment);
    }

    @Override
    public int getInt(int index, String comment) {
        return getPrimitive(index, Integer.class, comment);
    }

    @Override
    public double getDouble(int index, String comment) {
        return getPrimitive(index, Double.class, comment);
    }

    @Override
    public JsonConfigArray getArray(int index, String comment) {
        Object value = get(index, comment);
        if (value instanceof JsonArray) {
            return JsonConfigArray.of(value);
        }
        if (value != null) {
            LOGGER.warn("{} has an invalid value for type 'Array', overwriting with default", key);
        }
        return null;
    }

    @Override
    public void removeElement(JsonElement value) {
        remove(value);
    }

    @Override
    public void remove(int index) { // Bad Falkreon - make it public or protected
        try {
            Field entriesField = JsonArray.class.getField("entries");
            entriesField.setAccessible(true);
            ((List)entriesField.get(this)).remove(index);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addElement(JsonElement value, String comment) {
        add(value, comment);
    }

    @Override
    public void add(Object value, String comment) {
        add(marshaller.serialize(value), comment);
    }
}
