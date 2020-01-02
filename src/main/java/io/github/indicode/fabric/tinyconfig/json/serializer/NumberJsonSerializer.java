package io.github.indicode.fabric.tinyconfig.json.serializer;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonPrimitive;

public class NumberJsonSerializer<T extends Number> implements JsonDataSerializer<T> {
    @Override
    public JsonElement serialize(T data) {
        return new JsonPrimitive(data);
    }

    @Override
    public T deserialize(JsonElement data) {
        return (T) ((JsonPrimitive)data).getValue();
    }
}
