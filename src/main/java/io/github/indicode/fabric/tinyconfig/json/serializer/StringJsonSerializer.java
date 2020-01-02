package io.github.indicode.fabric.tinyconfig.json.serializer;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonPrimitive;

public class StringJsonSerializer implements JsonDataSerializer<String> {
    @Override
    public JsonElement serialize(String data) {
        return new JsonPrimitive(data);
    }

    @Override
    public String deserialize(JsonElement data) {
        return ((JsonPrimitive)data).asString();
    }
}
