package com.tagmycode.intellij;

import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;
import com.tagmycode.plugin.AbstractVersion;

public class IntelliJVersion extends AbstractVersion {
    @Override
    public String getPluginVersion() {
        return PluginManagerCore.getPlugin(PluginId.getId("com.tagmycode.intellij")).getVersion();
    }

    @Override
    public String getPluginTitle() {
        return "TagMyCode IntelliJ Plugin";
    }
}
