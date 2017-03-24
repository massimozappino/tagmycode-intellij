package com.tagmycode.intellij;


import com.intellij.ide.util.PropertiesComponent;

public class IntelliJProperties {

    private final PropertiesComponent propertiesComponent;

    public IntelliJProperties()
    {
        this.propertiesComponent = PropertiesComponent.getInstance();
    }

    public String read(String s) {
        return propertiesComponent.getValue(s, "");
    }

    public void write(String key, String value) {
        propertiesComponent.setValue(key, value);
    }

    public void unset(String s) {
        propertiesComponent.unsetValue(s);
    }

}
