package com.tagmycode.intellij;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.tagmycode.plugin.IMessageManager;

public class MessageManager implements IMessageManager {

    private final Project project;

    public MessageManager(Project project) {
    this.project = project;
    }

    @Override
    public void error(String message) {
        Messages.showMessageDialog(project, message, "Error", Messages.getErrorIcon());
    }
}
