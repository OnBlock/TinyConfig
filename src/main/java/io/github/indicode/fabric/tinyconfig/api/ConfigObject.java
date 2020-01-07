package io.github.indicode.fabric.tinyconfig.api;

import java.util.HashMap;
import java.util.Map;

public class ConfigObject extends HashMap<String, Object> {
    public boolean isCategory = false;
    protected Map<String, ConfigEntryMeta> metadata = new HashMap<>();
    public ConfigEntryMeta getMeta(String key) {
        if (metadata.containsKey(key)) {
            return metadata.get(key);
        } else {
            ConfigEntryMeta meta = new ConfigEntryMeta();
            metadata.put(key, meta);
            return meta;
        }
    }
    public <T> T get(String key, Class<T> clazz) {
        return (T) get(key);
    }
}
