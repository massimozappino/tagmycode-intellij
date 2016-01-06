package com.tagmycode.intellij;


import com.intellij.ide.util.PropertiesComponent;
import com.tagmycode.plugin.IStorage;

public class Storage implements IStorage {

    private final PropertiesComponent propertiesComponent;

    public Storage()
    {
        this.propertiesComponent = PropertiesComponent.getInstance();
    }

    @Override
    public String read(String s) {
        return propertiesComponent.getValue(s, "");
    }

    @Override
    public void write(String key, String value) {
        propertiesComponent.setValue(key, value);
    }

    @Override
    public void unset(String s) {
        propertiesComponent.unsetValue(s);
    }

}
