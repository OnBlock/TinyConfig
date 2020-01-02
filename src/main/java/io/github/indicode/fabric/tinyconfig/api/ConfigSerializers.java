package io.github.indicode.fabric.tinyconfig.api;

import java.util.HashMap;
import java.util.Map;

public class ConfigSerializers {
    // data -> config type -> serializer
    private static Map<Class, Map<Class, DataSerializer>> SERIALIZERS = new HashMap<>();
    public static <T, R> void registerSerializer(Class<T> dataType, Class<R> configType, DataSerializer<T, R> serializer) {
        Map<Class, DataSerializer> typeMap = SERIALIZERS.get(dataType);
        if (typeMap == null) {
            typeMap = new HashMap<>();
        }
        typeMap.put(configType, serializer);
        SERIALIZERS.put(dataType, typeMap);
    }

    public static <T, R> DataSerializer<T, R> getSerializer(Class<T> dataType, Class<R> configType) {
        Map<Class, DataSerializer> typeMap = SERIALIZERS.get(dataType);
        if (typeMap == null) {
            for (Map.Entry<Class, Map<Class, DataSerializer>> entry : SERIALIZERS.entrySet()) {
                if (dataType.isAssignableFrom(entry.getKey())) {
                    return entry.getValue().get(configType);
                }
            }
            return null;
        } else {
            return typeMap.get(configType);
        }
    }
}
