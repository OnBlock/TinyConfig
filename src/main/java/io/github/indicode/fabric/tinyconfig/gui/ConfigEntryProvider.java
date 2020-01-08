package io.github.indicode.fabric.tinyconfig.gui;

import io.github.indicode.fabric.tinyconfig.api.ConfigObject;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

public interface ConfigEntryProvider<T, E extends AbstractConfigListEntry> {
    E provide(String id, ConfigObject.Entry<T> entry, ConfigEntryBuilder entryBuilder);
}
