package io.github.indicode.fabric.tinyconfig.json.serializer;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonPrimitive;

public class BooleanJsonSerializer implements JsonDataSerializer<Boolean> {
    @Override
    public JsonElement serialize(Boolean data) {
        return new JsonPrimitive(data);
    }

    @Override
    public Boolean deserialize(JsonElement data) {
        return (Boolean) ((JsonPrimitive)data).getValue();
    }
}
