package io.github.indicode.fabric.tinyconfig.json.serializer;

import blue.endless.jankson.JsonElement;
import io.github.indicode.fabric.tinyconfig.api.ConfigSerializers;
import io.github.indicode.fabric.tinyconfig.api.DataSerializer;

import static io.github.indicode.fabric.tinyconfig.api.ConfigSerializers.registerSerializer;

public interface JsonDataSerializer<T> extends DataSerializer<T, JsonElement> {
    public static void registerSerializers() {
        registerSerializer(String.class, JsonElement.class, new StringJsonSerializer());
        registerSerializer(Boolean.class, JsonElement.class, new BooleanJsonSerializer());
        registerSerializer(Number.class, JsonElement.class, new NumberJsonSerializer<>());
    }
}
