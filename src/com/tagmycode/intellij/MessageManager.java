package com.tagmycode.intellij;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.tagmycode.plugin.IMessageManager;

public class MessageManager implements IMessageManager {
    private final Project project;

    MessageManager(Project project) {
        this.project = project;
    }

    @Override
    public void errorLog(String message) {
        Notifications.Bus.notify(new Notification("TagMyCode", "TagMyCode", message, NotificationType.ERROR));
    }

    @Override
    public void errorDialog(String message) {
        Messages.showMessageDialog(project, message, "Error", Messages.getErrorIcon());
    }
}
