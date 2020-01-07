package io.github.indicode.fabric.tinyconfig.gui;

import io.github.indicode.fabric.tinyconfig.Getter;
import io.github.indicode.fabric.tinyconfig.api.ConfigEntryMeta;
import io.github.indicode.fabric.tinyconfig.api.ConfigObject;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ClothEntryBuilder {
    private static Map<Class, ConfigEntryProvider<?, AbstractConfigListEntry>> BUILDERS = new HashMap<>();

    public static <T> void registerBuilder(Class<T> dataType, ConfigEntryProvider<T, AbstractConfigListEntry> getter) {
        BUILDERS.put(dataType, getter);
    }

    public static <T> ConfigEntryProvider<T, AbstractConfigListEntry> getEntryProvider(Class<T> dataType) {
        if (!BUILDERS.containsKey(dataType)) {
            for (Map.Entry<Class, ConfigEntryProvider<?, AbstractConfigListEntry>> entry : BUILDERS.entrySet()) {
                if (dataType.isAssignableFrom(entry.getKey())) {
                    return (ConfigEntryProvider<T, AbstractConfigListEntry>) entry.getValue();
                }
            }
            return null;
        } else {
            return (ConfigEntryProvider<T, AbstractConfigListEntry>) BUILDERS.get(dataType);
        }
    }

    public static AbstractConfigListEntry getGuiEntry(String id, Object data, ConfigEntryMeta metadata, ConfigEntryBuilder entryBuilder) {
        ConfigEntryProvider entryProvider = getEntryProvider(data.getClass());
        AbstractConfigListEntry entry = entryProvider.provide(id, data, metadata, entryBuilder);
        return entry;
    }

    private static final String TPRE = "tinyconfig.";

    private static AbstractConfigListEntry buildEntry(String key, Object data, ConfigEntryMeta meta, ConfigEntryBuilder entryBuilder, String prefix, ConfigBuilder config) {
        if (data instanceof ConfigObject) {
            ConfigObject object = (ConfigObject) data;
            if (object.isCategory) {
                String catName = meta.translation == null ? prefix + "." + key : meta.translation;
                ConfigCategory cat = config.getOrCreateCategory(catName);
                for (Map.Entry<String, Object> entry : object.entrySet()) {
                    AbstractConfigListEntry guiEntry = buildEntry(entry.getKey(), entry.getValue(), object.getMeta(entry.getKey()), entryBuilder, prefix + "." + key, config);//getGuiEntry(entry.getKey(), entry.getValue(), object.getMeta(entry.getKey()));
                    if (guiEntry != null) cat.addEntry(guiEntry);

                }
                return null;
            } else {
                String dropName = meta.translation == null ? TPRE + prefix + "." + key : meta.translation;
                SubCategoryBuilder dropdown = entryBuilder.startSubCategory(dropName);
                for (Map.Entry<String, Object> entry : object.entrySet()) {
                    AbstractConfigListEntry guiEntry = buildEntry(entry.getKey(), entry.getValue(), object.getMeta(entry.getKey()), entryBuilder, prefix + "." + key, config);//getGuiEntry(entry.getKey(), entry.getValue(), object.getMeta(entry.getKey()));
                    if (guiEntry != null) dropdown.add(guiEntry);
                }
                return dropdown.build();
            }
        } else {
            AbstractConfigListEntry guiEntry = getGuiEntry(TPRE + prefix + "." + key, data, meta, entryBuilder);
            return guiEntry;
        }
    }

    public static ConfigBuilder getConfigBuilder(ConfigObject config, String modid) {
        ConfigBuilder configBuilder = ConfigBuilder.create();
        ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();
        for (Map.Entry<String, Object> entry : config.entrySet()) {
            ConfigEntryMeta meta = config.getMeta(entry.getKey());
            AbstractConfigListEntry guiEntry = buildEntry(entry.getKey(), entry.getValue(), meta, entryBuilder, modid, configBuilder);
            if (guiEntry != null) configBuilder.getOrCreateCategory(meta.translation == null ? TPRE + modid + ".general" : meta.translation).addEntry(guiEntry);
        }
        return configBuilder;
    }

    static {
        registerBuilder(String.class, (id, str, meta, entryBuilder) -> entryBuilder.startStrField(id, str).setTooltip(meta.comment).setDefaultValue(str).build());
        registerBuilder(Number.class, (id, num, meta, entryBuilder) -> entryBuilder.startDoubleField(id, num.doubleValue()).setTooltip(meta.comment).setDefaultValue(num.doubleValue()).build());
        registerBuilder(Integer.class, (id, num, meta, entryBuilder) -> entryBuilder.startIntField(id, num).setTooltip(meta.comment).setDefaultValue(num).build());
        registerBuilder(Float.class, (id, num, meta, entryBuilder) -> entryBuilder.startFloatField(id, num).setTooltip(meta.comment).setDefaultValue(num).build());
        registerBuilder(Double.class, (id, num, meta, entryBuilder) -> entryBuilder.startDoubleField(id, num).setTooltip(meta.comment).setDefaultValue(num).build());
        registerBuilder(Long.class, (id, num, meta, entryBuilder) -> entryBuilder.startLongField(id, num).setTooltip(meta.comment).setDefaultValue(num).build());
    }
}
