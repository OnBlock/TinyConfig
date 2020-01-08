package io.github.indicode.fabric.tinyconfig.api;

import java.util.HashMap;

public class ConfigObject extends HashMap<String, ConfigObject.Entry> {
    public boolean isCategory = false;
    public <T> Entry<T> put(String key, T value) {
        Entry<T> entry = new Entry<>(value);
        super.put(key, entry);
        return entry;
    }
    public <T> T get(String key, Class<T> clazz) {
        return (T) get(key);
    }
    public static class Entry<T> {
        protected String comment = null;
        protected String translation = null;
        protected boolean requiresRestart = false;
        protected T data;
        public Entry(T data) {
            this.data = data;
        }
        public Entry<T> setComment(String comment) {
            this.comment = comment;
            return this;
        }
        public Entry<T> setTranslation(String translation) {
            this.translation = translation;
            return this;
        }
        public Entry<T> setRequiresRestart(boolean requiresRestart) {
            this.requiresRestart = requiresRestart;
            return this;
        }

        public String getComment() {
            return comment;
        }
        public String getTranslation() {
            return translation;
        }
        public boolean checkRequiresRestart() {
            return requiresRestart;
        }

        public T get() {
            return data;
        }
    }
}
