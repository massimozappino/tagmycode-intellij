package com.tagmycode.intellij;


import com.intellij.ide.util.PropertiesComponent;
import com.tagmycode.plugin.AbstractStorage;

public class Storage extends AbstractStorage {

    private final PropertiesComponent propertiesComponent;

    public Storage()
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
