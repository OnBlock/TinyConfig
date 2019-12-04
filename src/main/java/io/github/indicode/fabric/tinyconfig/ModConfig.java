package io.github.indicode.fabric.tinyconfig;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonGrammar;
import blue.endless.jankson.api.Marshaller;
import blue.endless.jankson.api.SyntaxError;
import io.github.cottonmc.jankson.JanksonFactory;
import net.fabricmc.loader.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;

/**
 * @author Indigo Amann
 */
public class ModConfig {
    public String modid;
    public Jankson jankson;
    public ModConfig(String modid, Jankson jankson) {
        this.modid = modid;
        this.jankson = jankson;
    }
    public ModConfig(String modid) {
        this(modid, JanksonFactory.createJankson());
    }
    public File getConfigFile() {
        return new File(FabricLoader.INSTANCE.getConfigDirectory() + "/" + modid + ".json5");
    }
    public File getConfigFile(String file) {
        return new File(FabricLoader.INSTANCE.getConfigDirectory() + "/" + modid + "/" + file + ".json5");
    }
    protected DefaultedJsonObject loadConfig(File file) {
        if (!file.exists()) return new DefaultedJsonObject(jankson.getMarshaller());
        try {
            return DefaultedJsonObject.of(jankson.load(file));
        } catch (IOException | SyntaxError e) {
            return new DefaultedJsonObject(jankson.getMarshaller());
        }
    }
    public DefaultedJsonObject loadConfig() {
        return loadConfig(getConfigFile());
    }
    public DefaultedJsonObject loadConfig(String file) {
        return loadConfig(getConfigFile(file));
    }
    protected void saveConfig(File file, DefaultedJsonObject json) {
        String toSave = json.toJson(JsonGrammar.JANKSON);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                try (PrintWriter out = new PrintWriter(file)) {
                    out.print(toSave);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveConfig(DefaultedJsonObject json) {
        saveConfig(getConfigFile(), json);
    }
    public void saveConfig(String file, DefaultedJsonObject json) {
        saveConfig(getConfigFile(file), json);
    }
    public void configure(Consumer<DefaultedJsonObject> consumer) {
        configure(false, consumer);
    }
    public void configure(String file, Consumer<DefaultedJsonObject> consumer) {
        configure(file, false, consumer);
    }
    public void configure(boolean newJson, Consumer<DefaultedJsonObject> consumer) {
        DefaultedJsonObject config = newJson ? new DefaultedJsonObject(jankson.getMarshaller()) : loadConfig();
        consumer.accept(config);
        saveConfig(config);
    }
    public void configure(String file, boolean newJson, Consumer<DefaultedJsonObject> consumer) {
        DefaultedJsonObject config = newJson ? new DefaultedJsonObject(jankson.getMarshaller()) : loadConfig(file);
        consumer.accept(config);
        saveConfig(file, config);
    }
}
