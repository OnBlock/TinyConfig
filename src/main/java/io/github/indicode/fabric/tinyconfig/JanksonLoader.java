package io.github.indicode.fabric.tinyconfig;

import blue.endless.jankson.JsonGrammar;
import blue.endless.jankson.api.SyntaxError;
import io.github.cottonmc.jankson.JanksonFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Indigo Amann
 */
public class JanksonLoader {
    public File file;
    public JsonGrammar grammar;
    public JanksonLoader(File file) {
        this(file, JsonGrammar.JANKSON);
    }
    public JanksonLoader(File file, JsonGrammar grammar) {
        this.file = file;
        this.grammar = grammar;
    }
    public DefaultedJsonObject load() {
        if (!file.exists()) return new DefaultedJsonObject();
        try {
            return DefaultedJsonObject.of(JanksonFactory.createJankson().load(file));
        } catch (IOException | SyntaxError e) {
            return new DefaultedJsonObject();
        }
    }
    public void save(DefaultedJsonObject jankson) throws IOException {
        String toSave = jankson.toJson(grammar);
        if (!file.exists()) file.createNewFile();
        try (PrintWriter out = new PrintWriter(file)) {
            out.print(toSave);
        }
    }
}
