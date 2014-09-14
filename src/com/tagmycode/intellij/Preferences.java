package com.tagmycode.intellij;


import com.intellij.ide.util.PropertiesComponent;
import com.tagmycode.plugin.AbstractPreferences;

public class Preferences extends AbstractPreferences {

    private final PropertiesComponent propertiesComponent;

    public Preferences()
    {
        this.propertiesComponent = PropertiesComponent.getInstance();
    }

    @Override
    protected String read(String s) {
        return propertiesComponent.getValue(s, "");
    }

    @Override
    protected void write(String key, String value) {
        propertiesComponent.setValue(key, value);
    }

    @Override
    protected void unset(String s) {
        propertiesComponent.unsetValue(s);
    }

}
