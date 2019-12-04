package io.github.indicode.fabric.tinyconfig;

import blue.endless.jankson.JsonArray;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import blue.endless.jankson.api.Marshaller;

import java.util.function.Consumer;

/**
 * @author Indigo Amann
 */
public class DefaultedJsonArray extends JsonArray {
    public DefaultedJsonArray(Marshaller marshaller) {
        super();
        this.marshaller = marshaller;
    }
    public DefaultedJsonArray() {
        super();
    }
    public static DefaultedJsonArray of(JsonArray jsonArray) {
        if (jsonArray instanceof DefaultedJsonArray) return (DefaultedJsonArray) jsonArray;
        DefaultedJsonArray n = new DefaultedJsonArray(jsonArray.getMarshaller());
        n.addAll(jsonArray);
        return n;
    }
    public DefaultedJsonArray getArray(int index) {
        if (this.size() > index) {
            JsonElement value = this.get(index);
            if (value instanceof JsonArray) {
                return DefaultedJsonArray.of((JsonArray) value);
            }
        }
        return null;
    }
    public Number getNumber(int index) {
        if (isNumber(index)) {
            return (Number)((JsonPrimitive)get(index)).getValue();
        } else return null;
    }
    protected Class getPrimitiveType(int index) {
        if (get(index) == null) return null;
        else {
            JsonElement element = get(index);
            if (element instanceof JsonPrimitive) {
                JsonPrimitive primitive = ((JsonPrimitive) element);
                return primitive.getValue().getClass();
            } else return JsonElement.class;
        }
    }
    public boolean isNumber(int index) {
        if (get(index) == null) return false;
        else {
            JsonElement element = get(index);
            if (element instanceof JsonPrimitive) {
                JsonPrimitive primitive = ((JsonPrimitive) element);
                return primitive.getValue() instanceof Number;
            } else return false;
        }
    }
    public String getString(int index) {
        return get(String.class, index);
    }
    public boolean getBool(int index) {
        return get(Boolean.class, index);
    }
    public int getInt(int index) {
        return getNumber(index).intValue();
    }
    public double getDouble(int index) {
        return getNumber(index).doubleValue();
    }
    public long getLong(int index) {
        return getNumber(index).longValue();
    }
}
