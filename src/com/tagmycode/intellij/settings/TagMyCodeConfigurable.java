package com.tagmycode.intellij.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.tagmycode.intellij.IntelliJUtils;
import com.tagmycode.intellij.TagMyCodeProject;
import com.tagmycode.plugin.gui.form.SettingsForm;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public final class TagMyCodeConfigurable implements Configurable {
    private final JPanel mainPanel;

    public TagMyCodeConfigurable(Project project) {
        TagMyCodeProject tagMyCodeProject = IntelliJUtils.getTagMyCodeProject(project);

        mainPanel = (JPanel) new SettingsForm(tagMyCodeProject.getFramework(), null).getMainComponent();
    }

    @Override
    @Nullable
    public JComponent createComponent() {
        return mainPanel;
    }


    @Override
    public boolean isModified() {
        return false;
    }


    @Override
    public void apply() {

    }


    @Override
    public void reset() {

    }


    @Override
    public void disposeUIResources() {
    }

    @Override
    @Nls
    public String getDisplayName() {
        return "TagMyCode";
    }

    @Override
    @Nullable
    @NonNls
    public String getHelpTopic() {
        return null;
    }
}

