/*
 * Copyright (c) 2013-present RedisBungee contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 *
 *  http://www.eclipse.org/legal/epl-v10.html
 */

package com.imaginarycode.minecraft.redisbungee.api.config.loaders;

import com.imaginarycode.minecraft.redisbungee.api.RedisBungeePlugin;
import com.imaginarycode.minecraft.redisbungee.api.config.LangConfiguration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

public interface LangConfigLoader extends GenericConfigLoader {

    int CONFIG_VERSION = 1;

    default void loadLangConfig(RedisBungeePlugin<?> plugin, Path dataFolder) throws IOException {
        Path configFile = createConfigFile(dataFolder, "lang.yml", "lang.yml");
        final YAMLConfigurationLoader yamlConfigurationFileLoader = YAMLConfigurationLoader.builder().setPath(configFile).build();
        ConfigurationNode node = yamlConfigurationFileLoader.load();
        if (node.getNode("config-version").getInt(0) != CONFIG_VERSION) {
            handleOldConfig(dataFolder, "lang.yml", "lang.yml");
            node = yamlConfigurationFileLoader.load();
        }
        // MINI message serializer
        MiniMessage miniMessage = MiniMessage.miniMessage();

        Component prefix = miniMessage.deserialize(node.getNode("prefix").getString("<color:red>[<color:yellow>Redis<color:red>Bungee]"));
        Locale defaultLocale = Locale.forLanguageTag(node.getNode("default-locale").getString("en-us"));
        boolean useClientLocale = node.getNode("use-client-locale").getBoolean(true);
        LangConfiguration.Messages messages = new LangConfiguration.Messages(defaultLocale);
        node.getNode("messages").getChildrenMap().forEach((key, childNode) -> childNode.getChildrenMap().forEach((childKey, childChildNode) -> {
            messages.register(key.toString(), Locale.forLanguageTag(childKey.toString()), childChildNode.getString());
        }));
        messages.test(defaultLocale);
        LangConfiguration.CommandMessages commandMessages = new LangConfiguration.CommandMessages(defaultLocale);

        node.getNode("commands-common").getChildrenMap().forEach((key, childNode) -> childNode.getChildrenMap().forEach((childKey, childChildNode) -> {
            commandMessages.register("commands-common:" + key.toString(), Locale.forLanguageTag(childKey.toString()), childChildNode.getString());
        }));
        node.getNode("commands").getChildrenMap().forEach((key, childNode) -> childNode.getChildrenMap().forEach((childKey, childChildNode) -> childChildNode.getChildrenMap().forEach((childChildKey, childChildChildNode) -> {
            commandMessages.register("commands:" + key.toString() + ":" + childKey.toString(), Locale.forLanguageTag(childChildKey.toString()), childChildChildNode.getString());
        })));
        commandMessages.test(defaultLocale);
        onLangConfigLoad(new LangConfiguration(prefix, defaultLocale, useClientLocale, messages, commandMessages));
    }


    void onLangConfigLoad(LangConfiguration langConfiguration);


}
